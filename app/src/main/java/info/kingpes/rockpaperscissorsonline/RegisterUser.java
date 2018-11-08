package info.kingpes.rockpaperscissorsonline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.github.nkzawa.emitter.Emitter;
import com.google.gson.Gson;

import info.kingpes.rockpaperscissorsonline.Location.Country;
import info.kingpes.rockpaperscissorsonline.Storage.Storage;
import info.kingpes.rockpaperscissorsonline.log.MyLog;
import info.kingpes.rockpaperscissorsonline.models.InfoRegister;
import info.kingpes.rockpaperscissorsonline.models.InfoResult;
import info.kingpes.rockpaperscissorsonline.socket.AppSocket;
import info.kingpes.rockpaperscissorsonline.utils.Key;
import info.kingpes.rockpaperscissorsonline.utils.Params;
import info.kingpes.rockpaperscissorsonline.utils.Utils;

public class RegisterUser extends BaseActivity implements View.OnClickListener {
    private EditText edtInput;
    private Button btnOk;
    private ImageView imgAvatar;
    String nickName;
    String address = null;

    //On Server
    private Emitter.Listener onRegister;

    {
        onRegister = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                on_Register(args[0]);
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        AppSocket.mSocket.on(Key.REGISTER, onRegister);
        edtInput = (EditText) findViewById(R.id.edittext_register_input);
        btnOk = (Button) findViewById(R.id.button_register_ok);
        btnOk.setOnClickListener(this);
        imgAvatar = (ImageView) findViewById(R.id.imageView_register_avatar);
        setScreenName("Register");
        address = Country.getCountry(this);
    }

    @Override
    public void onClick(View view) {
        nickName = edtInput.getText().toString().trim();

        if (view.getId() == R.id.button_register_ok) {
            if (nickName.equals("")) {
                edtInput.setError(getString(R.string.no_input));
                return;
            }

            emit_Register(Utils.getIdDevice(this), nickName, address);

        }
    }

    @Override
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    private void emit_Register(String idDevice, String nickName, String address) {
        MyLog.writeLog("EMIT_REGISTER:-----/////----->: ");
        if (address == null) {
            address = getString(R.string.unknown);
        }
        InfoRegister infoRegister = new InfoRegister();
        infoRegister.setIdDevice(idDevice);
        infoRegister.setNickName(nickName);
        infoRegister.setAddress(address);
        AppSocket.mSocket.emit(Key.REGISTER, infoRegister.toJSON());
    }

    private void on_Register(final Object object) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MyLog.writeLog("ON_REGISTER:-----/////----->" + object.toString());
                Gson gson = new Gson();
                InfoResult infoResult = gson.fromJson(object.toString(), InfoResult.class);

                if (infoResult.getStatus() == Params.RESULT_EXIST) {
                    edtInput.setError(getString(R.string.exist_nickname));
                } else if (infoResult.getStatus() == Params.RESULT_SUCCESS) {
                    //Store nickName, Address
                    Storage.getInstance(RegisterUser.this).setNickName(nickName);
                    Storage.getInstance(RegisterUser.this).setAddress(address);
                    Intent i = new Intent(RegisterUser.this, SplashScreen.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

}
