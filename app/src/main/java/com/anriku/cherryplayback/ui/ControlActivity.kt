package com.anriku.cherryplayback.ui

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.config.PLAY_PATTERN
import com.anriku.cherryplayback.databinding.ActivityControlBinding
import com.anriku.cherryplayback.event.ServiceConnectEvent
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.network.ImageUrl
import com.anriku.cherryplayback.utils.IMusicBinder
import com.anriku.cherryplayback.utils.PlaybackInfoListener
import com.anriku.cherryplayback.utils.extensions.getSPValue
import com.anriku.cherryplayback.utils.extensions.setSPValue
import com.anriku.cherryplayback.viewmodel.SongsViewModel
import com.bumptech.glide.Glide
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class ControlActivity : BaseActivity() {

    companion object {
        private const val TAG = "ControlActivity"

        const val TRANSITION_NAME = "civAlbum"
    }

    private lateinit var mBinding: ActivityControlBinding
    private val mMusicListFragment: MusicListFragment by lazy(LazyThreadSafetyMode.NONE) { MusicListFragment() }
    private lateinit var mSongsViewModel: SongsViewModel
    // 表示是否是用户在进行拖动SeekBar操作
    private var mIsUserSeeking: Boolean = false
    // 当前音乐的播放模式
    private var mPlayPattern: Int = IMusicBinder.SEQUENCE_PLAY
    private lateinit var mPlaybackListener: PlaybackInfoListener

    private val mPlayAndPauseIcons: List<Drawable?> by lazy(LazyThreadSafetyMode.NONE) {
        listOf(
            ContextCompat.getDrawable(this, R.drawable.ic_pause),
            ContextCompat.getDrawable(this, R.drawable.ic_play)
        )
    }
    private val mPatternIcons: List<Drawable?> by lazy(LazyThreadSafetyMode.NONE) {
        listOf(
            ContextCompat.getDrawable(this, R.drawable.ic_sequence_play),
            ContextCompat.getDrawable(this, R.drawable.ic_random_play),
            ContextCompat.getDrawable(this, R.drawable.ic_single_play)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_control)
        mBinding.setLifecycleOwner(this)

        initControlActivity()
    }


    private fun initControlActivity() {
        ViewCompat.setTransitionName(mBinding.civAlbum, TRANSITION_NAME)

        mSongsViewModel = ViewModelProviders.of(this).get(SongsViewModel::class.java)
        val songsViewModel = mSongsViewModel

        // 获取之前设置的播放模式并更新图标
        mPlayPattern = getSPValue().getInt(PLAY_PATTERN, IMusicBinder.SEQUENCE_PLAY)
        mBinding.ivPattern.setImageDrawable(mPatternIcons[mPlayPattern])

        songsViewModel.bindService(this)

        mBinding.viewModel = songsViewModel
        // 设置各个按键的点击事件
        mBinding.listeners = Listeners(onPattern = {
            mPlayPattern = (mPlayPattern + 1) % 3
            setSPValue({
                putInt(PLAY_PATTERN, mPlayPattern)
            })
            it.setImageDrawable(mPatternIcons[mPlayPattern])
        }, onList = {
            mMusicListFragment.show(supportFragmentManager, "music_list_fragment")
        }, onPrevious = {
            songsViewModel.binder?.loadAnotherMusic(false)
        }, onNext = {
            songsViewModel.binder?.loadAnotherMusic()
        }, onPlayAndPause = {
            if (songsViewModel.binder?.isPlaying() == true) {
                songsViewModel.binder?.pause()
            } else {
                songsViewModel.binder?.play()
            }
        })

        // 给SeekBar设置拖动的事件
        mBinding.sb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            private var mSeekProgress = 0

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // 如果来自于用户的改变就进拖动位置的记录
                if (fromUser) {
                    mSeekProgress = progress
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                mIsUserSeeking = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                mIsUserSeeking = false
                mSongsViewModel.binder?.seekTo(mSeekProgress)
            }
        })
    }

    override fun onDestroy() {
        mSongsViewModel.unbindService(this)
        mSongsViewModel.binder?.removePlaybackInfoListener(mPlaybackListener)
        super.onDestroy()
    }

    /**
     * 当绑定服务成功了之后进行音乐的初始化操作。
     *
     * @param serviceConnectEvent [ServiceConnectEvent]
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onServiceConnected(serviceConnectEvent: ServiceConnectEvent) {
        mPlaybackListener = PlaybackListener()
        mSongsViewModel.binder?.addPlaybackInfoListener(mPlaybackListener)
    }

    inner class PlaybackListener : PlaybackInfoListener() {

        override fun onLoadMedia(song: Song) {
            mSongsViewModel.onLoadMedia(this@ControlActivity, song, mBinding.civAlbum)
        }

        override fun onDurationChanged(duration: Int) {
            mBinding.sb.max = duration
        }

        override fun onPositionChanged(position: Int) {
            if (!mIsUserSeeking) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mBinding.sb.setProgress(position, true)
                } else {
                    mBinding.sb.progress = position
                }
            }
        }

        override fun onStateChange(state: Int) {
            when (state) {
                PlaybackInfoListener.PAUSE, PlaybackInfoListener.COMPLETE,
                PlaybackInfoListener.INVALID, PlaybackInfoListener.RESET -> {
                    mBinding.ivPlayOrPause.setImageDrawable(mPlayAndPauseIcons[1])
                }
                else -> {
                    mBinding.ivPlayOrPause.setImageDrawable(mPlayAndPauseIcons[0])
                }
            }
        }

        override fun onComplete() {}
    }


    class Listeners(
        val onPattern: (ImageView) -> Unit = {},
        val onPrevious: (ImageView) -> Unit = {},
        val onPlayAndPause: (ImageView) -> Unit = {},
        val onNext: (ImageView) -> Unit = {},
        val onList: (ImageView) -> Unit = {}
    ) {

        fun onPatternClick(view: View) {
            onPattern(view as ImageView)
        }

        fun onPreviousClick(view: View) {
            onPrevious(view as ImageView)
        }

        fun onPlayAndPauseClick(view: View) {
            onPlayAndPause(view as ImageView)
        }

        fun onNextClick(view: View) {
            onNext(view as ImageView)
        }

        fun onListClick(view: View) {
            onList(view as ImageView)
        }
    }
}
