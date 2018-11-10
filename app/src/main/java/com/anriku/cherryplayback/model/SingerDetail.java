package com.anriku.cherryplayback.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anriku on 2018/11/9.
 */

public class SingerDetail {

    private String code;
    private DataBean data;
    private String message;
    private String subcode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubcode() {
        return subcode;
    }

    public void setSubcode(String subcode) {
        this.subcode = subcode;
    }

    public static class DataBean {

        private String singer_id;
        private String singer_mid;
        private String singer_name;
        private String total;
        private List<ListBean> list;

        public String getSinger_id() {
            return singer_id;
        }

        public void setSinger_id(String singer_id) {
            this.singer_id = singer_id;
        }

        public String getSinger_mid() {
            return singer_mid;
        }

        public void setSinger_mid(String singer_mid) {
            this.singer_mid = singer_mid;
        }

        public String getSinger_name() {
            return singer_name;
        }

        public void setSinger_name(String singer_name) {
            this.singer_name = singer_name;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {

            private String Flisten_count1;
            private String Fupload_time;
            private String index;
            private String isnew;
            private String listenCount;
            private MusicDataBean musicData;
            private String playurl;
            private String price;
            private VidBean vid;

            public String getFlisten_count1() {
                return Flisten_count1;
            }

            public void setFlisten_count1(String Flisten_count1) {
                this.Flisten_count1 = Flisten_count1;
            }

            public String getFupload_time() {
                return Fupload_time;
            }

            public void setFupload_time(String Fupload_time) {
                this.Fupload_time = Fupload_time;
            }

            public String getIndex() {
                return index;
            }

            public void setIndex(String index) {
                this.index = index;
            }

            public String getIsnew() {
                return isnew;
            }

            public void setIsnew(String isnew) {
                this.isnew = isnew;
            }

            public String getListenCount() {
                return listenCount;
            }

            public void setListenCount(String listenCount) {
                this.listenCount = listenCount;
            }

            public MusicDataBean getMusicData() {
                return musicData;
            }

            public void setMusicData(MusicDataBean musicData) {
                this.musicData = musicData;
            }

            public String getPlayurl() {
                return playurl;
            }

            public void setPlayurl(String playurl) {
                this.playurl = playurl;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public VidBean getVid() {
                return vid;
            }

            public void setVid(VidBean vid) {
                this.vid = vid;
            }

            public static class MusicDataBean {
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

                private String albumdesc;
                private String albumid;
                private String albummid;
                private String albumname;
                private String alertid;
                private String belongCD;
                private String cdIdx;
                private String Stringerval;
                private String isonly;
                private String label;
                private String msgid;
                private PayBean pay;
                private PreviewBean preview;
                private String rate;
                private String size128;
                private String size320;
                private String size5_1;
                private String sizeape;
                private String sizeflac;
                private String sizeogg;
                private String songid;
                private String songmid;
                private String songname;
                private String songorig;
                private String songtype;
                private String strMediaMid;
                private String stream;
                @SerializedName("switch")
                private String switchX;
                private String type;
                private String vid;
                private List<SingerBean> singer;

                public String getAlbumdesc() {
                    return albumdesc;
                }

                public void setAlbumdesc(String albumdesc) {
                    this.albumdesc = albumdesc;
                }

                public String getAlbumid() {
                    return albumid;
                }

                public void setAlbumid(String albumid) {
                    this.albumid = albumid;
                }

                public String getAlbummid() {
                    return albummid;
                }

                public void setAlbummid(String albummid) {
                    this.albummid = albummid;
                }

                public String getAlbumname() {
                    return albumname;
                }

                public void setAlbumname(String albumname) {
                    this.albumname = albumname;
                }

                public String getAlertid() {
                    return alertid;
                }

                public void setAlertid(String alertid) {
                    this.alertid = alertid;
                }

                public String getBelongCD() {
                    return belongCD;
                }

                public void setBelongCD(String belongCD) {
                    this.belongCD = belongCD;
                }

                public String getCdIdx() {
                    return cdIdx;
                }

                public void setCdIdx(String cdIdx) {
                    this.cdIdx = cdIdx;
                }

                public String getInterval() {
                    return Stringerval;
                }

                public void setInterval(String Stringerval) {
                    this.Stringerval = Stringerval;
                }

                public String getIsonly() {
                    return isonly;
                }

                public void setIsonly(String isonly) {
                    this.isonly = isonly;
                }

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public String getMsgid() {
                    return msgid;
                }

                public void setMsgid(String msgid) {
                    this.msgid = msgid;
                }

                public PayBean getPay() {
                    return pay;
                }

                public void setPay(PayBean pay) {
                    this.pay = pay;
                }

                public PreviewBean getPreview() {
                    return preview;
                }

                public void setPreview(PreviewBean preview) {
                    this.preview = preview;
                }

                public String getRate() {
                    return rate;
                }

                public void setRate(String rate) {
                    this.rate = rate;
                }

                public String getSize128() {
                    return size128;
                }

                public void setSize128(String size128) {
                    this.size128 = size128;
                }

                public String getSize320() {
                    return size320;
                }

                public void setSize320(String size320) {
                    this.size320 = size320;
                }

                public String getSize5_1() {
                    return size5_1;
                }

                public void setSize5_1(String size5_1) {
                    this.size5_1 = size5_1;
                }

                public String getSizeape() {
                    return sizeape;
                }

                public void setSizeape(String sizeape) {
                    this.sizeape = sizeape;
                }

                public String getSizeflac() {
                    return sizeflac;
                }

                public void setSizeflac(String sizeflac) {
                    this.sizeflac = sizeflac;
                }

                public String getSizeogg() {
                    return sizeogg;
                }

                public void setSizeogg(String sizeogg) {
                    this.sizeogg = sizeogg;
                }

                public String getSongid() {
                    return songid;
                }

                public void setSongid(String songid) {
                    this.songid = songid;
                }

                public String getSongmid() {
                    return songmid;
                }

                public void setSongmid(String songmid) {
                    this.songmid = songmid;
                }

                public String getSongname() {
                    return songname;
                }

                public void setSongname(String songname) {
                    this.songname = songname;
                }

                public String getSongorig() {
                    return songorig;
                }

                public void setSongorig(String songorig) {
                    this.songorig = songorig;
                }

                public String getSongtype() {
                    return songtype;
                }

                public void setSongtype(String songtype) {
                    this.songtype = songtype;
                }

                public String getStrMediaMid() {
                    return strMediaMid;
                }

                public void setStrMediaMid(String strMediaMid) {
                    this.strMediaMid = strMediaMid;
                }

                public String getStream() {
                    return stream;
                }

                public void setStream(String stream) {
                    this.stream = stream;
                }

                public String getSwitchX() {
                    return switchX;
                }

                public void setSwitchX(String switchX) {
                    this.switchX = switchX;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getVid() {
                    return vid;
                }

                public void setVid(String vid) {
                    this.vid = vid;
                }

                public List<SingerBean> getSinger() {
                    return singer;
                }

                public void setSinger(List<SingerBean> singer) {
                    this.singer = singer;
                }

                public static class PayBean {
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

                    private String payalbum;
                    private String payalbumprice;
                    private String paydownload;
                    private String payinfo;
                    private String payplay;
                    private String paytrackmouth;
                    private String paytrackprice;
                    private String timefree;

                    public String getPayalbum() {
                        return payalbum;
                    }

                    public void setPayalbum(String payalbum) {
                        this.payalbum = payalbum;
                    }

                    public String getPayalbumprice() {
                        return payalbumprice;
                    }

                    public void setPayalbumprice(String payalbumprice) {
                        this.payalbumprice = payalbumprice;
                    }

                    public String getPaydownload() {
                        return paydownload;
                    }

                    public void setPaydownload(String paydownload) {
                        this.paydownload = paydownload;
                    }

                    public String getPayinfo() {
                        return payinfo;
                    }

                    public void setPayinfo(String payinfo) {
                        this.payinfo = payinfo;
                    }

                    public String getPayplay() {
                        return payplay;
                    }

                    public void setPayplay(String payplay) {
                        this.payplay = payplay;
                    }

                    public String getPaytrackmouth() {
                        return paytrackmouth;
                    }

                    public void setPaytrackmouth(String paytrackmouth) {
                        this.paytrackmouth = paytrackmouth;
                    }

                    public String getPaytrackprice() {
                        return paytrackprice;
                    }

                    public void setPaytrackprice(String paytrackprice) {
                        this.paytrackprice = paytrackprice;
                    }

                    public String getTimefree() {
                        return timefree;
                    }

                    public void setTimefree(String timefree) {
                        this.timefree = timefree;
                    }
                }

                public static class PreviewBean {
                    /**
                     * trybegin : 65138
                     * tryend : 85421
                     * trysize : 0
                     */

                    private String trybegin;
                    private String tryend;
                    private String trysize;

                    public String getTrybegin() {
                        return trybegin;
                    }

                    public void setTrybegin(String trybegin) {
                        this.trybegin = trybegin;
                    }

                    public String getTryend() {
                        return tryend;
                    }

                    public void setTryend(String tryend) {
                        this.tryend = tryend;
                    }

                    public String getTrysize() {
                        return trysize;
                    }

                    public void setTrysize(String trysize) {
                        this.trysize = trysize;
                    }
                }

                public static class SingerBean {
                    /**
                     * id : 4558
                     * mid : 0025NhlN2yWrP4
                     * name : 周杰伦
                     */

                    private String id;
                    private String mid;
                    private String name;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getMid() {
                        return mid;
                    }

                    public void setMid(String mid) {
                        this.mid = mid;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }
                }
            }

            public static class VidBean {
                /**
                 * Fmv_id : 1053277
                 * Fstatus : 1
                 * Fvid : u00222le4ox
                 */

                private String Fmv_id;
                private String Fstatus;
                private String Fvid;

                public String getFmv_id() {
                    return Fmv_id;
                }

                public void setFmv_id(String Fmv_id) {
                    this.Fmv_id = Fmv_id;
                }

                public String getFstatus() {
                    return Fstatus;
                }

                public void setFstatus(String Fstatus) {
                    this.Fstatus = Fstatus;
                }

                public String getFvid() {
                    return Fvid;
                }

                public void setFvid(String Fvid) {
                    this.Fvid = Fvid;
                }
            }
        }
    }
}
