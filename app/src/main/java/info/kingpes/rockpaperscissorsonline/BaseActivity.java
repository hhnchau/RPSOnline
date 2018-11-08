package info.kingpes.rockpaperscissorsonline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import info.kingpes.rockpaperscissorsonline.log.MyLog;

/**
 * Created by Chau Huynh on 6/4/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected String screenName = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        MyLog.writeLog(getScreenName());
    }

    public void listenerMessageChat(){

    }

    private String getScreenName() {
        return screenName;
    }

    public abstract void setScreenName(String screenName);
}
