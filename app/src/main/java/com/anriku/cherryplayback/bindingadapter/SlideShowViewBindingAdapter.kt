package com.anriku.cherryplayback.bindingadapter

import androidx.databinding.BindingAdapter
import com.anriku.cherryplayback.adapter.SlideViewAdapter
import com.anriku.cherryplayback.component.SlideShowView
import com.anriku.cherryplayback.model.Slide

/**
 * Created by anriku on 2018/11/16.
 */

object SlideShowViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("slides")
    fun setSlides(slideShowView: SlideShowView, slides: List<Slide.DataBean.SliderBean>) {
        slideShowView.slideShowViewAdapter = SlideViewAdapter(slides)
    }

}