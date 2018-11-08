package info.kingpes.rockpaperscissorsonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import info.kingpes.rockpaperscissorsonline.log.MyLog;
import info.kingpes.rockpaperscissorsonline.models.InfoUser;
import info.kingpes.rockpaperscissorsonline.models.RealModel;
import info.kingpes.rockpaperscissorsonline.socket.AppSocket;
import info.kingpes.rockpaperscissorsonline.socket.CallbackFrg_Three;
import info.kingpes.rockpaperscissorsonline.utils.Key;
import info.kingpes.rockpaperscissorsonline.utils.Utils;

/**
 * Created by Chau Huynh on 07/01/02017.
 */

public class Frg_Three extends BaseFragment implements View.OnClickListener {
    private View view;
    private Button btnBattle;
    private TextView txtName, txtLocation, txtStar, txtStar1, txtWin, txtLost, txtPosition, txtRobbinProfile;
    public static TextView txtLife;
    public static TextView txtWheel;

    private InfoUser infoUser;
    private boolean isPress = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_three, container, false);
        Init();
        on_Get_Info_User();
        setScreenName("Frg_Three");



//        Utils utils = new Utils();
//        View views = (View) view.findViewById(R.id.f_view);
//        utils.storeImageInSDCard(utils.layoutToBitmap(views));



        return view;
    }

    @Override
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    //SetProfile
    private void setProfile(InfoUser infoUser) {
        if (infoUser != null) {
            txtName.setText(infoUser.getNickName());
            txtLocation.setText(infoUser.getAddress());

            txtStar.setText("" + infoUser.getMaxChampion());
            txtWin.setText(getString(R.string.win) + "  " + infoUser.getWin());
            txtLost.setText(getString(R.string.lost) + "  " + infoUser.getLost());
            txtStar1.setText(getString(R.string.star) + "  " + infoUser.getChampion());
            txtLife.setText(getString(R.string.life) + "  " + infoUser.getLife());
            txtWheel.setText(getString(R.string.wheel) + "  " + infoUser.getSpin());
            txtPosition.setText(infoUser.getRank() + "");
            //Set Header View
            MainGame.setHeader(infoUser.getMaxChampion(), infoUser.getChampion(), infoUser.getLife());
        }
    }

    //Init
    private void Init() {
        txtName = (TextView) view.findViewById(R.id.textView_my_name);
        txtLocation = (TextView) view.findViewById(R.id.textView_my_location);
        txtStar = (TextView) view.findViewById(R.id.textView_my_star);
        txtStar1 = (TextView) view.findViewById(R.id.textView_my_star1);
        txtWin = (TextView) view.findViewById(R.id.textView_my_win);
        txtLost = (TextView) view.findViewById(R.id.textView_my_lost);
        txtLife = (TextView) view.findViewById(R.id.textView_my_life);
        txtWheel = (TextView) view.findViewById(R.id.textView_my_wheel);
        txtPosition = (TextView) view.findViewById(R.id.textView_my_position);
        txtRobbinProfile = (TextView) view.findViewById(R.id.textView_robbin_profile);
        txtRobbinProfile.setText(getString(R.string.profile));

        btnBattle = (Button) view.findViewById(R.id.battle);
        btnBattle.setText(getString(R.string.fight));
        btnBattle.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (isPress) {
            isPress = false;
            if (infoUser.getLife() > 0) {
                Intent fight = new Intent(getActivity(), Search.class);
                startActivity(fight);
            } else {
                isPress = true;
                //Toast.makeText(getActivity(), getString(R.string.not_life), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "het life show dialog", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        isPress = true;

        emit_Get_Info_User(Utils.getIdDevice(getActivity()));
    }

    private void emit_Get_Info_User(String idDevice) {
        MyLog.writeLog("EMIT_GET_INFO_USER:-----/////----->: ");
        RealModel getInfoUser = new RealModel();
        getInfoUser.setIdDevice(idDevice);
        AppSocket.mSocket.emit(Key.INFO_USER, getInfoUser.toJSON());
    }

    private void on_Get_Info_User(){
        ((AppSocket)getActivity().getApplication()).listener(new CallbackFrg_Three() {
            @Override
            public void onListener(final Object object) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyLog.writeLog("ON_GET_INFO_USER:-----/////----->" + object.toString());
                        Gson gson = new Gson();
                        infoUser = gson.fromJson(object.toString(), InfoUser.class);
                        setProfile(infoUser);
                    }
                });
            }
        });
    }
}
