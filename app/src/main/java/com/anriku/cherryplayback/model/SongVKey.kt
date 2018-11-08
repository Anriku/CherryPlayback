package com.anriku.cherryplayback.model

/**
 * Created by anriku on 2018/11/8.
 */

class SongVKey {

    /**
     * code : 0
     * cid : 205361747
     * userip : 223.104.251.28
     * data : {"expiration":80400,"items":[{"subcode":0,"songmid":"003OUlho2HcRHC","filename":"C400003OUlho2HcRHC.m4a","vkey":"D3E8FE05E636FD5F113128EB7690E54C0283134B37D1DC492E6C2F6AC1B5BBFED4ABB572A0EF24CA51D2C8D201F2B12AB35E2DF0F213352E"}]}
     */

    var code: Int = 0
    var cid: Int = 0
    var userip: String? = null
    var data: DataBean? = null

    class DataBean {
        /**
         * expiration : 80400
         * items : [{"subcode":0,"songmid":"003OUlho2HcRHC","filename":"C400003OUlho2HcRHC.m4a","vkey":"D3E8FE05E636FD5F113128EB7690E54C0283134B37D1DC492E6C2F6AC1B5BBFED4ABB572A0EF24CA51D2C8D201F2B12AB35E2DF0F213352E"}]
         */

        var expiration: Int = 0
        var items: List<ItemsBean>? = null

        class ItemsBean {
            /**
             * subcode : 0
             * songmid : 003OUlho2HcRHC
             * filename : C400003OUlho2HcRHC.m4a
             * vkey : D3E8FE05E636FD5F113128EB7690E54C0283134B37D1DC492E6C2F6AC1B5BBFED4ABB572A0EF24CA51D2C8D201F2B12AB35E2DF0F213352E
             */

            var subcode: Int = 0
            var songmid: String? = null
            var filename: String? = null
            var vkey: String? = null
        }
    }
}
