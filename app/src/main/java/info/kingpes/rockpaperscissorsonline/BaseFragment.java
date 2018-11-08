package info.kingpes.rockpaperscissorsonline;

import android.support.v4.app.Fragment;

import info.kingpes.rockpaperscissorsonline.log.MyLog;

/**
 * Created by Chau Huynh on 6/4/2017.
 */

public abstract class BaseFragment extends Fragment {
    protected String screenName = "BaseFragment";
    @Override
    public void onStart() {
        super.onStart();
        MyLog.writeLog(getScreenName());
    }

    private String getScreenName() {
        return screenName;
    }

    public abstract void setScreenName(String screenName);
}
