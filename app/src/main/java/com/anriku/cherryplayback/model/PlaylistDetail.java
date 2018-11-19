package com.anriku.cherryplayback.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anriku on 2018/11/18.
 */

public class PlaylistDetail {

    private int code;
    private int subcode;
    private int accessed_plaza_cache;
    private int accessed_favbase;
    private String login;
    private int cdnum;
    private int realcdnum;
    private List<CdlistBean> cdlist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getSubcode() {
        return subcode;
    }

    public void setSubcode(int subcode) {
        this.subcode = subcode;
    }

    public int getAccessed_plaza_cache() {
        return accessed_plaza_cache;
    }

    public void setAccessed_plaza_cache(int accessed_plaza_cache) {
        this.accessed_plaza_cache = accessed_plaza_cache;
    }

    public int getAccessed_favbase() {
        return accessed_favbase;
    }

    public void setAccessed_favbase(int accessed_favbase) {
        this.accessed_favbase = accessed_favbase;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getCdnum() {
        return cdnum;
    }

    public void setCdnum(int cdnum) {
        this.cdnum = cdnum;
    }

    public int getRealcdnum() {
        return realcdnum;
    }

    public void setRealcdnum(int realcdnum) {
        this.realcdnum = realcdnum;
    }

    public List<CdlistBean> getCdlist() {
        return cdlist;
    }

    public void setCdlist(List<CdlistBean> cdlist) {
        this.cdlist = cdlist;
    }

    public static class CdlistBean {


        private String disstid;
        private int dirid;
        private String coveradurl;
        private int dissid;
        private String login;
        private String uin;
        private String encrypt_uin;
        private int owndir;
        private String dissname;
        private String logo;
        private String pic_mid;
        private String album_pic_mid;
        private int pic_dpi;
        private int isAd;
        private String desc;
        private int ctime;
        private int mtime;
        private String headurl;
        private String ifpicurl;
        private String nick;
        private String nickname;
        private int type;
        private int singerid;
        private String singermid;
        private int isvip;
        private int isdj;
        private int songnum;
        private String songids;
        private String songtypes;
        private int disstype;
        private String dir_pic_url2;
        private int song_update_time;
        private int song_update_num;
        private int total_song_num;
        private int song_begin;
        private int cur_song_num;
        private int visitnum;
        private int cmtnum;
        private int buynum;
        private String scoreavage;
        private int scoreusercount;
        private List<TagsBean> tags;
        private List<SonglistBean> songlist;

        public String getDisstid() {
            return disstid;
        }

        public void setDisstid(String disstid) {
            this.disstid = disstid;
        }

        public int getDirid() {
            return dirid;
        }

        public void setDirid(int dirid) {
            this.dirid = dirid;
        }

        public String getCoveradurl() {
            return coveradurl;
        }

        public void setCoveradurl(String coveradurl) {
            this.coveradurl = coveradurl;
        }

        public int getDissid() {
            return dissid;
        }

        public void setDissid(int dissid) {
            this.dissid = dissid;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getUin() {
            return uin;
        }

        public void setUin(String uin) {
            this.uin = uin;
        }

        public String getEncrypt_uin() {
            return encrypt_uin;
        }

        public void setEncrypt_uin(String encrypt_uin) {
            this.encrypt_uin = encrypt_uin;
        }

        public int getOwndir() {
            return owndir;
        }

        public void setOwndir(int owndir) {
            this.owndir = owndir;
        }

        public String getDissname() {
            return dissname;
        }

        public void setDissname(String dissname) {
            this.dissname = dissname;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getPic_mid() {
            return pic_mid;
        }

        public void setPic_mid(String pic_mid) {
            this.pic_mid = pic_mid;
        }

        public String getAlbum_pic_mid() {
            return album_pic_mid;
        }

        public void setAlbum_pic_mid(String album_pic_mid) {
            this.album_pic_mid = album_pic_mid;
        }

        public int getPic_dpi() {
            return pic_dpi;
        }

        public void setPic_dpi(int pic_dpi) {
            this.pic_dpi = pic_dpi;
        }

        public int getIsAd() {
            return isAd;
        }

        public void setIsAd(int isAd) {
            this.isAd = isAd;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }

        public int getMtime() {
            return mtime;
        }

        public void setMtime(int mtime) {
            this.mtime = mtime;
        }

        public String getHeadurl() {
            return headurl;
        }

        public void setHeadurl(String headurl) {
            this.headurl = headurl;
        }

        public String getIfpicurl() {
            return ifpicurl;
        }

        public void setIfpicurl(String ifpicurl) {
            this.ifpicurl = ifpicurl;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getSingerid() {
            return singerid;
        }

        public void setSingerid(int singerid) {
            this.singerid = singerid;
        }

        public String getSingermid() {
            return singermid;
        }

        public void setSingermid(String singermid) {
            this.singermid = singermid;
        }

        public int getIsvip() {
            return isvip;
        }

        public void setIsvip(int isvip) {
            this.isvip = isvip;
        }

        public int getIsdj() {
            return isdj;
        }

        public void setIsdj(int isdj) {
            this.isdj = isdj;
        }

        public int getSongnum() {
            return songnum;
        }

        public void setSongnum(int songnum) {
            this.songnum = songnum;
        }

        public String getSongids() {
            return songids;
        }

        public void setSongids(String songids) {
            this.songids = songids;
        }

        public String getSongtypes() {
            return songtypes;
        }

        public void setSongtypes(String songtypes) {
            this.songtypes = songtypes;
        }

        public int getDisstype() {
            return disstype;
        }

        public void setDisstype(int disstype) {
            this.disstype = disstype;
        }

        public String getDir_pic_url2() {
            return dir_pic_url2;
        }

        public void setDir_pic_url2(String dir_pic_url2) {
            this.dir_pic_url2 = dir_pic_url2;
        }

        public int getSong_update_time() {
            return song_update_time;
        }

        public void setSong_update_time(int song_update_time) {
            this.song_update_time = song_update_time;
        }

        public int getSong_update_num() {
            return song_update_num;
        }

        public void setSong_update_num(int song_update_num) {
            this.song_update_num = song_update_num;
        }

        public int getTotal_song_num() {
            return total_song_num;
        }

        public void setTotal_song_num(int total_song_num) {
            this.total_song_num = total_song_num;
        }

        public int getSong_begin() {
            return song_begin;
        }

        public void setSong_begin(int song_begin) {
            this.song_begin = song_begin;
        }

        public int getCur_song_num() {
            return cur_song_num;
        }

        public void setCur_song_num(int cur_song_num) {
            this.cur_song_num = cur_song_num;
        }

        public int getVisitnum() {
            return visitnum;
        }

        public void setVisitnum(int visitnum) {
            this.visitnum = visitnum;
        }

        public int getCmtnum() {
            return cmtnum;
        }

        public void setCmtnum(int cmtnum) {
            this.cmtnum = cmtnum;
        }

        public int getBuynum() {
            return buynum;
        }

        public void setBuynum(int buynum) {
            this.buynum = buynum;
        }

        public String getScoreavage() {
            return scoreavage;
        }

        public void setScoreavage(String scoreavage) {
            this.scoreavage = scoreavage;
        }

        public int getScoreusercount() {
            return scoreusercount;
        }

        public void setScoreusercount(int scoreusercount) {
            this.scoreusercount = scoreusercount;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public List<SonglistBean> getSonglist() {
            return songlist;
        }

        public void setSonglist(List<SonglistBean> songlist) {
            this.songlist = songlist;
        }

        public static class TagsBean {
            /**
             * id : 167
             * name : 英语
             * pid : 167
             */

            private int id;
            private String name;
            private int pid;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }
        }

        public static class SonglistBean {


            private String albumdesc;
            private int albumid;
            private String albummid;
            private String albumname;
            private int alertid;
            private int belongCD;
            private int cdIdx;
            private int interval;
            private int isonly;
            private String label;
            private int msgid;
            private PayBean pay;
            private PreviewBean preview;
            private int rate;
            private int size128;
            private int size320;
            private int size5_1;
            private int sizeape;
            private int sizeflac;
            private int sizeogg;
            private int songid;
            private String songmid;
            private String songname;
            private String songorig;
            private int songtype;
            private String strMediaMid;
            private int stream;
            @SerializedName("switch")
            private int switchX;
            private int type;
            private String vid;
            private List<SingerBean> singer;

            public String getAlbumdesc() {
                return albumdesc;
            }

            public void setAlbumdesc(String albumdesc) {
                this.albumdesc = albumdesc;
            }

            public int getAlbumid() {
                return albumid;
            }

            public void setAlbumid(int albumid) {
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

            public int getAlertid() {
                return alertid;
            }

            public void setAlertid(int alertid) {
                this.alertid = alertid;
            }

            public int getBelongCD() {
                return belongCD;
            }

            public void setBelongCD(int belongCD) {
                this.belongCD = belongCD;
            }

            public int getCdIdx() {
                return cdIdx;
            }

            public void setCdIdx(int cdIdx) {
                this.cdIdx = cdIdx;
            }

            public int getInterval() {
                return interval;
            }

            public void setInterval(int interval) {
                this.interval = interval;
            }

            public int getIsonly() {
                return isonly;
            }

            public void setIsonly(int isonly) {
                this.isonly = isonly;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public int getMsgid() {
                return msgid;
            }

            public void setMsgid(int msgid) {
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

            public int getRate() {
                return rate;
            }

            public void setRate(int rate) {
                this.rate = rate;
            }

            public int getSize128() {
                return size128;
            }

            public void setSize128(int size128) {
                this.size128 = size128;
            }

            public int getSize320() {
                return size320;
            }

            public void setSize320(int size320) {
                this.size320 = size320;
            }

            public int getSize5_1() {
                return size5_1;
            }

            public void setSize5_1(int size5_1) {
                this.size5_1 = size5_1;
            }

            public int getSizeape() {
                return sizeape;
            }

            public void setSizeape(int sizeape) {
                this.sizeape = sizeape;
            }

            public int getSizeflac() {
                return sizeflac;
            }

            public void setSizeflac(int sizeflac) {
                this.sizeflac = sizeflac;
            }

            public int getSizeogg() {
                return sizeogg;
            }

            public void setSizeogg(int sizeogg) {
                this.sizeogg = sizeogg;
            }

            public int getSongid() {
                return songid;
            }

            public void setSongid(int songid) {
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

            public int getSongtype() {
                return songtype;
            }

            public void setSongtype(int songtype) {
                this.songtype = songtype;
            }

            public String getStrMediaMid() {
                return strMediaMid;
            }

            public void setStrMediaMid(String strMediaMid) {
                this.strMediaMid = strMediaMid;
            }

            public int getStream() {
                return stream;
            }

            public void setStream(int stream) {
                this.stream = stream;
            }

            public int getSwitchX() {
                return switchX;
            }

            public void setSwitchX(int switchX) {
                this.switchX = switchX;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
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
                 * payalbum : 0
                 * payalbumprice : 0
                 * paydownload : 1
                 * payinfo : 1
                 * payplay : 0
                 * paytrackmouth : 1
                 * paytrackprice : 200
                 * timefree : 0
                 */

                private int payalbum;
                private int payalbumprice;
                private int paydownload;
                private int payinfo;
                private int payplay;
                private int paytrackmouth;
                private int paytrackprice;
                private int timefree;

                public int getPayalbum() {
                    return payalbum;
                }

                public void setPayalbum(int payalbum) {
                    this.payalbum = payalbum;
                }

                public int getPayalbumprice() {
                    return payalbumprice;
                }

                public void setPayalbumprice(int payalbumprice) {
                    this.payalbumprice = payalbumprice;
                }

                public int getPaydownload() {
                    return paydownload;
                }

                public void setPaydownload(int paydownload) {
                    this.paydownload = paydownload;
                }

                public int getPayinfo() {
                    return payinfo;
                }

                public void setPayinfo(int payinfo) {
                    this.payinfo = payinfo;
                }

                public int getPayplay() {
                    return payplay;
                }

                public void setPayplay(int payplay) {
                    this.payplay = payplay;
                }

                public int getPaytrackmouth() {
                    return paytrackmouth;
                }

                public void setPaytrackmouth(int paytrackmouth) {
                    this.paytrackmouth = paytrackmouth;
                }

                public int getPaytrackprice() {
                    return paytrackprice;
                }

                public void setPaytrackprice(int paytrackprice) {
                    this.paytrackprice = paytrackprice;
                }

                public int getTimefree() {
                    return timefree;
                }

                public void setTimefree(int timefree) {
                    this.timefree = timefree;
                }
            }

            public static class PreviewBean {
                /**
                 * trybegin : 0
                 * tryend : 0
                 * trysize : 0
                 */

                private int trybegin;
                private int tryend;
                private int trysize;

                public int getTrybegin() {
                    return trybegin;
                }

                public void setTrybegin(int trybegin) {
                    this.trybegin = trybegin;
                }

                public int getTryend() {
                    return tryend;
                }

                public void setTryend(int tryend) {
                    this.tryend = tryend;
                }

                public int getTrysize() {
                    return trysize;
                }

                public void setTrysize(int trysize) {
                    this.trysize = trysize;
                }
            }

            public static class SingerBean {
                /**
                 * id : 12221
                 * mid : 001nwdiK02hg0y
                 * name : Pitbull
                 */

                private int id;
                private String mid;
                private String name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
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
    }

    @Override
    public String toString() {
        return "PlaylistDetail{" +
                "code=" + code +
                ", subcode=" + subcode +
                ", accessed_plaza_cache=" + accessed_plaza_cache +
                ", accessed_favbase=" + accessed_favbase +
                ", login='" + login + '\'' +
                ", cdnum=" + cdnum +
                ", realcdnum=" + realcdnum +
                ", cdlist=" + cdlist +
                '}';
    }
}
