package info.kingpes.rockpaperscissorsonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.google.gson.Gson;

import info.kingpes.rockpaperscissorsonline.log.MyLog;
import info.kingpes.rockpaperscissorsonline.models.InfoBattle;
import info.kingpes.rockpaperscissorsonline.models.RealModel;
import info.kingpes.rockpaperscissorsonline.socket.AppSocket;
import info.kingpes.rockpaperscissorsonline.socket.CallbackSearch;
import info.kingpes.rockpaperscissorsonline.utils.Key;
import info.kingpes.rockpaperscissorsonline.utils.Utils;

public class Search extends AppCompatActivity {
    private ImageView imgSearch;
    private Button btnCancel;
    private TextView txtProgress;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search);
        Init();
        on_Enter_Wating_Room();
        EMIT_ENTER_WAITING_ROOM();
    }


    //Init
    private void Init() {
        txtProgress = (TextView) findViewById(R.id.textView_search);
        imgSearch = (ImageView) findViewById(R.id.imageView_search);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_search);
        btnCancel = (Button) findViewById(R.id.button_cancel);
        btnCancel.setText(getString(R.string.cancel));
    }

    //EMIT_ENTER_WAITING_ROOM
    private void EMIT_ENTER_WAITING_ROOM() {
        RealModel emitEnterWaitingRoom = new RealModel();
        emitEnterWaitingRoom.setIdDevice(Utils.getIdDevice(this));
        AppSocket.mSocket.emit(Key.ENTER_WAITING_ROOM, emitEnterWaitingRoom.toJSON());
        MyLog.writeLog("EMIT_ENTER_WAITING_ROOM:-----/////----->: ");
    }

    private void on_Enter_Wating_Room(){
        ((AppSocket)getApplication()).listener(new CallbackSearch() {
            @Override
            public void onListener(final Object object) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyLog.writeLog("ON_ENTER_WAITING_ROOM:-----/////----->" + object.toString());
                        Gson gson = new Gson();
                        InfoBattle infoBattle = gson.fromJson(object.toString(), InfoBattle.class);
                        Intent i = new Intent(Search.this, Battle.class);
                        i.putExtra(Utils.getIntentBattle(), infoBattle);
                        startActivity(i);
                        finish();
                    }
                });
            }
        });
    }
}
