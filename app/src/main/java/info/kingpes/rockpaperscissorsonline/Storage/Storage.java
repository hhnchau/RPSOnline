package info.kingpes.rockpaperscissorsonline.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Chau Huynh on 09/02/02017.
 */

public class Storage {
    private static Storage instance;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    public static Storage getInstance(Context context) {
        if (instance == null) {
            instance = new Storage();
            preferences = context.getSharedPreferences("Storage", MODE_PRIVATE);
            editor = preferences.edit();
        }
        return instance;
    }

    public void setNickName(String nickName) {
        editor.putString("NickName", nickName);
        editor.commit();
    }

    public String getNickName() {
        return preferences.getString("NickName", null);
    }

    public void setAddress(String address) {
        editor.putString("Address", address);
        editor.commit();
    }

    public String getAddress() {
        return preferences.getString("Address", null);
    }

    public void setSoundEnable(boolean off) {
        editor.putBoolean("SoundOff", off);
        editor.commit();
    }

    public boolean getSoundEnable() {
        return preferences.getBoolean("SoundOff", false);
    }


//    private Context context;
//    private SharedPreferences preferences;
//    private SharedPreferences.Editor editor;
//
//    public Storage(Context context) {
//        this.context = context;
//        preferences = context.getSharedPreferences("Storage", MODE_PRIVATE);
//        editor = preferences.edit();
//    }

    public void setBilling(int bill) {
        editor.putInt("Bill", bill);
        editor.commit();
    }

    public int getBilling() {
        return preferences.getInt("Bill", 0);
    }

    public void setRank(int rank) {
        editor.putInt("Rank", rank);
        editor.commit();
    }

    public int getRank() {
        return preferences.getInt("Rank", 0);
    }

    public void setLocal(String local) {
        editor.putString("LocalUpdate", local);
        editor.commit();
    }

    public String getLocal() {
        return preferences.getString("LocalUpdate", "0");
    }

    public void setGlobal(String global) {
        editor.putString("GlobalUpdate", global);
        editor.commit();
    }

    public String getGlobal() {
        return preferences.getString("GlobalUpdate", "0");
    }


}
