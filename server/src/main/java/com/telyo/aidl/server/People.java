package com.telyo.aidl.server;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tanghuan on 2018/11/1.
 * Supported By 甜瓜移动.
 * Official Website: www.melonmobile.cn.
 *
 * @author tanghuan
 */
public class People implements Parcelable{
    String name;
    String age;

    public People(String name, String age) {
        this.name = name;
        this.age = age;
    }

    protected People(Parcel in) {
        name = in.readString();
        age = in.readString();
    }

    public static final Creator<People> CREATOR = new Creator<People>() {
        @Override
        public People createFromParcel(Parcel in) {
            return new People(in);
        }

        @Override
        public People[] newArray(int size) {
            return new People[size];
        }
    };

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age == null ? "" : age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(age);
    }

    public void readFromParcel(Parcel reply) {
        name = reply.readString();
        age = reply.readString();
    }
}
