package info.kingpes.rockpaperscissorsonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.nkzawa.emitter.Emitter;
import com.google.gson.Gson;

import info.kingpes.rockpaperscissorsonline.log.MyLog;
import info.kingpes.rockpaperscissorsonline.models.InfoBattle;
import info.kingpes.rockpaperscissorsonline.models.RealModel;
import info.kingpes.rockpaperscissorsonline.socket.AppSocket;
import info.kingpes.rockpaperscissorsonline.socket.CallbackAgain;
import info.kingpes.rockpaperscissorsonline.utils.Key;
import info.kingpes.rockpaperscissorsonline.utils.Utils;

/**
 * Created by Chau Huynh on 5/28/2017.
 */

public class Again extends AppCompatActivity {

    private String room;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_again);
        on_Again();

        if (getIntent().getExtras() != null) {
            room = getIntent().getStringExtra(Utils.getIntentPlayAgain());
        }

        EMIT_ENTER_AGAIN(Utils.getIdDevice(Again.this), room);
    }

    //EMIT_ENTER_WAITING_ROOM
    private void EMIT_ENTER_AGAIN(String idDevice, String room) {
        RealModel emitEnterWaitingRoom = new RealModel();
        emitEnterWaitingRoom.setIdDevice(idDevice);
        emitEnterWaitingRoom.setRoom(room);
        AppSocket.mSocket.emit(Key.ENTER_AGAIN, emitEnterWaitingRoom.toJSON());
        MyLog.writeLog("EMIT_ENTER_AGAIN:-----/////----->: ");
    }

    private void on_Again() {
        ((AppSocket) getApplication()).listener(new CallbackAgain() {
            @Override
            public void onListener(final Object object) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyLog.writeLog("ON_ENTER_AGAIN:-----/////----->" + object.toString());
                        Gson gson = new Gson();
                        InfoBattle infoBattle = gson.fromJson(object.toString(), InfoBattle.class);
                        Intent i = new Intent(Again.this, Battle.class);
                        i.putExtra(Utils.getIntentBattle(), infoBattle);
                        startActivity(i);
                        finish();
                    }
                });
            }
        });
    }
}
