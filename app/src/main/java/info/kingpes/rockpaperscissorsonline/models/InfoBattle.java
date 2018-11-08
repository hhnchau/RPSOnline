package info.kingpes.rockpaperscissorsonline.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Chau Huynh on 5/21/2017.
 */

public class InfoBattle implements Parcelable {
    private String room;
    private List<InfoUserBattle> infoUserBattle;

    public InfoBattle() {
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public List<InfoUserBattle> getInfoUserBattle() {
        return infoUserBattle;
    }

    public void setInfoUserBattle(List<InfoUserBattle> infoUserBattle) {
        this.infoUserBattle = infoUserBattle;
    }

    //Implements Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.room);
        dest.writeTypedList(this.infoUserBattle);
    }

    protected InfoBattle(Parcel in) {
        room = in.readString();
        infoUserBattle = in.createTypedArrayList(InfoUserBattle.CREATOR);
    }

    public static final Creator<InfoBattle> CREATOR = new Creator<InfoBattle>() {
        @Override
        public InfoBattle createFromParcel(Parcel in) {
            return new InfoBattle(in);
        }

        @Override
        public InfoBattle[] newArray(int size) {
            return new InfoBattle[size];
        }
    };
}
