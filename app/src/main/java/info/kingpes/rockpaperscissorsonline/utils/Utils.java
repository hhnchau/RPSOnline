package info.kingpes.rockpaperscissorsonline.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import info.kingpes.rockpaperscissorsonline.R;

/**
 * Created by Chau Huynh on 5/21/2017.
 */

public class Utils {
    public static String getIdDevice(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getIntentBattle() {
        return "INTENT_BATTLE";
    }

    public static String getIntentPlayAgain() {
        return "PLAY_AGAIN";
    }

    public static String getIntentMainGame() {
        return "MAIN_GAME";
    }

    public void storeImageInSDCard(Bitmap bitmap) {
        File outputFile = new File(Environment.getExternalStorageDirectory(), "photo_" + "XXXXX" + ".jpg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bitmap layoutToBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return b;
    }

    public static void shareFacebook(Context context, String url) {

        Uri uri = Uri.parse("file:///storage/emulated/0/DCIM/Camera/abc.jpg");
        //Uri uri = Uri.parse("file:///"+url);

        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
        share.putExtra(Intent.EXTRA_TEXT, url);
        share.putExtra(Intent.EXTRA_STREAM, uri);
        share.setType("image/*");
        share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> activityList = pm.queryIntentActivities(share, 0);
        boolean isHaveApp = false;
        for (final ResolveInfo app : activityList) {
            if (app.activityInfo.packageName.contains("com.facebook.katana")) {
                final ActivityInfo activity = app.activityInfo;
                final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
                share.setComponent(name);
                context.startActivity(share);
                isHaveApp = true;
                break;
            }
        }
        if (!isHaveApp) {
            Uri marketUri = Uri.parse("market://details?id=" + "com.facebook.katana");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            context.startActivity(marketIntent);
        }
    }
}
