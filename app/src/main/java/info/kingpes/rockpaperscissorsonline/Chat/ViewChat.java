package info.kingpes.rockpaperscissorsonline.Chat;

import java.util.List;

import info.kingpes.rockpaperscissorsonline.Model.messageObject;

/**
 * Created by Chau Huynh on 06/02/02017.
 */

public interface ViewChat {
    void getMessage(List<messageObject> listMessage, boolean local);
    void updateNew(String msg, boolean lcal);
}
