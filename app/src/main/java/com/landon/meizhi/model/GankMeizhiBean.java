package com.landon.meizhi.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.landon.meizhi.retrofit.GankService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by landon.xu on 2017/1/6.
 */

public class GankMeizhiBean implements Parcelable {

    /**
     * error : false
     * results : [{"_id":"586d8f74421aa9316407fb56","createdAt":"2017-01-05T08:12:36.360Z","desc":"1-5","publishedAt":"2017-01-05T13:18:10.185Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1fbffqo6jjoj20u011hgpx.jpg","used":true,"who":"daimajia "},{"_id":"586c63a6421aa94dc1ac0b02","createdAt":"2017-01-04T10:53:26.957Z","desc":"1-4","publishedAt":"2017-01-04T11:39:01.326Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1fbeerrs7aqj20u011htec.jpg","used":true,"who":"daimajia"},{"_id":"586b0915421aa94dbbd82bcf","createdAt":"2017-01-03T10:14:45.467Z","desc":"1-3","publishedAt":"2017-01-03T11:51:31.742Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034jw1fbd818kkwjj20u011hjup.jpg","used":true,"who":"daimajia"},{"_id":"5865ad4e421aa94dbe2ccdb0","createdAt":"2016-12-30T08:41:50.830Z","desc":"12-30","publishedAt":"2016-12-30T16:16:11.125Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1fb8iv9u08ij20u00u0tc7.jpg","used":true,"who":"daimajia"},{"_id":"58645be0421aa94dbbd82bac","createdAt":"2016-12-29T08:42:08.389Z","desc":"12-29","publishedAt":"2016-12-29T11:56:56.946Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034gw1fb7d9am05gj20u011hq64.jpg","used":true,"who":"daimajia"},{"_id":"58632374421aa9723d29b9ba","createdAt":"2016-12-28T10:29:08.507Z","desc":"12-28","publishedAt":"2016-12-28T11:57:39.616Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034gw1fb6aqccs3nj20u00u0wk4.jpg","used":true,"who":"daimajia"},{"_id":"5861d315421aa97240ef9f43","createdAt":"2016-12-27T10:33:57.376Z","desc":"12-27","publishedAt":"2016-12-27T12:06:15.638Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034gw1fb558z2peqj20u00u00v9.jpg","used":true,"who":"daimajia "},{"_id":"58606820421aa9723d29b9a1","createdAt":"2016-12-26T08:45:20.537Z","desc":"12-26","publishedAt":"2016-12-26T11:40:05.242Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1fb3whph0ilj20u00na405.jpg","used":true,"who":"daimajia"},{"_id":"585c9b32421aa9723a5a77b6","createdAt":"2016-12-23T11:34:10.86Z","desc":"12-23","publishedAt":"2016-12-23T11:41:19.908Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034gw1fb0kieivhgj20u00k2gmr.jpg","used":true,"who":"daimajia"},{"_id":"585b22cd421aa97240ef9f15","createdAt":"2016-12-22T08:48:13.828Z","desc":"12-22","publishedAt":"2016-12-22T11:34:37.39Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1faza3ghd2lj20f00k1gof.jpg","used":true,"who":"daimajia"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements Parcelable {
        /**
         * _id : 586d8f74421aa9316407fb56
         * createdAt : 2017-01-05T08:12:36.360Z
         * desc : 1-5
         * publishedAt : 2017-01-05T13:18:10.185Z
         * source : chrome
         * type : 福利
         * url : http://ww4.sinaimg.cn/large/610dc034jw1fbffqo6jjoj20u011hgpx.jpg
         * used : true
         * who : daimajia
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public MeizhiBean convert(){
            MeizhiBean bean = new MeizhiBean();
            bean.setId(get_id());
            bean.setUrl(getUrl());
            bean.setDes(getDesc());
            bean.setSource("gank.io");
            return bean;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this._id);
            dest.writeString(this.createdAt);
            dest.writeString(this.desc);
            dest.writeString(this.publishedAt);
            dest.writeString(this.source);
            dest.writeString(this.type);
            dest.writeString(this.url);
            dest.writeByte(this.used ? (byte) 1 : (byte) 0);
            dest.writeString(this.who);
        }

        public ResultsBean() {
        }

        protected ResultsBean(Parcel in) {
            this._id = in.readString();
            this.createdAt = in.readString();
            this.desc = in.readString();
            this.publishedAt = in.readString();
            this.source = in.readString();
            this.type = in.readString();
            this.url = in.readString();
            this.used = in.readByte() != 0;
            this.who = in.readString();
        }

        public static final Parcelable.Creator<ResultsBean> CREATOR = new Parcelable.Creator<ResultsBean>() {
            @Override
            public ResultsBean createFromParcel(Parcel source) {
                return new ResultsBean(source);
            }

            @Override
            public ResultsBean[] newArray(int size) {
                return new ResultsBean[size];
            }
        };
    }

    public List<MeizhiBean> convert(){
        if(getResults() != null && getResults().size() > 0){
            List<MeizhiBean> result = new ArrayList<>();
            for(ResultsBean bean : getResults()){
                result.add(bean.convert());
            }
            return result;
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.error ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.results);
    }

    public GankMeizhiBean() {
    }

    protected GankMeizhiBean(Parcel in) {
        this.error = in.readByte() != 0;
        this.results = in.createTypedArrayList(ResultsBean.CREATOR);
    }

    public static final Parcelable.Creator<GankMeizhiBean> CREATOR = new Parcelable.Creator<GankMeizhiBean>() {
        @Override
        public GankMeizhiBean createFromParcel(Parcel source) {
            return new GankMeizhiBean(source);
        }

        @Override
        public GankMeizhiBean[] newArray(int size) {
            return new GankMeizhiBean[size];
        }
    };
}
