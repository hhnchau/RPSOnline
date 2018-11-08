package info.kingpes.rockpaperscissorsonline.Util;

import android.content.Context;
import android.provider.Settings;

/**
 * Created by Chau Huynh on 24/01/02017.
 */

public class Helper {
    private Context context;
    private String hardwareId;

    public Helper() {
    }

    public Helper(Context context) {
        this.context = context;
        hardwareId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public String getId() {
        return hardwareId;
    }

    public String getRoot() {
        return "KINGPES";
    }

    public String getBilling() {
        return "BILLING";
    }

    public String getRank() {
        return "RANK";
    }

    public String getChat() {
        return "CHAT";
    }

    public String getGlobal() {
        return "GLOBAL";
    }

    public String getLocal() {
        return "LOCAL";
    }

    public String getLast() {
        return "LAST";
    }

    public String getlast() {
        return "last";
    }

    public String getTime() {
        return "TIMES";
    }

    public String getJoin() {
        return "JOIN";
    }

    public String getBonus() {
        return "BONUS";
    }

    public String getCheck() {
        return "CHECK";
    }

    public String getBonusTime() {
        return "bonusTime";
    }

    public String getCheckBonusTime() {
        return "checkBonusTime";
    }

    public String checkConnect() {
        return "CHECK-CONNECT";
    }

    public String getComputer() {
        return "COPUTER";
    }

    public String getcomputer() {
        return "computer";
    }

    public String getUser() {
        return "USER";
    }

    public String getBattle() {
        return "BATTLE";
    }

    public String getServer() {
        return "SERVER";
    }

    public String getClient() {
        return "CLIENT";
    }

    public String getConnect() {
        return "CONNECT";
    }

    public String getRPS() {
        return "RPS";
    }

    public String getrps() {
        return "rps";
    }

    public String getIcon() {
        return "ICON";
    }

    public String geticon() {
        return "icon";
    }

    public String getFlag() {
        return "FLAG";
    }

    public String getflag() {
        return "flag";
    }

    public String getAgain() {
        return "AGAIN";
    }

    public String getagain() {
        return "again";
    }

    public String getIdGuest() {
        return "id";
    }

    public String getNameHome() {
        return "name_home";
    }

    public String getNameGuest() {
        return "name_guest";
    }

    public String getLocationHome() {
        return "location_home";
    }

    public String getLocationGuest() {
        return "location_guest";
    }

    public String getStarHome() {
        return "star_home";
    }

    public String getStarGuest() {
        return "star_guest";
    }

    public String getLifeHome() {
        return "life_home";
    }

    public String getLifeGuest() {
        return "life_guest";
    }

    public String getWinHome() {
        return "win_home";
    }

    public String getWinGuest() {
        return "win_guest";
    }

    public String getLostHome() {
        return "lost_home";
    }

    public String getLostGuest() {
        return "lost_guest";
    }

    public String getWheelHome() {
        return "wheel_home";
    }

    public String getAreaHome() {
        return "area_home";
    }

    public String getAreaGuest() {
        return "area_guest";
    }

    public String getWin() {
        return "You win";
    }

    public String getLost() {
        return "You lost";
    }

    public String getDraw() {
        return "You draw";
    }

    public int getSlow() {
        return 2000;
    }

    public int getFast() {
        return 1;
    }

    public String getRound() {
        return "Round ";
    }

    public String getstar() {
        return "star";
    }

    public String getlife() {
        return "life";
    }

    public String getwin() {
        return "win";
    }

    public String getlost() {
        return "lost";
    }

    public String getwheel() {
        return "wheel";
    }

    public String getArea() {
        return "Area";
    }

    public String getarea() {
        return "area";
    }

    public String getOffline() {
        return "OFFLINE";
    }

    public String getoffline() {
        return "off";
    }

    public String getSync() {
        return "SYNC";
    }

    public String getsync() {
        return "sync";
    }

}
