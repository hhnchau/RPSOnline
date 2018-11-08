package info.kingpes.rockpaperscissorsonline.Interface.Interface;

import android.view.View;

/**
 * Created by Chau Huynh on 14/01/02017.
 */

public interface InterfaceAnimation {
    void rps_home_Translate(View view, int rps);
    void rps_guest_Translate(View view, int rps);
    void icon_home_Translate(View view);
    void icon_guest_Translate(View view);
    void blink(View view);
    void ready_Anmation(View view);
    void result_Animation(View view);
    void search_Animation(View view);
    void vs_in_Animation(View view);
    void vs_out_Animation(View view);
    void bg1_Animation(View view);
    void profile_Animation(View view);
    void score_Animation(View view);
    void rps_in_Animation(View view);
    void rps_out_Animation(View view);
    void count_Animation(View view);
    void ln_icon_in_Animation(View view);
    void ln_icon_out_Animation(View view);
    void over_Animation(View view);
    void messege_Animation(View view);
    void select_Animation(View view);
    void no_wifi_Animation(View view);
}
