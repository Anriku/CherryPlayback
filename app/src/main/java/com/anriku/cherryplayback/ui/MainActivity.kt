package com.anriku.cherryplayback.ui

import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.databinding.ActivityMainBinding
import com.anriku.cherryplayback.event.ServiceConnectEvent
import com.anriku.cherryplayback.model.Song
import com.anriku.cherryplayback.network.ImageUrl
import com.anriku.cherryplayback.service.MusicService
import com.anriku.cherryplayback.utils.PlaybackInfoListener
import com.anriku.cherryplayback.utils.SafeOnclickListener
import com.anriku.cherryplayback.viewmodel.SongsViewModel
import com.bumptech.glide.Glide
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : BaseActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mSongsViewModel: SongsViewModel
    private lateinit var mPlaybackListener: PlaybackInfoListener
    private val mMusicListFragment: MusicListFragment by lazy(LazyThreadSafetyMode.NONE) { MusicListFragment() }
    protected lateinit var mNavController: NavController

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
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        mNavController = host.navController

        val songsViewModel = mSongsViewModel

        songsViewModel.bindService(this)

        mBinding.listeners = Listeners(
            onStartControlActivity = {
                startActivity(
                    Intent(this, ControlActivity::class.java),
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this
                        , mBinding.civAlbum, ControlActivity.TRANSITION_NAME
                    ).toBundle()
                )
            }, onPlayAndPause = SafeOnclickListener {
                val intent = Intent(this, MusicService::class.java).apply {
                    action = MusicService.ACTION_PLAY_OR_PAUSE
                }
                startService(intent)
            }, onList = {
                mMusicListFragment.show(supportFragmentManager, "music_list_fragment")
            }
        )

    }

    /**
     * 当绑定服务成功了之后进行音乐的初始化操作。
     *
     * @param serviceConnectEvent [ServiceConnectEvent]
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onServiceConnected(serviceConnectEvent: ServiceConnectEvent) {
        mSongsViewModel.firstStartService(this)

        mPlaybackListener = PlaybackListener()
        mSongsViewModel.binder!!.addPlaybackInfoListener(mPlaybackListener)
    }

    override fun onSupportNavigateUp(): Boolean {
        return mNavController.navigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                mNavController.navigateUp()
            }
            R.id.exit -> {
                mSongsViewModel.stopAndUnbindService(this)
            }
        }
        return true
    }

    override fun onDestroy() {
        mSongsViewModel.unbindService(this)
        mSongsViewModel.binder?.removePlaybackInfoListener(mPlaybackListener)
        super.onDestroy()
    }


    inner class PlaybackListener : PlaybackInfoListener() {

        override fun onLoadMedia(song: Song) {
            mSongsViewModel.onLoadMedia(this@MainActivity, song, mBinding.civAlbum as ImageView, 300)
        }

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
