package info.kingpes.rockpaperscissorsonline.Presenter;

import android.os.CountDownTimer;
import android.view.View;

import info.kingpes.rockpaperscissorsonline.Interface.Interface.InterfaceTimerTask;
import info.kingpes.rockpaperscissorsonline.Interface.View.ViewTimerTask;

/**
 * Created by Chau Huynh on 14/01/02017.
 */

public class PresenterTimerTask implements InterfaceTimerTask {
    private ViewTimerTask timerTask;

    public PresenterTimerTask(ViewTimerTask timerTask) {
        this.timerTask = timerTask;
    }

    private CountDownTimer cTimer;

    @Override
    public void startTimer(int seconds, final View view) {
        cTimer = null;
        cTimer = new CountDownTimer(seconds * 1000, 1000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                timerTask.timerFinish(view);
            }
        };
        cTimer.start();

    }
}
