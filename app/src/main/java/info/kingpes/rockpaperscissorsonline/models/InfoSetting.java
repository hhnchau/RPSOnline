package info.kingpes.rockpaperscissorsonline.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Chau Huynh on 6/8/2017.
 */

public class InfoSetting implements Parcelable {
    private String version;
    private int bonus;
    private boolean exist;
    private long currentTime;
    private List<MessageChat> messageChat;
    private List<InfoMarket> infoMarket;

    public InfoSetting() {
    }

    protected InfoSetting(Parcel in) {
        version = in.readString();
        bonus = in.readInt();
        exist = in.readByte() != 0;
        currentTime = in.readLong();
        messageChat = in.createTypedArrayList(MessageChat.CREATOR);
        infoMarket = in.createTypedArrayList(InfoMarket.CREATOR);
    }

    public static final Creator<InfoSetting> CREATOR = new Creator<InfoSetting>() {
        @Override
        public InfoSetting createFromParcel(Parcel in) {
            return new InfoSetting(in);
        }

        @Override
        public InfoSetting[] newArray(int size) {
            return new InfoSetting[size];
        }
    };

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public List<MessageChat> getMessageChat() {
        return messageChat;
    }

    public void setMessageChat(List<MessageChat> messageChat) {
        this.messageChat = messageChat;
    }

    public List<InfoMarket> getInfoMarket() {
        return infoMarket;
    }

    public void setInfoMarket(List<InfoMarket> infoMarket) {
        this.infoMarket = infoMarket;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(version);
        dest.writeInt(bonus);
        dest.writeByte((byte) (exist ? 1 : 0));
        dest.writeLong(currentTime);
        dest.writeTypedList(messageChat);
        dest.writeTypedList(infoMarket);
    }
}
