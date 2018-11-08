package info.kingpes.rockpaperscissorsonline.Presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import info.kingpes.rockpaperscissorsonline.Interface.Interface.InterfaceAnimation;
import info.kingpes.rockpaperscissorsonline.Interface.View.ViewAnimation;
import info.kingpes.rockpaperscissorsonline.R;
import info.kingpes.rockpaperscissorsonline.Size.SizeScreen;
import info.kingpes.rockpaperscissorsonline.log.MyLog;
import info.kingpes.rockpaperscissorsonline.utils.Params;


/**
 * Created by Chau Huynh on 14/01/02017.
 */

public class PresenterAnimation implements InterfaceAnimation {
    private ViewAnimation viewAnimation;
    private SizeScreen sizeScreen;

    public PresenterAnimation(ViewAnimation viewAnimation) {
        this.viewAnimation = viewAnimation;
    }

    //Start IN Profile VS
    @Override
    public void vs_in_Animation(final View view) {
        Animation vs_in = AnimationUtils.loadAnimation((Context) viewAnimation, R.anim.vs_in);
        vs_in.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewAnimation.finishAnimation(view, Params.PROFILE_VS_IN);  //lan 1 khoi dong lan 2
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(vs_in);
    }

    @Override
    public void rps_home_Translate(final View view, final int rps) {
        sizeScreen = new SizeScreen((Context) viewAnimation);
        int toY = sizeScreen.getY_Animation_RPS();
        TranslateAnimation animation_home = new TranslateAnimation(0, 0, 0, -toY);
        animation_home.setDuration(100);
        animation_home.setFillAfter(true);
        animation_home.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
            @Override
            public void onAnimationStart(android.view.animation.Animation animation) {

            }

            @Override
            public void onAnimationEnd(android.view.animation.Animation animation) {
                viewAnimation.finishAnimation(view, rps);
            }

            @Override
            public void onAnimationRepeat(android.view.animation.Animation animation) {

            }
        });
        view.startAnimation(animation_home);
    }

    @Override
    public void rps_guest_Translate(final View view, final int rps) {
        sizeScreen = new SizeScreen((Context) viewAnimation);
        int toY = sizeScreen.getY_Animation_RPS();
        TranslateAnimation animation_guest = new TranslateAnimation(0, 0, 0, toY);
        animation_guest.setDuration(100);
        animation_guest.setFillAfter(true);
        animation_guest.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
            @Override
            public void onAnimationStart(android.view.animation.Animation animation) {

            }

            @Override
            public void onAnimationEnd(android.view.animation.Animation animation) {
                viewAnimation.finishAnimation(view, rps);
            }

            @Override
            public void onAnimationRepeat(android.view.animation.Animation animation) {

            }
        });
        view.startAnimation(animation_guest);
    }

    @Override
    public void icon_home_Translate(View view) {
        sizeScreen = new SizeScreen((Context) viewAnimation);
        int[] i = sizeScreen.get_Animation_Icon();
        TranslateAnimation animation_home = new TranslateAnimation(0, -i[0], 0, i[1]);//-280, 350
        animation_home.setDuration(100);
        animation_home.setFillAfter(true);
        view.startAnimation(animation_home);
    }

    @Override
    public void icon_guest_Translate(View view) {
        sizeScreen = new SizeScreen((Context) viewAnimation);
        int[] i = sizeScreen.get_Animation_Icon();
        TranslateAnimation animation_guest = new TranslateAnimation(0, i[0], 0, -i[1]);
        animation_guest.setDuration(100);
        animation_guest.setFillAfter(true);
        view.startAnimation(animation_guest);
    }

    @Override
    public void blink(View view) {
        Animation zoom = AnimationUtils.loadAnimation((Context) viewAnimation, R.anim.zoom);
        view.startAnimation(zoom);
    }

    //Show Ready
    @Override
    public void ready_Anmation(final View view) {
        TranslateAnimation round = new TranslateAnimation(0, 0, -500, 0);
        round.setDuration(2000);
        round.setFillAfter(true);

        round.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewAnimation.finishAnimation(view, Params.NEW_ROUND);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(round);
    }

    //Finish Round
    @Override
    public void result_Animation(final View view) {
        Animation result = AnimationUtils.loadAnimation((Context) viewAnimation, R.anim.txt);
        result.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewAnimation.finishAnimation(view, Params.FINISH_ROUND);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(result);
    }

    @Override
    public void search_Animation(View view) {
        Animation animAlpha = AnimationUtils.loadAnimation((Context) viewAnimation, R.anim.search);
        view.startAnimation(animAlpha);
    }


    //Profile VS out
    @Override
    public void vs_out_Animation(final View view) {
        Animation vs_out = AnimationUtils.loadAnimation((Context) viewAnimation, R.anim.vs_out);
        vs_out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewAnimation.finishAnimation(view, Params.PROFILE_VS_OUT);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(vs_out);
    }

    @Override
    public void bg1_Animation(final View view) {
        Animation bg1 = AnimationUtils.loadAnimation((Context) viewAnimation, R.anim.bg1);
        bg1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewAnimation.finishAnimation(view, -1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(bg1);
    }

    @Override
    public void profile_Animation(View view) {
        Animation toolbar = AnimationUtils.loadAnimation((Context) viewAnimation, R.anim.profile);
        view.startAnimation(toolbar);
    }

    @Override
    public void score_Animation(View view) {
        Animation score = AnimationUtils.loadAnimation((Context) viewAnimation, R.anim.score);
        view.startAnimation(score);
    }

    @Override
    public void rps_in_Animation(View view) {
        Animation rps_in = AnimationUtils.loadAnimation((Context) viewAnimation, R.anim.rps_in);
        view.startAnimation(rps_in);
    }

    @Override
    public void rps_out_Animation(View view) {
        Animation rps_out = AnimationUtils.loadAnimation((Context) viewAnimation, R.anim.rps_out);
        view.startAnimation(rps_out);
    }

    //Counter 10s
    @Override
    public void count_Animation(final View view) {
        ScaleAnimation count = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        count.setDuration(1000);
        count.setRepeatCount(9);
        count.setFillAfter(true);
        count.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewAnimation.finishAnimation(view, -1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                viewAnimation.repeatAnimation(view);
            }

        });
        view.setPivotX(50);
        view.setPivotY(50);
        view.startAnimation(count);
    }

    @Override
    public void ln_icon_in_Animation(View view) {
        Animation lnIcon_in = AnimationUtils.loadAnimation((Context) viewAnimation, R.anim.icon_in);
        view.startAnimation(lnIcon_in);
    }

    @Override
    public void ln_icon_out_Animation(final View view) {
        Animation lnIcon_out = AnimationUtils.loadAnimation((Context) viewAnimation, R.anim.icon_out);
        lnIcon_out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewAnimation.finishAnimation(view, -1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(lnIcon_out);
    }

    @Override
    public void over_Animation(final View view) {
        Animation over = AnimationUtils.loadAnimation((Context) viewAnimation, R.anim.over);
        over.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewAnimation.finishAnimation(view, -1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(over);
    }

    @Override
    public void messege_Animation(View view) {
        Animation messege = AnimationUtils.loadAnimation((Context) viewAnimation, R.anim.messege);
        view.startAnimation(messege);
    }

    @Override
    public void select_Animation(View view) {
        Animation select = AnimationUtils.loadAnimation((Context) viewAnimation, R.anim.select);
        view.startAnimation(select);
    }

    @Override
    public void no_wifi_Animation(final View view) {
        ScaleAnimation wifi = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        wifi.setDuration(300);
        wifi.setRepeatCount(3);
        wifi.setFillAfter(false);
        wifi.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewAnimation.finishAnimation(view, -1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(wifi);
    }
}
