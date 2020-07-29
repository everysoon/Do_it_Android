package com.example.study34_gridview;

public class girl {
    String name;
    String mobile;
    int resId;
    public girl(String name, String mobile,int redId) {
        this.name = name;
        this.mobile = mobile;
        this.resId=redId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "girl{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
