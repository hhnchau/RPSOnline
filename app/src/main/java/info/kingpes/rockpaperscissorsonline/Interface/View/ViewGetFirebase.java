package info.kingpes.rockpaperscissorsonline.Interface.View;

import info.kingpes.rockpaperscissorsonline.Model.userObject;

/**
 * Created by Chau Huynh on 23/01/02017.
 */

public interface ViewGetFirebase {
    void getIdGuest(String id);

    void getProfile(String id, userObject user);

    void listenerConnecting(String idGuest);

    void listenerRPS(String rps);

    void listenerIcon(String icon);

    void listenerFlag(String flag);

    void listenerAgain(String again);

    void listenerVisitorOffline(String off);

    void listenerSync(String sync);
}
