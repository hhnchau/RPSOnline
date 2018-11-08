package info.kingpes.rockpaperscissorsonline.log;

import android.util.Log;

/**
 * Created by Chau Huynh on 5/16/2017.
 */

public class MyLog {
    private static boolean log = true;
    private static String TAG = "RPS";

    public static void writeLog(String s){
        if (log) Log.d(TAG, "---> "+s);
    }
}
