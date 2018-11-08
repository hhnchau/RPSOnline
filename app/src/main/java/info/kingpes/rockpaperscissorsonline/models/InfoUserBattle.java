package info.kingpes.rockpaperscissorsonline.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chau Huynh on 5/21/2017.
 */

public class InfoUserBattle implements Parcelable {
    private String idDevice;
    private String nickName;
    private String address;
    private int star;
    private int life;
    private int win;
    private int lost;
    private int draw;
    private int champion;
    private int maxChampion;

    public InfoUserBattle() {
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getChampion() {
        return champion;
    }

    public void setChampion(int champion) {
        this.champion = champion;
    }

    public int getMaxChampion() {
        return maxChampion;
    }

    public void setMaxChampion(int maxChampion) {
        this.maxChampion = maxChampion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idDevice);
        dest.writeString(this.nickName);
        dest.writeString(this.address);
        dest.writeInt(this.star);
        dest.writeInt(this.life);
        dest.writeInt(this.win);
        dest.writeInt(this.lost);
        dest.writeInt(this.draw);
        dest.writeInt(this.champion);
        dest.writeInt(this.maxChampion);
    }

    protected InfoUserBattle(Parcel in) {
        idDevice = in.readString();
        nickName = in.readString();
        address = in.readString();
        star = in.readInt();
        life = in.readInt();
        win = in.readInt();
        lost = in.readInt();
        draw = in.readInt();
        champion = in.readInt();
        maxChampion = in.readInt();
    }

    public static final Creator<InfoUserBattle> CREATOR = new Creator<InfoUserBattle>() {
        @Override
        public InfoUserBattle createFromParcel(Parcel in) {
            return new InfoUserBattle(in);
        }

        @Override
        public InfoUserBattle[] newArray(int size) {
            return new InfoUserBattle[size];
        }
    };
}
