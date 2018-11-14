package com.anriku.cherryplayback.ui

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.adapter.MainVPAdapter
import com.anriku.cherryplayback.databinding.ActivityMainBinding
import com.anriku.cherryplayback.event.ServiceConnectEvent
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.ui.mine.MineContainerFragment
import com.anriku.cherryplayback.ui.square.MusicSquareContainerFragment
import com.anriku.cherryplayback.utils.LogUtil
import com.anriku.cherryplayback.utils.PlaybackInfoListener
import com.anriku.cherryplayback.viewmodel.SongsViewModel
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : BaseActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mSongsViewModel: SongsViewModel
    private lateinit var mPlaybackListener: PlaybackInfoListener
    private val mMusicListFragment: MusicListFragment by lazy(LazyThreadSafetyMode.NONE) { MusicListFragment() }

    companion object {
        private const val TAG = "MainActivity"
    }

    private val mPlayAndPauseIcons: List<Drawable?> by lazy(LazyThreadSafetyMode.NONE) {
        listOf(
            ContextCompat.getDrawable(this, R.drawable.ic_pause),
            ContextCompat.getDrawable(this, R.drawable.ic_play)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initActivity()
    }

    private fun initActivity() {
        mSongsViewModel = ViewModelProviders.of(this).get(SongsViewModel::class.java)

        val songsViewModel = mSongsViewModel
        songsViewModel.startAndBindService(this)

        mBinding.listeners = Listeners(
            onStartControlActivity = {
                val intent = Intent(this, ControlActivity::class.java)
                startActivity(intent)
            }, onPlayAndPause = {
                if (songsViewModel.binder?.isPlaying() == true) {
                    songsViewModel.binder?.pause()
                } else {
                    songsViewModel.binder?.play()
                }
            }, onList = {
                mMusicListFragment.show(supportFragmentManager, "music_list_fragment")
            }
        )

        mBinding.vp.adapter = MainVPAdapter(
            supportFragmentManager,
            mutableListOf(
                MineContainerFragment(),
                MusicSquareContainerFragment()
            ),
            mutableListOf("我的", "音乐广场")
        )
        mBinding.tl.setupWithViewPager(mBinding.vp)
    }

    /**
     * 当绑定服务成功了之后进行音乐的初始化操作。
     *
     * @param serviceConnectEvent [ServiceConnectEvent]
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onServiceConnected(serviceConnectEvent: ServiceConnectEvent) {
        mPlaybackListener = PlaybackListener()

        mSongsViewModel.binder!!.addPlaybackInfoListener(mPlaybackListener)
    }


    override fun onDestroy() {
        mSongsViewModel.unbindService(this)
        mSongsViewModel.binder?.removePlaybackInfoListener(mPlaybackListener)
        super.onDestroy()
    }


    inner class PlaybackListener : PlaybackInfoListener() {

        override fun onLoadMedia(song: Song) {}

        override fun onDurationChanged(duration: Int) {}

        override fun onPositionChanged(position: Int) {}

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
        val onStartControlActivity: (View) -> Unit = {},
        val onPlayAndPause: (ImageView) -> Unit = {},
        val onList: (ImageView) -> Unit = {}
    ) {

        fun onStartControlActivityClick(view: View) {
            onStartControlActivity(view)
        }

        fun onPlayAndPauseClick(view: View) {
            onPlayAndPause(view as ImageView)
        }

        fun onListClick(view: View) {
            onList(view as ImageView)
        }
    }
}
