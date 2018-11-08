package info.kingpes.rockpaperscissorsonline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.google.gson.Gson;

import java.util.Random;

import info.kingpes.rockpaperscissorsonline.Sound.Sound;
import info.kingpes.rockpaperscissorsonline.Util.Helper;
import info.kingpes.rockpaperscissorsonline.Spinner.AnimationSpinner;
import info.kingpes.rockpaperscissorsonline.Spinner.ViewSpinner;
import info.kingpes.rockpaperscissorsonline.log.MyLog;
import info.kingpes.rockpaperscissorsonline.models.InfoGame;
import info.kingpes.rockpaperscissorsonline.models.RealModel;
import info.kingpes.rockpaperscissorsonline.socket.AppSocket;
import info.kingpes.rockpaperscissorsonline.socket.CallbackFrg_Four;
import info.kingpes.rockpaperscissorsonline.utils.Key;
import info.kingpes.rockpaperscissorsonline.utils.Utils;

/**
 * Created by Chau Huynh on 07/01/02017.
 */

public class Frg_Four extends BaseFragment implements ViewSpinner {
    private View view;
    private ImageView imvSwap, imvLifeWheel;
    private TextView txtWheel;
    public static TextView txtMyWheel;
    private Button btnSwap;

    private AnimationSpinner animationSpinner = new AnimationSpinner(this);

    private Helper helper;
    private boolean swap = true;
    private int spin = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_four, container, false);
        Init();
        on_LuckyLife();
        setScreenName("Frg_Four");
        Rotate();

        emit_LuckyLife(Utils.getIdDevice(getActivity()));

        return view;
    }

    //Init
    private void Init() {
        helper = new Helper(getContext());
        imvSwap = (ImageView) view.findViewById(R.id.imageView_wheel);
        imvLifeWheel = (ImageView) view.findViewById(R.id.imageView_wheel_life);
        txtWheel = (TextView) view.findViewById(R.id.textView_wheel);
        txtMyWheel = (TextView) view.findViewById(R.id.textView_my_wheel);
        btnSwap = (Button) view.findViewById(R.id.button_swap);
        btnSwap.setText(getString(R.string.start));
    }

    //Rotate
    private void Rotate() {
        btnSwap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Random
                if (swap) {
                    if (spin > 0) {
                        Random random = new Random();
                        spin = random.nextInt(12) + 1;
                        if (spin == 3) {
                            spin = random.nextInt(12) + 1;
                        }
                        if (spin == 6) {
                            spin = random.nextInt(12) + 1;
                        }
                        if (spin == 9) {
                            spin = random.nextInt(12) + 1;
                        }
                        if (spin == 12) {
                            spin = random.nextInt(12) + 1;
                        }
                        animationSpinner.startWheel(imvSwap, spin);
                        swap = false;

                    } else {
                        animationSpinner.starBlink(txtMyWheel);
                        //Sound
                        Sound.getInstance().playDisableButton(getActivity());
                    }
                } else {
                    animationSpinner.starBlink(btnSwap);
                    //Sound
                    Sound.getInstance().playDisableButton(getActivity());
                }
            }
        });

    }

    private void refreshView() {

        txtMyWheel.setText(getString(R.string.you_have) + " " + spin + " " + getString(R.string.swap));

        Frg_Three.txtWheel.setText("");

        if (MainGame.bonus > 0) {
            MainGame.setViewPage(3, "N");
        }
    }

    @Override
    public void finish(View view) {
        switch (view.getId()) {
            case R.id.imageView_wheel:

                break;
            case R.id.textView_wheel:
                view.clearAnimation();
                view.setVisibility(View.GONE);

                break;
            case R.id.imageView_wheel_life:
                view.clearAnimation();
                view.setVisibility(View.GONE);

                break;
        }
    }

    @Override
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    private void emit_LuckyLife(String idDevice) {
        MyLog.writeLog("EMIT_LUCKY_LIFE:-----/////----->: ");
        RealModel getInfoUser = new RealModel();
        getInfoUser.setIdDevice(idDevice);
        AppSocket.mSocket.emit(Key.LUCKY_LIFE, getInfoUser.toJSON());
    }

    private void on_LuckyLife(){
        ((AppSocket)getActivity().getApplication()).listener(new CallbackFrg_Four() {
            @Override
            public void onListener(final Object object) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyLog.writeLog("ON_LUCKY_LIFE:-----/////----->" + object.toString());
                        Gson gson = new Gson();
                        InfoGame infoGame = gson.fromJson(object.toString(), InfoGame.class);
                    }
                });
            }
        });
    }
}
