package info.kingpes.rockpaperscissorsonline.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;


import info.kingpes.rockpaperscissorsonline.log.MyLog;

/**
 * Created by Chau Huynh on 6/7/2017.
 */

public class MessageChat implements Parcelable {
    private String room;
    private String idDevice;
    private String nickName;
    private String address;
    private String message;
    private long time;

    public MessageChat() {
    }

    protected MessageChat(Parcel in) {
        room = in.readString();
        idDevice = in.readString();
        nickName = in.readString();
        address = in.readString();
        message = in.readString();
        time = in.readLong();
    }

    public static final Creator<MessageChat> CREATOR = new Creator<MessageChat>() {
        @Override
        public MessageChat createFromParcel(Parcel in) {
            return new MessageChat(in);
        }

        @Override
        public MessageChat[] newArray(int size) {
            return new MessageChat[size];
        }
    };

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    //Convert toJSONObject
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("room", getRoom());
            jsonObject.put("idDevice", getIdDevice());
            jsonObject.put("nickName", getNickName());
            jsonObject.put("address", getAddress());
            jsonObject.put("message", getMessage());
            jsonObject.put("time", getTime());

            MyLog.writeLog("MessageChat: " + jsonObject.toString());
            return jsonObject;
        } catch (JSONException e) {
            MyLog.writeLog("MessageChat: " + e);
            e.printStackTrace();
            return jsonObject;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(room);
        dest.writeString(idDevice);
        dest.writeString(nickName);
        dest.writeString(address);
        dest.writeString(message);
        dest.writeLong(time);
    }
}
