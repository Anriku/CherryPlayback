package com.anriku.cherryplayback.model;

import java.util.List;

/**
 * Created by anriku on 2018/11/18.
 */

public class RecommendPlaylist {

    private RecomPlaylistBean recomPlaylist;
    private int code;
    private long ts;

    public RecomPlaylistBean getRecomPlaylist() {
        return recomPlaylist;
    }

    public void setRecomPlaylist(RecomPlaylistBean recomPlaylist) {
        this.recomPlaylist = recomPlaylist;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public static class RecomPlaylistBean {
        /**
         * data : {"page":0,"v_hot":[{"album_pic_mid":"","content_id":5510591024,"cover":"http://p.qpic.cn/music_cover/ibSiagqKjw1zfTgxY7F8CfEyCaJ7CCLTe65h53Gv4A88B6gwt25kZIicA/300?n=1","creator":3562963616,"edge_mark":"http://y.gtimg.cn/music/common/upload/hot_recommend_item/215957.png","id":1699,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":27433,"pic_mid":"","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"每日新歌：可龙宝温柔治愈整个冬日","tjreport":"","type":10014,"username":"QQ音乐新歌推荐"},{"album_pic_mid":"001lffwo4cqVjr","content_id":5561291252,"cover":"http://p.qpic.cn/music_cover/JBDCVgqXWXaYUvcsElqcicUaAjicbDWBz3HjyCcDo6m3fdwry2X2VHCQ/300?n=1","creator":3414308253,"edge_mark":"","id":1704,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":3547,"pic_mid":"","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"成长助推剂｜愿你成为你自己","tjreport":"","type":10014,"username":"鹅猴综艺狸"},{"album_pic_mid":"","content_id":5557152027,"cover":"http://p.qpic.cn/music_cover/ESQkDB4ohgibFpRT23xHVFXDQaR1pxyIhLicPRvTxczOUichichRTWIU6A/300?n=1","creator":1283432642,"edge_mark":"","id":0,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":115417,"pic_mid":"","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"达人周末 | 最喜欢的漫威电影音乐","tjreport":"","type":10014,"username":"达人周末"},{"album_pic_mid":"002zGuIB3jHDfd","content_id":5521559198,"cover":"http://p.qpic.cn/music_cover/R8unPZMA4Vp5v2Ms96bst9ayqKJwRSEBxsLMyTfRZC2sOwBIV7ajXw/300?n=1","creator":945187827,"edge_mark":"","id":0,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":221228,"pic_mid":"","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"温柔女声：撩动心弦100%","tjreport":"","type":10014,"username":"念安娜"},{"album_pic_mid":"004N7dsZ2RKl2t","content_id":5486351061,"cover":"http://p.qpic.cn/music_cover/x9DLyh0c10r7JMNsHc4d2B4YuXnA6zvhkFMFDXYcbjvG6Lb2Uy9PIw/300?n=1","creator":3226975720,"edge_mark":"","id":0,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":146773,"pic_mid":"","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"《风味人间》纪录片原声大碟","tjreport":"","type":10014,"username":"Sugarbling。Ada"},{"album_pic_mid":"000MISZa1VWIJe","content_id":4942168639,"cover":"http://p.qpic.cn/music_cover/Xh9w3XT51TT9sbShMd8OrNicGa1lVqvl04LbYnN8ib4pPZyW1yl87dVg/300?n=1","creator":3313854020,"edge_mark":"","id":0,"is_dj":false,"is_vip":false,"jump_url":"","listen_num":29608,"pic_mid":"0031yGd44NfOPE","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"李大奔！从美院走出的黑马少侠","tjreport":"","type":10014,"username":"万利达李宗盛"},{"album_pic_mid":"","content_id":5109655439,"cover":"http://p.qpic.cn/music_cover/uXtIBAxaiaVfmdYhOibK1Dg2gyP7YicBRic6QefVTyNmXk59kXHn7TLJicw/300?n=1","creator":482219519,"edge_mark":"","id":0,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":11495,"pic_mid":"001RnwZm49Uuzy","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"冷峻中盛开 | 苏格兰摇滚","tjreport":"","type":10014,"username":"K先生的猫"},{"album_pic_mid":"","content_id":5485947352,"cover":"http://p.qpic.cn/music_cover/x9DLyh0c10r7JMNsHc4d2B4YuXnA6zvhrfRGmsI5e0iacRCcfpG03Gw/300?n=1","creator":3226975720,"edge_mark":"","id":0,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":227239,"pic_mid":"","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"入围金马奖的原创独立音乐","tjreport":"","type":10014,"username":"Sugarbling。Ada"},{"album_pic_mid":"004N0RdU1APRzG","content_id":5538465260,"cover":"http://p.qpic.cn/music_cover/qhuJFHlwiayRp1rhWoJk9Hc1EnJIPTGKEib2NwZqA3Xiak02g3LTomyzw/300?n=1","creator":3413833718,"edge_mark":"","id":0,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":427962,"pic_mid":"","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"暖心向民谣：给予掌心的一抹温度","tjreport":"","type":10014,"username":"腾讯音乐人"},{"album_pic_mid":"000AiYsg0O5nut","content_id":4518625246,"cover":"http://p.qpic.cn/music_cover/Biax4WTSMic4N0bgPWDwUCs4dlnGJibB8vm68QSlwOQM2RBQYH8KU86bA/300?n=1","creator":1245280330,"edge_mark":"","id":0,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":62428,"pic_mid":"003GV7aD07w8Yd","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"致冷剂 · Coldwave","tjreport":"","type":10014,"username":"花痞"},{"album_pic_mid":"","content_id":5507359456,"cover":"http://p.qpic.cn/music_cover/Ln2hcJrJkibdW1MpKCmHtuQDyxhP3uibiaurTiaP3yWH4AUJ2iaY3MjD2Kg/300?n=1","creator":2726942483,"edge_mark":"","id":0,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":78847,"pic_mid":"","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"你敢挑战这些古风高音吗","tjreport":"","type":10014,"username":"忘忧熙月"},{"album_pic_mid":"001j2IHy2CkIaz","content_id":5233725423,"cover":"http://p.qpic.cn/music_cover/SP5Xwt3FodK2djBqtvKArIRep6J6HNBTriclnCTZpxfxwvNlwYab1nw/300?n=1","creator":2900245782,"edge_mark":"","id":0,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":164762,"pic_mid":"0020RjQY1DLPhd","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"宅女大扫除の清新动力源泉","tjreport":"","type":10014,"username":"胖虎御用铲屎官"}]}
         * code : 0
         */

        private DataBean data;
        private int code;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public static class DataBean {
            /**
             * page : 0
             * v_hot : [{"album_pic_mid":"","content_id":5510591024,"cover":"http://p.qpic.cn/music_cover/ibSiagqKjw1zfTgxY7F8CfEyCaJ7CCLTe65h53Gv4A88B6gwt25kZIicA/300?n=1","creator":3562963616,"edge_mark":"http://y.gtimg.cn/music/common/upload/hot_recommend_item/215957.png","id":1699,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":27433,"pic_mid":"","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"每日新歌：可龙宝温柔治愈整个冬日","tjreport":"","type":10014,"username":"QQ音乐新歌推荐"},{"album_pic_mid":"001lffwo4cqVjr","content_id":5561291252,"cover":"http://p.qpic.cn/music_cover/JBDCVgqXWXaYUvcsElqcicUaAjicbDWBz3HjyCcDo6m3fdwry2X2VHCQ/300?n=1","creator":3414308253,"edge_mark":"","id":1704,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":3547,"pic_mid":"","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"成长助推剂｜愿你成为你自己","tjreport":"","type":10014,"username":"鹅猴综艺狸"},{"album_pic_mid":"","content_id":5557152027,"cover":"http://p.qpic.cn/music_cover/ESQkDB4ohgibFpRT23xHVFXDQaR1pxyIhLicPRvTxczOUichichRTWIU6A/300?n=1","creator":1283432642,"edge_mark":"","id":0,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":115417,"pic_mid":"","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"达人周末 | 最喜欢的漫威电影音乐","tjreport":"","type":10014,"username":"达人周末"},{"album_pic_mid":"002zGuIB3jHDfd","content_id":5521559198,"cover":"http://p.qpic.cn/music_cover/R8unPZMA4Vp5v2Ms96bst9ayqKJwRSEBxsLMyTfRZC2sOwBIV7ajXw/300?n=1","creator":945187827,"edge_mark":"","id":0,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":221228,"pic_mid":"","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"温柔女声：撩动心弦100%","tjreport":"","type":10014,"username":"念安娜"},{"album_pic_mid":"004N7dsZ2RKl2t","content_id":5486351061,"cover":"http://p.qpic.cn/music_cover/x9DLyh0c10r7JMNsHc4d2B4YuXnA6zvhkFMFDXYcbjvG6Lb2Uy9PIw/300?n=1","creator":3226975720,"edge_mark":"","id":0,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":146773,"pic_mid":"","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"《风味人间》纪录片原声大碟","tjreport":"","type":10014,"username":"Sugarbling。Ada"},{"album_pic_mid":"000MISZa1VWIJe","content_id":4942168639,"cover":"http://p.qpic.cn/music_cover/Xh9w3XT51TT9sbShMd8OrNicGa1lVqvl04LbYnN8ib4pPZyW1yl87dVg/300?n=1","creator":3313854020,"edge_mark":"","id":0,"is_dj":false,"is_vip":false,"jump_url":"","listen_num":29608,"pic_mid":"0031yGd44NfOPE","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"李大奔！从美院走出的黑马少侠","tjreport":"","type":10014,"username":"万利达李宗盛"},{"album_pic_mid":"","content_id":5109655439,"cover":"http://p.qpic.cn/music_cover/uXtIBAxaiaVfmdYhOibK1Dg2gyP7YicBRic6QefVTyNmXk59kXHn7TLJicw/300?n=1","creator":482219519,"edge_mark":"","id":0,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":11495,"pic_mid":"001RnwZm49Uuzy","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"冷峻中盛开 | 苏格兰摇滚","tjreport":"","type":10014,"username":"K先生的猫"},{"album_pic_mid":"","content_id":5485947352,"cover":"http://p.qpic.cn/music_cover/x9DLyh0c10r7JMNsHc4d2B4YuXnA6zvhrfRGmsI5e0iacRCcfpG03Gw/300?n=1","creator":3226975720,"edge_mark":"","id":0,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":227239,"pic_mid":"","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"入围金马奖的原创独立音乐","tjreport":"","type":10014,"username":"Sugarbling。Ada"},{"album_pic_mid":"004N0RdU1APRzG","content_id":5538465260,"cover":"http://p.qpic.cn/music_cover/qhuJFHlwiayRp1rhWoJk9Hc1EnJIPTGKEib2NwZqA3Xiak02g3LTomyzw/300?n=1","creator":3413833718,"edge_mark":"","id":0,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":427962,"pic_mid":"","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"暖心向民谣：给予掌心的一抹温度","tjreport":"","type":10014,"username":"腾讯音乐人"},{"album_pic_mid":"000AiYsg0O5nut","content_id":4518625246,"cover":"http://p.qpic.cn/music_cover/Biax4WTSMic4N0bgPWDwUCs4dlnGJibB8vm68QSlwOQM2RBQYH8KU86bA/300?n=1","creator":1245280330,"edge_mark":"","id":0,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":62428,"pic_mid":"003GV7aD07w8Yd","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"致冷剂 · Coldwave","tjreport":"","type":10014,"username":"花痞"},{"album_pic_mid":"","content_id":5507359456,"cover":"http://p.qpic.cn/music_cover/Ln2hcJrJkibdW1MpKCmHtuQDyxhP3uibiaurTiaP3yWH4AUJ2iaY3MjD2Kg/300?n=1","creator":2726942483,"edge_mark":"","id":0,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":78847,"pic_mid":"","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"你敢挑战这些古风高音吗","tjreport":"","type":10014,"username":"忘忧熙月"},{"album_pic_mid":"001j2IHy2CkIaz","content_id":5233725423,"cover":"http://p.qpic.cn/music_cover/SP5Xwt3FodK2djBqtvKArIRep6J6HNBTriclnCTZpxfxwvNlwYab1nw/300?n=1","creator":2900245782,"edge_mark":"","id":0,"is_dj":false,"is_vip":true,"jump_url":"","listen_num":164762,"pic_mid":"0020RjQY1DLPhd","rcmdcontent":"","rcmdtemplate":"编辑推荐","rcmdtype":0,"singerid":0,"title":"宅女大扫除の清新动力源泉","tjreport":"","type":10014,"username":"胖虎御用铲屎官"}]
             */

            private int page;
            private List<VHotBean> v_hot;

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public List<VHotBean> getV_hot() {
                return v_hot;
            }

            public void setV_hot(List<VHotBean> v_hot) {
                this.v_hot = v_hot;
            }

            public static class VHotBean {
                /**
                 * album_pic_mid :
                 * content_id : 5510591024
                 * cover : http://p.qpic.cn/music_cover/ibSiagqKjw1zfTgxY7F8CfEyCaJ7CCLTe65h53Gv4A88B6gwt25kZIicA/300?n=1
                 * creator : 3562963616
                 * edge_mark : http://y.gtimg.cn/music/common/upload/hot_recommend_item/215957.png
                 * id : 1699
                 * is_dj : false
                 * is_vip : true
                 * jump_url :
                 * listen_num : 27433
                 * pic_mid :
                 * rcmdcontent :
                 * rcmdtemplate : 编辑推荐
                 * rcmdtype : 0
                 * singerid : 0
                 * title : 每日新歌：可龙宝温柔治愈整个冬日
                 * tjreport :
                 * type : 10014
                 * username : QQ音乐新歌推荐
                 */

                private String album_pic_mid;
                private long content_id;
                private String cover;
                private long creator;
                private String edge_mark;
                private int id;
                private boolean is_dj;
                private boolean is_vip;
                private String jump_url;
                private int listen_num;
                private String pic_mid;
                private String rcmdcontent;
                private String rcmdtemplate;
                private int rcmdtype;
                private int singerid;
                private String title;
                private String tjreport;
                private int type;
                private String username;

                public String getAlbum_pic_mid() {
                    return album_pic_mid;
                }

                public void setAlbum_pic_mid(String album_pic_mid) {
                    this.album_pic_mid = album_pic_mid;
                }

                public long getContent_id() {
                    return content_id;
                }

                public void setContent_id(long content_id) {
                    this.content_id = content_id;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public long getCreator() {
                    return creator;
                }

                public void setCreator(long creator) {
                    this.creator = creator;
                }

                public String getEdge_mark() {
                    return edge_mark;
                }

                public void setEdge_mark(String edge_mark) {
                    this.edge_mark = edge_mark;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public boolean isIs_dj() {
                    return is_dj;
                }

                public void setIs_dj(boolean is_dj) {
                    this.is_dj = is_dj;
                }

                public boolean isIs_vip() {
                    return is_vip;
                }

                public void setIs_vip(boolean is_vip) {
                    this.is_vip = is_vip;
                }

                public String getJump_url() {
                    return jump_url;
                }

                public void setJump_url(String jump_url) {
                    this.jump_url = jump_url;
                }

                public int getListen_num() {
                    return listen_num;
                }

                public void setListen_num(int listen_num) {
                    this.listen_num = listen_num;
                }

                public String getPic_mid() {
                    return pic_mid;
                }

                public void setPic_mid(String pic_mid) {
                    this.pic_mid = pic_mid;
                }

                public String getRcmdcontent() {
                    return rcmdcontent;
                }

                public void setRcmdcontent(String rcmdcontent) {
                    this.rcmdcontent = rcmdcontent;
                }

                public String getRcmdtemplate() {
                    return rcmdtemplate;
                }

                public void setRcmdtemplate(String rcmdtemplate) {
                    this.rcmdtemplate = rcmdtemplate;
                }

                public int getRcmdtype() {
                    return rcmdtype;
                }

                public void setRcmdtype(int rcmdtype) {
                    this.rcmdtype = rcmdtype;
                }

                public int getSingerid() {
                    return singerid;
                }

                public void setSingerid(int singerid) {
                    this.singerid = singerid;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getTjreport() {
                    return tjreport;
                }

                public void setTjreport(String tjreport) {
                    this.tjreport = tjreport;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }
            }
        }
    }
}
