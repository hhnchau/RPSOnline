package info.kingpes.rockpaperscissorsonline.Chat;

/**
 * Created by Chau Huynh on 06/02/02017.
 */

public interface InterfaceChat {
    void sendMessage(String id,String name, String location, String msg, boolean global);
    void getMessageLocal(String location);
    void getMessageGlobal();
    void getUpdateLocal(String location);
    void getUpdateGlobal();
}
