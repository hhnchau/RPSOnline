package info.kingpes.rockpaperscissorsonline.Version;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import org.jsoup.Jsoup;

import java.io.IOException;

import info.kingpes.rockpaperscissorsonline.R;

/**
 * Created by Chau Huynh on 25/01/02017.
 */

public class VersionChecker extends AsyncTask<String, String, String> {

    private Context context;

    public VersionChecker(Context context) {
        this.context = context;
    }

    private String newVersion;

    @Override
    protected String doInBackground(String... params) {
        try {
            newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + context.getPackageName() + "&hl=en")
                    .timeout(30000)
                    .userAgent("Mozilla/5.0(Windows; U; WindowsNT 5.1; en-US; rv1.8.6) Gecko/20070725 firefox/2.0.0.2")
                    .referrer("http://www.google.com")
                    .get()
                    .select("div[itemprop = softwareVersion]")
                    .first()
                    .ownText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newVersion;
    }

    public String getMyVersion() {
        String versionName = null;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
            //int verCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

//    private void intentGooglePlay(){
//        try {
//            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
//        } catch (android.content.ActivityNotFoundException e) {
//            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName())));
//        }
//    }

    public void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(context.getString(R.string.last_version))
                .setCancelable(false)
                .setPositiveButton(context.getString(R.string.update), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
                        } catch (android.content.ActivityNotFoundException e) {
                            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName())));
                        }
                        dialog.dismiss();
                    }
                });
//        .setNegativeButton(context.getString(R.string.no), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                        dialog.dismiss();
//                    }
//                });
        AlertDialog alert = builder.create();
        alert.setTitle(context.getString(R.string.app_name));
        alert.show();
    }
}
