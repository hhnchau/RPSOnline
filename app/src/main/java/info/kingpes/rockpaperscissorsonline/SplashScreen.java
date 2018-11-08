package info.kingpes.rockpaperscissorsonline;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import info.kingpes.rockpaperscissorsonline.Dialog.Notify.DialogNotify;
import info.kingpes.rockpaperscissorsonline.Dialog.Notify.ViewNotify;
import info.kingpes.rockpaperscissorsonline.Location.Country;
import info.kingpes.rockpaperscissorsonline.Progress.Progress;
import info.kingpes.rockpaperscissorsonline.Progress.ViewProgress;
import info.kingpes.rockpaperscissorsonline.Storage.Storage;
import info.kingpes.rockpaperscissorsonline.log.MyLog;
import info.kingpes.rockpaperscissorsonline.models.InfoSetting;
import info.kingpes.rockpaperscissorsonline.models.RealModel;
import info.kingpes.rockpaperscissorsonline.socket.AppSocket;
import info.kingpes.rockpaperscissorsonline.socket.CallbackSettings;
import info.kingpes.rockpaperscissorsonline.utils.ConnectionDetector;
import info.kingpes.rockpaperscissorsonline.utils.Key;
import info.kingpes.rockpaperscissorsonline.utils.Params;
import info.kingpes.rockpaperscissorsonline.utils.Utils;
import info.kingpes.rockpaperscissorsonline.utils.Version;

public class SplashScreen extends BaseActivity implements ViewProgress, ViewNotify {
    private ProgressBar progressBar;
    private TextView txtProgress;
    private TextView tvSplash;
    private ConnectionDetector connectionDetector;
    private Progress progress = new Progress(this);
    private InfoSetting infoSetting;
    private boolean isExist = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setScreenName("SplashScreen");
        Init();
        //Listener
        on_Setting();
    }

    @Override
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    //Init
    private void Init() {
        tvSplash = (TextView) findViewById(R.id.textView_Splash);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        txtProgress = (TextView) findViewById(R.id.textView_progressbar);
    }

    //Check Connect
    private void CheckConnect() {
        //Check Connect
        connectionDetector = new ConnectionDetector(this);
        if (!connectionDetector.isConnected()) {
            /*
            / OK
            */

            String address = Storage.getInstance(this).getAddress();
            //Check Address
            if (address == null) {
                address = Country.getCountry(this);
            }

            emit_Settings(Utils.getIdDevice(this), address);

            //Start Process
            progress.start(Params.PROCESS_SLOW);

        } else {
            //Show Alert not Connect
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    connectionDetector.Alert(
                            getString(R.string.app_name),
                            getString(R.string.no_connect),
                            getString(R.string.check),
                            ""
                    );
                }
            }, 700);
        }
    }


    @Override
    public void onProgress(int process) {
        progressBar.setProgress(process);
        txtProgress.setText(process + " %");
    }

    @Override
    public void finishProgress() {

        if (!isExist) {

            /*
            / Goto MainGame
            */
            Intent mainGame = new Intent(SplashScreen.this, MainGame.class);
            mainGame.putExtra(Utils.getIntentMainGame(), infoSetting);
            startActivity(mainGame);
            finish();

        } else {

            /*
            / Goto Register
            */
            Intent intent = new Intent(SplashScreen.this, RegisterUser.class);
            startActivity(intent);
            finish();
        }
    }

    private void showNotify(String msg, boolean exits) {
        DialogNotify dialogNotify = new DialogNotify(this, this, msg, exits);
        dialogNotify.show();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        showNotify(getString(R.string.connect_fail), false);
    }

    @Override
    public void cancelClick(boolean exit) {
        finish();
    }

    @Override
    public void okClick(boolean eixt) {
        Intent mainActivity = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(mainActivity);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        CheckConnect();
    }

    private void emit_Settings(String idDevice, String address) {
        MyLog.writeLog("EMIT_SETTINGS:-----/////----->: ");
        RealModel getInfoUser = new RealModel();
        getInfoUser.setIdDevice(idDevice);
        getInfoUser.setRoom(address);
        AppSocket.mSocket.emit(Key.SETTINGS, getInfoUser.toJSON());
    }

    private void on_Setting() {
        ((AppSocket) getApplication()).listener(new CallbackSettings() {
            @Override
            public void onListener(final Object object) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyLog.writeLog("ON_SETTINGS:-----/////----->" + object.toString());
                        Gson gson = new Gson();
                        infoSetting = gson.fromJson(object.toString(), InfoSetting.class);
                        if (infoSetting != null) {
                            //Check Version
                            String server = infoSetting.getVersion();
                            int check = Version.getInstance().check(server);
                            if (check == Version.UPDATE) {

                            } else if (check == Version.FORCE) {

                            } else {
                                //Continue
                                //Set isExist
                                isExist = infoSetting.isExist();
                                //Start Process Past
                                progress.start(Params.PROCESS_FAST);
                            }
                        }
                    }
                });
            }
        });
    }
}
