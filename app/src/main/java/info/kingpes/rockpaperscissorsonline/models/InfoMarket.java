package info.kingpes.rockpaperscissorsonline.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import info.kingpes.rockpaperscissorsonline.log.MyLog;

/**
 * Created by Chau Huynh on 6/8/2017.
 */

public class InfoMarket implements Parcelable {
    private String title;
    private String des;
    private int price;
    private int value;
    private int type;

    public InfoMarket() {
    }

    protected InfoMarket(Parcel in) {
        title = in.readString();
        des = in.readString();
        price = in.readInt();
        value = in.readInt();
        type = in.readInt();
    }

    public static final Creator<InfoMarket> CREATOR = new Creator<InfoMarket>() {
        @Override
        public InfoMarket createFromParcel(Parcel in) {
            return new InfoMarket(in);
        }

        @Override
        public InfoMarket[] newArray(int size) {
            return new InfoMarket[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(des);
        dest.writeInt(price);
        dest.writeInt(value);
        dest.writeInt(type);
    }
}
