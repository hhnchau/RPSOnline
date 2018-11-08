package info.kingpes.rockpaperscissorsonline;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import info.kingpes.rockpaperscissorsonline.Sound.Sound;

public class MainActivity extends BaseActivity {
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setScreenName("MainActivity");

        logo = (ImageView) findViewById(R.id.imageView_logo);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, SplashScreen.class);
                startActivity(intent);
                finish();
            }
        }, 2500);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animAlpha = AnimationUtils.loadAnimation(MainActivity.this, R.anim.logo);
                logo.startAnimation(animAlpha);

                Sound.getInstance().playLogo(MainActivity.this);

            }
        }, 500);
    }

    @Override
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //sound.pause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //sound.resume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //sound.clear(this);
    }
}


