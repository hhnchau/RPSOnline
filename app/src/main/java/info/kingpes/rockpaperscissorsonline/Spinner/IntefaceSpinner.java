package info.kingpes.rockpaperscissorsonline.Spinner;

import android.view.View;

/**
 * Created by Chau Huynh on 08/02/02017.
 */

public interface IntefaceSpinner {
    void startWheel(View view, int wheel);
    void startLife(View view, int count);
    void starBlink(View view);
    void startZoom(View view);
    int getBonus(int wheel);
}
