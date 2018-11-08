package info.kingpes.rockpaperscissorsonline.models;

import org.json.JSONException;
import org.json.JSONObject;

import info.kingpes.rockpaperscissorsonline.log.MyLog;

/**
 * Created by Chau Huynh on 5/27/2017.
 */

public class RealModel {
    private String idDevice;
    private String room;
    private int value;
    private int status;
    private int position;
    private String message;

    public RealModel() {
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //Convert toJSONObject
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idDevice", getIdDevice());

            if (getRoom() != null) {
                jsonObject.put("room", getRoom());
            }

            if (getValue() != 0) {
                jsonObject.put("value", getValue());
            }
            if (getStatus() != 0) {
                jsonObject.put("status", getStatus());
            }
            if (getMessage() != null) {
                jsonObject.put("message", getMessage());
            }
            MyLog.writeLog("RealModel: " + jsonObject.toString());
            return jsonObject;
        } catch (JSONException e) {
            MyLog.writeLog("RealModel: " + e);
            e.printStackTrace();
            return jsonObject;
        }
    }
}
