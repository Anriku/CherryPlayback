package com.anriku.cherryplayback.model

/**
 * Created by anriku on 2018/11/16.
 */

class Slide {

    var code: Int = 0
    var data: DataBean? = null

    class DataBean {
        var slider: List<SliderBean>? = null
        var radioList: List<RadioListBean>? = null
        var songList: List<*>? = null

        class SliderBean {
            /**
             * linkUrl : https://y.qq.com/m/digitalbum/gold/index.html?openinqqmusic=1_video=true&id=4911397&g_f=shoujijiaodian
             * picUrl : http://y.gtimg.cn/music/common/upload/MUSIC_FOCUS/1077021.jpg
             * id : 18506
             */

            var linkUrl: String? = null
            var picUrl: String? = null
            var id: Int = 0
        }

        class RadioListBean {
            /**
             * picUrl : http://y.gtimg.cn/music/photo/radio/track_radio_199_13_1.jpg
             * Ftitle : 热歌
             * radioid : 199
             */

            var picUrl: String? = null
            var ftitle: String? = null
            var radioid: Int = 0
        }
    }
}
