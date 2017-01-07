package com.landon.meizhi.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by landon.xu on 2017/1/6.
 */

public class MeizhiBean implements Parcelable {

    private String id;

    private String url;

    private String des;

    private String source;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.url);
        dest.writeString(this.des);
        dest.writeString(this.source);
    }

    public MeizhiBean(){

    }

    protected MeizhiBean(Parcel in) {
        this.id = in.readString();
        this.url = in.readString();
        this.des = in.readString();
        this.source = in.readString();
    }

    public static final Parcelable.Creator<MeizhiBean> CREATOR = new Parcelable.Creator<MeizhiBean>() {
        @Override
        public MeizhiBean createFromParcel(Parcel source) {
            return new MeizhiBean(source);
        }

        @Override
        public MeizhiBean[] newArray(int size) {
            return new MeizhiBean[size];
        }
    };
}
