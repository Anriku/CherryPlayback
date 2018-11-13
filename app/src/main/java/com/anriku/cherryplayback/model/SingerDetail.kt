package com.anriku.cherryplayback.model

import com.google.gson.annotations.SerializedName

/**
 * Created by anriku on 2018/11/9.
 */

class SingerDetail {

    var code: String? = null
    var data: DataBean? = null
    var message: String? = null
    var subcode: String? = null

    class DataBean {

        var singer_id: String? = null
        var singer_mid: String? = null
        var singer_name: String? = null
        var total: String? = null
        var list: List<ListBean>? = null

        class ListBean {

            var flisten_count1: String? = null
            var fupload_time: String? = null
            var index: String? = null
            var isnew: String? = null
            var listenCount: String? = null
            var musicData: MusicDataBean? = null
            var playurl: String? = null
            var price: String? = null
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
                 * Stringerval : 215
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
                var albumid: String? = null
                var albummid: String? = null
                var albumname: String? = null
                var alertid: String? = null
                var belongCD: String? = null
                var cdIdx: String? = null
                var interval: String? = null
                var isonly: String? = null
                var label: String? = null
                var msgid: String? = null
                var pay: PayBean? = null
                var preview: PreviewBean? = null
                var rate: String? = null
                var size128: String? = null
                var size320: String? = null
                var size5_1: String? = null
                var sizeape: String? = null
                var sizeflac: String? = null
                var sizeogg: String? = null
                var songid: String? = null
                var songmid: String? = null
                var songname: String? = null
                var songorig: String? = null
                var songtype: String? = null
                var strMediaMid: String? = null
                var stream: String? = null
                @SerializedName("switch")
                var switchX: String? = null
                var type: String? = null
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

                    var payalbum: String? = null
                    var payalbumprice: String? = null
                    var paydownload: String? = null
                    var payinfo: String? = null
                    var payplay: String? = null
                    var paytrackmouth: String? = null
                    var paytrackprice: String? = null
                    var timefree: String? = null
                }

                class PreviewBean {
                    /**
                     * trybegin : 65138
                     * tryend : 85421
                     * trysize : 0
                     */

                    var trybegin: String? = null
                    var tryend: String? = null
                    var trysize: String? = null
                }

                class SingerBean {
                    /**
                     * id : 4558
                     * mid : 0025NhlN2yWrP4
                     * name : 周杰伦
                     */

                    var id: String? = null
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
}
