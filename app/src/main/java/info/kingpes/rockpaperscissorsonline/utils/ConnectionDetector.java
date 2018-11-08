package info.kingpes.rockpaperscissorsonline.utils;

import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

/**
 * Created by Chau Huynh on 26/11/02016.
 */

public class ConnectionDetector {
    private Context context;

    public ConnectionDetector(Context context) {
        this.context = context;
    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }

        }
        return false;
    }

    // Alert
    public void Alert(String title, String message, String btnYes, String btnNo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(btnYes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent SettingWifi = new Intent();
                        SettingWifi.setAction(Settings.ACTION_WIFI_SETTINGS);
                        context.startActivity(SettingWifi);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(btnNo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle(title);
        alert.show();
    }
}

