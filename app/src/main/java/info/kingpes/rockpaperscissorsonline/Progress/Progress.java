package info.kingpes.rockpaperscissorsonline.Progress;

import android.os.CountDownTimer;

import java.util.Random;

/**
 * Created by Chau Huynh on 26/01/02017.
 */

public class Progress implements InterfaceProgess {
    private ViewProgress viewProgress;

    public Progress(ViewProgress viewProgress) {
        this.viewProgress = viewProgress;
    }

    private CountDownTimer counter = null;
    private int percentLoading = 0;
    private boolean onContinue = true;


    @Override
    public void start(final int type) {

        counter = new CountDownTimer(100000, type) {
            public void onTick(long millisUntilFinished) {
                if (onContinue) {
                    if (percentLoading < 100) {
                        viewProgress.onProgress(percentLoading);
                        Random random = new Random();
                        percentLoading = percentLoading + random.nextInt(5) + 1;
                    } else {
                        viewProgress.onProgress(100);
                        viewProgress.finishProgress();
                        onContinue = false;
                    }
                }
            }

            public void onFinish() {

            }
        };
        counter.start();
    }
}
