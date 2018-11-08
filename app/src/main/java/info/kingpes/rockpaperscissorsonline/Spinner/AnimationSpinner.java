package info.kingpes.rockpaperscissorsonline.Spinner;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by Chau Huynh on 08/02/02017.
 */

public class AnimationSpinner implements IntefaceSpinner {
    private ViewSpinner viewSpinner;

    public AnimationSpinner(ViewSpinner viewSpinner) {
        this.viewSpinner = viewSpinner;
    }

    private int round = 8;

    @Override
    public void startWheel(final View view, int wheel) {
        int degrees = 360 * round + (wheel * 30);
        RotateAnimation rotateAnimation = new RotateAnimation(0, degrees, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
        rotateAnimation.setDuration(3000);
        //rotateAnimation.setRepeatCount(PresenterAnimation.INFINITE);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setFillEnabled(true);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewSpinner.finish(view);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(rotateAnimation);
    }

    @Override
    public void startLife(final View view, int count) {
        TranslateAnimation wheel = new TranslateAnimation(0, 280, 0, -450);
        wheel.setDuration(100);
        wheel.setRepeatCount(count);
        wheel.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewSpinner.finish(view);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(wheel);

//        AnimationSet anmation = new AnimationSet(true);
//        ScaleAnimation scaleWheel = new ScaleAnimation(
//                0f, 1.5f, 0f, 1.5f,
//                Animation.RELATIVE_TO_SELF, 1.5f,
//                Animation.RELATIVE_TO_SELF, 1.5f
//        );
//        scaleWheel.setDuration(400);
//        scaleWheel.setFillAfter(true);
//        anmation.addAnimation(scaleWheel);
//        //view.startAnimation(scaleWheel);
//
//        TranslateAnimation transWheel = new TranslateAnimation(
//                Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, 0.0f,
//                Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, -150.0f
//        );
//        transWheel.setDuration(800);
//        transWheel.setFillAfter(true);
//        transWheel.setStartOffset(400);
//        anmation.addAnimation(transWheel);
//        //view.startAnimation(transWheel);
//
//        RotateAnimation rotateWheel = new RotateAnimation(
//                0, 360,
//                Animation.RELATIVE_TO_SELF, 0.5f,
//                Animation.RELATIVE_TO_SELF, 0.5f
//        );
//        rotateWheel.setDuration(1000);
//        rotateWheel.setStartOffset(1200);
//        rotateWheel.setFillAfter(true);
//        anmation.addAnimation(rotateWheel);
//        //view.startAnimation(rotateWheel);
//
//        AlphaAnimation alphaWheel = new AlphaAnimation(
//                0.5f, 0.9f
//        );
//        alphaWheel.setDuration(1000);
//        alphaWheel.setStartOffset(2200);
//        alphaWheel.setFillAfter(true);
//        anmation.addAnimation(alphaWheel);
//
//        view.startAnimation(anmation);
    }

    @Override
    public void starBlink(View view) {
        ScaleAnimation wheel = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        wheel.setDuration(500);
        wheel.setFillAfter(true);
        view.startAnimation(wheel);
    }

    @Override
    public void startZoom(final View view) {
        ScaleAnimation zoom = new ScaleAnimation(4f, 0f, 4f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        zoom.setDuration(800);
        zoom.setFillAfter(true);
        zoom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewSpinner.finish(view);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(zoom);
    }

    @Override
    public int getBonus(int wheel) {
        int Bonus = 0;
        switch (wheel) {
            case 1:
                Bonus = 15;
                break;
            case 2:
                Bonus = 7;
                break;
            case 3:
                Bonus = 40;
                break;
            case 4:
                Bonus = 17;
                break;
            case 5:
                Bonus = 10;
                break;
            case 6:
                Bonus = 80;
                break;
            case 7:
                Bonus = 5;
                break;
            case 8:
                Bonus = 25;
                break;
            case 9:
                Bonus = 60;
                break;
            case 10:
                Bonus = 20;
                break;
            case 11:
                Bonus = 3;
                break;
            case 12:
                Bonus = 100;
                break;
        }
        return Bonus;
    }
}
