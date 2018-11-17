package com.anriku.cherryplayback.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.model.Slide

/**
 * [SlideShowView] is used to display the slides.
 *
 *
 * Created by anriku on 2018/11/16.
 */
class SlideShowView : FrameLayout {

    companion object {
        private const val TAG = "SlideShowView"

        const val DEFAULT_UPDATE_INTERVAL = 4000
    }

    private lateinit var mViewPager: ViewPager
    private lateinit var mDotsView: DotsView
    private lateinit var mViewpagerAdapter: ViewPagerAdapter
    private var mUpdateTask: Runnable? = null
    private var mUpdateInterval: Int = DEFAULT_UPDATE_INTERVAL

    var slideShowViewAdapter: Adapter? = null
        set(value) {
            field = value
            // 添加ViewPager前先清理原有View
            removeAllViews()

            slideShowViewAdapter?.let {
                setViewPager(it)

                if (slideShowViewAdapter?.getSlides()?.size ?: 0 > 0) {
                    cleanSlideTask()
                    setSlideTask()
                }
            }
        }


    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, R.attr.SlideShowViewStyle) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlideShowView, defStyleAttr, 0)
        mUpdateInterval = typedArray.getInteger(R.styleable.SlideShowView_updateInterval, DEFAULT_UPDATE_INTERVAL)
        typedArray.recycle()
    }


    /**
     * 设置ViewPager
     *
     * @param adapter [Adapter]
     */
    private fun setViewPager(adapter: Adapter) {
        mViewPager = ViewPager(context)
        mViewpagerAdapter = ViewPagerAdapter(context, adapter)
        mViewPager.adapter = mViewpagerAdapter
        val viewPagerParams = FrameLayout.LayoutParams(measuredWidth, measuredHeight)
        mViewPager.layoutParams = viewPagerParams

        // Let the dots scrolled when the ViewPager is scrolled.
        mViewPager.addOnPageChangeListener(object : SlideShowOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                adapter.setCurrentFocusDot(mDotsView, position)
            }
        })

        addView(mViewPager)
        mDotsView = adapter.addDotsView(this)

        val params = FrameLayout.LayoutParams(measuredWidth, measuredHeight / 10)
        params.topMargin = (measuredHeight * 9) / 10
        params.leftMargin = 0

        addView(mDotsView.apply {
            layoutParams = params
        })
    }


    /**
     * 设置轮播事件
     */
    fun setSlideTask() {
        if (mUpdateTask == null) {
            mUpdateTask = Runnable {
                var currentIndex = mViewPager.currentItem
                currentIndex = (currentIndex + 1) % mViewPager.childCount
                mViewPager.setCurrentItem(currentIndex, true)
                postDelayed(mUpdateTask, mUpdateInterval.toLong())
            }
        }
        postDelayed(mUpdateTask, mUpdateInterval.toLong())
    }

    /**
     * 清除轮播事件
     */
    fun cleanSlideTask() {
        removeCallbacks(mUpdateTask)
        mUpdateTask = null
    }


    interface Adapter {

        fun getSlides(): List<Slide.DataBean.SliderBean>

        /**
         * This function is used to add specific dots.
         * @param container [SlideShowView]
         */
        fun addDotsView(container: ViewGroup): DotsView

        /**
         * This function is used to set the current focused dot.
         *
         * @param dotsView the dotsView
         * @param position The position of the dot.
         */
        fun setCurrentFocusDot(dotsView: DotsView, position: Int)

        @LayoutRes
        fun getItemLayout(): Int

        fun setSlideShowContent(view: View, itemViewInfo: Slide.DataBean.SliderBean)

    }


    abstract class SlideShowOnPageChangeListener : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
        }
    }


    class ViewPagerAdapter(private val mContext: Context, private val slideShowViewAdapter: Adapter) : PagerAdapter() {

        private val mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)

        override fun isViewFromObject(view: View, any: Any): Boolean {
            return view === any
        }

        override fun getCount(): Int {
            return slideShowViewAdapter.getSlides().size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            var itemView = container.getChildAt(position)
            val itemViewInfo = slideShowViewAdapter.getSlides()[position]

            if (itemView == null) {
                itemView = mLayoutInflater.inflate(slideShowViewAdapter.getItemLayout(), container, false)
                slideShowViewAdapter.setSlideShowContent(itemView, itemViewInfo)
                container.addView(itemView)
            }

            return itemView
        }

        override fun destroyItem(container: ViewGroup, position: Int, any: Any) {}

    }


    /**
     * The View which implement SimpleDotsView should extends the [DotsView].
     */
    abstract class DotsView : View {

        constructor(context: Context?) : super(context)
        constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
        constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
                super(context, attrs, defStyleAttr)

        /**
         * This function is used to set the focus dot.
         * @param position the position of the focus dot.
         */
        abstract fun setCurrentFocusDot(position: Int)

    }

}