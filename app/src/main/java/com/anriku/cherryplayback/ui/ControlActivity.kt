package com.anriku.cherryplayback.ui

import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.databinding.ActivityControlBinding
import com.anriku.cherryplayback.event.ServiceConnectEvent
import com.anriku.cherryplayback.lifecycle.EventBusObserver
import com.anriku.cherryplayback.service.MusicService
import com.anriku.cherryplayback.utils.PlaybackInfoListener
import com.anriku.cherryplayback.viewmodel.SongsViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class ControlActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityControlBinding
    private val mMusicListFragment: MusicListFragment by lazy(LazyThreadSafetyMode.NONE) {
        MusicListFragment()
    }
    private lateinit var mSongsViewModel: SongsViewModel
    private var mIsUserSeeking: Boolean = false
    private var mSeekProgress: Int = 0
    private val mIcons: List<Drawable?> by lazy(LazyThreadSafetyMode.NONE) {
        listOf(ContextCompat.getDrawable(this, R.drawable.ic_play),
                ContextCompat.getDrawable(this, R.drawable.ic_pause))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_control)
        lifecycle.addObserver(EventBusObserver(this))

        initControlActivity()
    }

    private fun initControlActivity() {
        mSongsViewModel = ViewModelProviders.of(this).get(SongsViewModel::class.java)
        mSongsViewModel.startAndBindService(this)

        mBinding.listeners = ControlActivityListener(onPattern = {

        }, onList = {
            mMusicListFragment.show(supportFragmentManager, "music_list_fragment")
        }, onPrevious = {
            mSongsViewModel.binder?.reset()
            mSongsViewModel.binder?.loadAnotherMusic(MusicService.SEQUENCE_PLAY, false)
            mSongsViewModel.binder?.play()
        }, onNext = {
            mSongsViewModel.binder?.reset()
            mSongsViewModel.binder?.loadAnotherMusic(MusicService.SEQUENCE_PLAY)
            mSongsViewModel.binder?.play()
        }, onPlayAndPause = {
            if (mSongsViewModel.binder?.isPlaying() == true) {
                it.setImageDrawable(mIcons[1])
                mSongsViewModel.binder?.pause()
            } else {
                it.setImageDrawable(mIcons[0])
                mSongsViewModel.binder?.play()
            }
        })

        mBinding.sb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onServiceConnected(serviceConnectEvent: ServiceConnectEvent) {
        mSongsViewModel.getSongs(this)
        mSongsViewModel.binder?.loadAnotherMusic(MusicService.SEQUENCE_PLAY)
        mSongsViewModel.binder?.addPlaybackInfoListener(PlaybackListener())
    }

    inner class PlaybackListener : PlaybackInfoListener() {

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
                PlaybackInfoListener.PAUSED, PlaybackInfoListener.COMPLETED,
                PlaybackInfoListener.INVALID, PlaybackInfoListener.RESET -> {
                    mBinding.ivPlayOrPause.setImageDrawable(mIcons[1])
                }
                else -> {
                    mBinding.ivPlayOrPause.setImageDrawable(mIcons[0])
                }
            }
        }

        override fun onComplete() {
            mSongsViewModel.binder?.loadAnotherMusic(MusicService.SEQUENCE_PLAY)
        }

    }


    class ControlActivityListener(
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
