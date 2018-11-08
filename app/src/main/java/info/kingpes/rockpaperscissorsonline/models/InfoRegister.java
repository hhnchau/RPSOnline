package info.kingpes.rockpaperscissorsonline.models;

import org.json.JSONException;
import org.json.JSONObject;

import info.kingpes.rockpaperscissorsonline.log.MyLog;

/**
 * Created by Chau Huynh on 6/12/2017.
 */

public class InfoRegister {
    private String idDevice;
    private String nickName;
    private String address;

    public InfoRegister() {
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

    //Convert toJSONObject
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idDevice", getIdDevice());

            jsonObject.put("nickName", getNickName());

            jsonObject.put("address", getAddress());


            MyLog.writeLog("InfoRegister: " + jsonObject.toString());
            return jsonObject;
        } catch (JSONException e) {
            MyLog.writeLog("InfoRegister: " + e);
            e.printStackTrace();
            return jsonObject;
        }
    }
}
