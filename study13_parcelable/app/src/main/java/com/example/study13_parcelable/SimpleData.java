package com.example.study13_parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleData implements Parcelable {
    int number;
    String message;

    public SimpleData(int number, String message) {
        this.number = number;
        this.message = message;
    }
    public SimpleData(Parcel src){
        number=src.readInt();
        message=src.readString(); //parcel -> simpledata
    }

    public static final Parcelable.Creator CREATOR=new Parcelable.Creator(){
        public SimpleData createFromParcel(Parcel in){
            return new SimpleData(in);
        }
        public SimpleData[] newArray(int size){
            return new SimpleData[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(number);
        dest.writeString(message); //simpledata-> parcel
    }
    //parcel은 객체안 데이터를 다른곳에 전달할때 사용되는 객체!
}
