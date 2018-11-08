package com.anriku.cherryplayback.model

import com.google.gson.annotations.SerializedName

/**
 * Created by anriku on 2018/11/8.
 */

class SingerDetail {

    var singer_id: String? = null
    var singer_mid: String? = null
    var singer_name: String? = null
    var total: Int = 0
    var list: List<ListBean>? = null

    class ListBean {
        /**
         * Flisten_count1 : 0
         * Fupload_time : 2016-06-06 16:21:17
         * index : 1
         * isnew : 0
         * listenCount : 0
         * musicData : {"albumdesc":"","albumid":1458791,"albummid":"003RMaRI1iFoYd","albumname":"周杰伦的床边故事","alertid":2,"belongCD":8,"cdIdx":0,"interval":215,"isonly":1,"label":"4611686018435776513","msgid":14,"pay":{"payalbum":1,"payalbumprice":2000,"paydownload":1,"payinfo":1,"payplay":0,"paytrackmouth":1,"paytrackprice":200,"timefree":0},"preview":{"trybegin":65138,"tryend":85421,"trysize":0},"rate":31,"singer":[{"id":4558,"mid":"0025NhlN2yWrP4","name":"周杰伦"}],"size128":3443771,"size320":8608939,"size5_1":0,"sizeape":24929083,"sizeflac":24971563,"sizeogg":5001304,"songid":107192078,"songmid":"003OUlho2HcRHC","songname":"告白气球","songorig":"告白气球","songtype":0,"strMediaMid":"003OUlho2HcRHC","stream":13,"switch":17413891,"type":0,"vid":"u00222le4ox"}
         * playurl :
         * price : 320
         * vid : {"Fmv_id":"1053277","Fstatus":"1","Fvid":"u00222le4ox"}
         */

        var flisten_count1: Int = 0
        var fupload_time: String? = null
        var index: Int = 0
        var isnew: Int = 0
        var listenCount: Int = 0
        var musicData: MusicDataBean? = null
        var playurl: String? = null
        var price: Int = 0
        var vid: VidBean? = null

        class MusicDataBean {
            /**
             * albumdesc :
             * albumid : 1458791
             * albummid : 003RMaRI1iFoYd
             * albumname : 周杰伦的床边故事
             * alertid : 2
             * belongCD : 8
             * cdIdx : 0
             * interval : 215
             * isonly : 1
             * label : 4611686018435776513
             * msgid : 14
             * pay : {"payalbum":1,"payalbumprice":2000,"paydownload":1,"payinfo":1,"payplay":0,"paytrackmouth":1,"paytrackprice":200,"timefree":0}
             * preview : {"trybegin":65138,"tryend":85421,"trysize":0}
             * rate : 31
             * singer : [{"id":4558,"mid":"0025NhlN2yWrP4","name":"周杰伦"}]
             * size128 : 3443771
             * size320 : 8608939
             * size5_1 : 0
             * sizeape : 24929083
             * sizeflac : 24971563
             * sizeogg : 5001304
             * songid : 107192078
             * songmid : 003OUlho2HcRHC
             * songname : 告白气球
             * songorig : 告白气球
             * songtype : 0
             * strMediaMid : 003OUlho2HcRHC
             * stream : 13
             * switch : 17413891
             * type : 0
             * vid : u00222le4ox
             */

            var albumdesc: String? = null
            var albumid: Int = 0
            var albummid: String? = null
            var albumname: String? = null
            var alertid: Int = 0
            var belongCD: Int = 0
            var cdIdx: Int = 0
            var interval: Int = 0
            var isonly: Int = 0
            var label: String? = null
            var msgid: Int = 0
            var pay: PayBean? = null
            var preview: PreviewBean? = null
            var rate: Int = 0
            var size128: Int = 0
            var size320: Int = 0
            var size5_1: Int = 0
            var sizeape: Int = 0
            var sizeflac: Int = 0
            var sizeogg: Int = 0
            var songid: Int = 0
            var songmid: String? = null
            var songname: String? = null
            var songorig: String? = null
            var songtype: Int = 0
            var strMediaMid: String? = null
            var stream: Int = 0
            @SerializedName("switch")
            var switchX: Int = 0
            var type: Int = 0
            var vid: String? = null
            var singer: List<SingerBean>? = null

            class PayBean {
                /**
                 * payalbum : 1
                 * payalbumprice : 2000
                 * paydownload : 1
                 * payinfo : 1
                 * payplay : 0
                 * paytrackmouth : 1
                 * paytrackprice : 200
                 * timefree : 0
                 */

                var payalbum: Int = 0
                var payalbumprice: Int = 0
                var paydownload: Int = 0
                var payinfo: Int = 0
                var payplay: Int = 0
                var paytrackmouth: Int = 0
                var paytrackprice: Int = 0
                var timefree: Int = 0
            }

            class PreviewBean {
                /**
                 * trybegin : 65138
                 * tryend : 85421
                 * trysize : 0
                 */

                var trybegin: Int = 0
                var tryend: Int = 0
                var trysize: Int = 0
            }

            class SingerBean {
                /**
                 * id : 4558
                 * mid : 0025NhlN2yWrP4
                 * name : 周杰伦
                 */

                var id: Int = 0
                var mid: String? = null
                var name: String? = null
            }
        }

        class VidBean {
            /**
             * Fmv_id : 1053277
             * Fstatus : 1
             * Fvid : u00222le4ox
             */

            var fmv_id: String? = null
            var fstatus: String? = null
            var fvid: String? = null
        }
    }

}
