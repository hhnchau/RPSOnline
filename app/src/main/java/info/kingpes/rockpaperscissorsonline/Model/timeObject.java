package info.kingpes.rockpaperscissorsonline.Model;

import java.util.Map;

/**
 * Created by Chau Huynh on 05/02/02017.
 */

public class timeObject {
    private Map<String, String> joinTime;
    private Map<String, String> bonusTime;
    private Map<String, String> checkBonusTime;

    public timeObject() {
    }

    public timeObject(Map<String, String> joinTime, Map<String, String> bonusTime, Map<String, String> checkBonusTime) {
        this.joinTime = joinTime;
        this.bonusTime = bonusTime;
        this.checkBonusTime = checkBonusTime;
    }

    public Map<String, String> getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Map<String, String> joinTime) {
        this.joinTime = joinTime;
    }

    public Map<String, String> getBonusTime() {
        return bonusTime;
    }

    public void setBonusTime(Map<String, String> bonusTime) {
        this.bonusTime = bonusTime;
    }

    public Map<String, String> getCheckBonusTime() {
        return checkBonusTime;
    }

    public void setCheckBonusTime(Map<String, String> checkBonusTime) {
        this.checkBonusTime = checkBonusTime;
    }
}
