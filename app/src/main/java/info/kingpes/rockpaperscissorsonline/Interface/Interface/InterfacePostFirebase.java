package info.kingpes.rockpaperscissorsonline.Interface.Interface;

/**
 * Created by Chau Huynh on 23/01/02017.
 */

public interface InterfacePostFirebase {

    void createUser(String name, String location);

    void deleteUser(String id);

    void registerRoomServer();

    void removeIdRoomServer(String id);

    void connectToGuest(String idGuest);

    void deleteConnect(String id);

    void setRPS(String idGuset, int rps);

    void deleteRPS(String id);

    void setIcon(String idGuset, int icon);

    void deleteIcon(String id);

    void setFlag(String idGuset, int flag);

    void deletaFlag(String id);

    void setAgain(String idGuest, int again);

    void deleteAgain(String id);

    void setSync(String idGuest, int sync);

    void deleteSync(String id);

    void setOffline(String idGuest, int off);

    void deleteOffline(String id);

    void addStart(String id, int star_old, int star_new);

    void subStart(String id, int star_old, int star_new);

    void addLife(String id, int life_old, int life_new);

    void subLife(String id, int life_old, int life_new);

    void addWin(String id, int win_old, int win_new);

    void subWin(String id, int win_old, int win_new);

    void addLost(String id, int lost_old, int lost_new);

    void subLost(String id, int lost_old, int lost_new);

}
