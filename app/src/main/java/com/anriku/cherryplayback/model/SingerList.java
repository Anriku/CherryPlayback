package com.anriku.cherryplayback.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by anriku on 2018/11/8.
 */

public class SingerList {

    private int code;
    private DataBean data;
    private String message;
    private int subcode;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public int getSubcode() {
        return subcode;
    }

    public void setSubcode(int subcode) {
        this.subcode = subcode;
    }

    public static class DataBean {


        private int per_page;
        private int total;
        private int total_page;
        private List<ListBean> list;

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotal_page() {
            return total_page;
        }

        public void setTotal_page(int total_page) {
            this.total_page = total_page;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Parcelable {
            /**
             * Farea : 1
             * Fattribute_3 : 3
             * Fattribute_4 : 0
             * Fgenre : 0
             * Findex : X
             * Fother_name : Joker
             * Fsinger_id : 5062
             * Fsinger_mid : 002J4UUk29y8BY
             * Fsinger_name : 薛之谦
             * Fsinger_tag : 541,555
             * Fsort : 1
             * Ftrend : 0
             * Ftype : 0
             * voc : 0
             */
            private String Farea;
            private String Fattribute_3;
            private String Fattribute_4;
            private String Fgenre;
            private String Findex;
            private String Fother_name;
            private String Fsinger_id;
            private String Fsinger_mid;
            private String Fsinger_name;
            private String Fsinger_tag;
            private String Fsort;
            private String Ftrend;
            private String Ftype;
            private String voc;

            protected ListBean(Parcel in) {
                Farea = in.readString();
                Fattribute_3 = in.readString();
                Fattribute_4 = in.readString();
                Fgenre = in.readString();
                Findex = in.readString();
                Fother_name = in.readString();
                Fsinger_id = in.readString();
                Fsinger_mid = in.readString();
                Fsinger_name = in.readString();
                Fsinger_tag = in.readString();
                Fsort = in.readString();
                Ftrend = in.readString();
                Ftype = in.readString();
                voc = in.readString();
            }

            public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
                @Override
                public ListBean createFromParcel(Parcel in) {
                    return new ListBean(in);
                }

                @Override
                public ListBean[] newArray(int size) {
                    return new ListBean[size];
                }
            };

            public String getFarea() {
                return Farea;
            }

            public void setFarea(String Farea) {
                this.Farea = Farea;
            }

            public String getFattribute_3() {
                return Fattribute_3;
            }

            public void setFattribute_3(String Fattribute_3) {
                this.Fattribute_3 = Fattribute_3;
            }

            public String getFattribute_4() {
                return Fattribute_4;
            }

            public void setFattribute_4(String Fattribute_4) {
                this.Fattribute_4 = Fattribute_4;
            }

            public String getFgenre() {
                return Fgenre;
            }

            public void setFgenre(String Fgenre) {
                this.Fgenre = Fgenre;
            }

            public String getFindex() {
                return Findex;
            }

            public void setFindex(String Findex) {
                this.Findex = Findex;
            }

            public String getFother_name() {
                return Fother_name;
            }

            public void setFother_name(String Fother_name) {
                this.Fother_name = Fother_name;
            }

            public String getFsinger_id() {
                return Fsinger_id;
            }

            public void setFsinger_id(String Fsinger_id) {
                this.Fsinger_id = Fsinger_id;
            }

            public String getFsinger_mid() {
                return Fsinger_mid;
            }

            public void setFsinger_mid(String Fsinger_mid) {
                this.Fsinger_mid = Fsinger_mid;
            }

            public String getFsinger_name() {
                return Fsinger_name;
            }

            public void setFsinger_name(String Fsinger_name) {
                this.Fsinger_name = Fsinger_name;
            }

            public String getFsinger_tag() {
                return Fsinger_tag;
            }

            public void setFsinger_tag(String Fsinger_tag) {
                this.Fsinger_tag = Fsinger_tag;
            }

            public String getFsort() {
                return Fsort;
            }

            public void setFsort(String Fsort) {
                this.Fsort = Fsort;
            }

            public String getFtrend() {
                return Ftrend;
            }

            public void setFtrend(String Ftrend) {
                this.Ftrend = Ftrend;
            }

            public String getFtype() {
                return Ftype;
            }

            public void setFtype(String Ftype) {
                this.Ftype = Ftype;
            }

            public String getVoc() {
                return voc;
            }

            public void setVoc(String voc) {
                this.voc = voc;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(Farea);
                dest.writeString(Fattribute_3);
                dest.writeString(Fattribute_4);
                dest.writeString(Fgenre);
                dest.writeString(Findex);
                dest.writeString(Fother_name);
                dest.writeString(Fsinger_id);
                dest.writeString(Fsinger_mid);
                dest.writeString(Fsinger_name);
                dest.writeString(Fsinger_tag);
                dest.writeString(Fsort);
                dest.writeString(Ftrend);
                dest.writeString(Ftype);
                dest.writeString(voc);
            }
        }
    }
}
