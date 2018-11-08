package info.kingpes.rockpaperscissorsonline.Time;

/**
 * Created by Chau Huynh on 05/02/02017.
 */

public interface InterfaceTime {
    void setJoinTime(String id);

    void setBonusTime(String id);

    void getBonusTime(String id);

    void checkBonusTime(String id, long checServer);
}
