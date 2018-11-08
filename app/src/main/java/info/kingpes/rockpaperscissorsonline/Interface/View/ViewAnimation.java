package info.kingpes.rockpaperscissorsonline.Interface.View;

import android.view.View;

/**
 * Created by Chau Huynh on 14/01/02017.
 */

public interface ViewAnimation {
    void finishAnimation(View view, int status);
    void repeatAnimation(View view);
}
