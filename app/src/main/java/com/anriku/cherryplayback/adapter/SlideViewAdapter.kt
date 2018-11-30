package com.anriku.cherryplayback.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.anriku.cherryplayback.R
import com.anriku.cherryplayback.component.SimpleDotsView
import com.anriku.cherryplayback.component.SlideShowView
import com.anriku.cherryplayback.model.Slide
import com.anriku.cherryplayback.ui.musicroom.SlideDetailFragment
import com.bumptech.glide.Glide

/**
 * Created by anriku on 2018/11/16.
 */

class SlideViewAdapter(private val mSlides: List<Slide.DataBean.SliderBean>) : SlideShowView.Adapter {

    override fun getSlides(): List<Slide.DataBean.SliderBean> = mSlides

    override fun addDotsView(container: ViewGroup): SlideShowView.DotsView {
        return SimpleDotsView(container.context).apply {
            dotsSize = mSlides.size
        }
    }

    override fun setCurrentFocusDot(dotsView: SlideShowView.DotsView, position: Int) {
        dotsView.setCurrentFocusDot(position)
    }

    override fun getItemLayout(): Int = R.layout.slide_view_item

    override fun setSlideShowContent(view: View, itemViewInfo: Slide.DataBean.SliderBean) {
        Glide.with(view).load(itemViewInfo.picUrl).into(view as ImageView)

        view.setOnClickListener {
            val bundle = bundleOf(SlideDetailFragment.URL to itemViewInfo.linkUrl)
            it.findNavController().navigate(R.id.action_main_container_fragment_to_slideFragment, bundle)
        }
    }
}