package info.kingpes.rockpaperscissorsonline.Score;

/**
 * Created by Chau Huynh on 08/02/02017.
 */

public interface InterfaceScore {
    void setUpdateLife(String id, int lifes);
    void setUpdateWheel(String id, int wheel);
    void setUpdateArea(String id, int area);
    void getUpdateLife(String id);
    void getUpdateWheel(String id);
    void getUpdateArea(String id);
}
