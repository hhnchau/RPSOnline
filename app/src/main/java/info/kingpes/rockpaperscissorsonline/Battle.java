package info.kingpes.rockpaperscissorsonline;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.google.gson.Gson;

import java.util.List;
import java.util.Random;

import info.kingpes.rockpaperscissorsonline.Interface.View.ViewAnimation;
import info.kingpes.rockpaperscissorsonline.Interface.View.ViewComputer;
import info.kingpes.rockpaperscissorsonline.Interface.View.ViewTimerTask;
import info.kingpes.rockpaperscissorsonline.Presenter.PresenterAnimation;
import info.kingpes.rockpaperscissorsonline.Presenter.PresenterComputer;
import info.kingpes.rockpaperscissorsonline.Presenter.PresenterTimerTask;
import info.kingpes.rockpaperscissorsonline.Sound.Sound;
import info.kingpes.rockpaperscissorsonline.log.MyLog;
import info.kingpes.rockpaperscissorsonline.models.RealModel;
import info.kingpes.rockpaperscissorsonline.models.InfoUserBattle;
import info.kingpes.rockpaperscissorsonline.models.InfoBattle;
import info.kingpes.rockpaperscissorsonline.socket.AppSocket;
import info.kingpes.rockpaperscissorsonline.socket.CallbackBattle;
import info.kingpes.rockpaperscissorsonline.utils.Key;
import info.kingpes.rockpaperscissorsonline.utils.Params;
import info.kingpes.rockpaperscissorsonline.utils.Utils;

public class Battle extends AppCompatActivity implements View.OnClickListener, ViewAnimation, ViewTimerTask {
    private Button btnRock, btnPaper, btnScrissor, btnIconClose, btnAgain, btnNext;
    private ImageView imgWifi, imgRock, imgPaper, imgScrissor, imgShowIcon, imgUnknownHome, imgUnknownGuest, imgIconGuest, imgIconHome;
    private LinearLayout lnVS, lnBG1, lnStarLife, lnProfileGuest, lnRPS, lnIcon, lnCircular, lnOver, lnBG2;
    private TextView txtProfileName, txtProfileLocation, txtVisitorLife, txtVisitorStar, txtHomeLife, txtHomeStar, txtReady, txtProgressBar, txtNameHome, txtNameGuset, txtLocationHome, txtLocationGuset, txtStarHome, txtStarGuset, txtMatchOver;
    private TextView txtRobbinVsVisitor, txtRobbinVsHome, txtRobbinMatchOver;
    private ImageView imgFlagVisitor, imgFlagHome;
    private ImageView icon_1, icon_2, icon_3, icon_4, icon_5, icon_6, icon_7, icon_8, flag_1, flag_2, flag_3, flag_4, flag_5, flag_6, flag_7, flag_8;

    private PresenterAnimation presenterAnimation = new PresenterAnimation(this);
    private PresenterTimerTask pesenterTimerTask = new PresenterTimerTask(this);

    private InfoBattle infoBattle;
    private List<InfoUserBattle> infoUserBattle;

    private static int RPS[] = {0, R.drawable.rock_green, R.drawable.paper_green, R.drawable.scrissror_green};
    private static int ICON[] = {0, R.drawable.icon_1, R.drawable.icon_2, R.drawable.icon_3, R.drawable.icon_4, R.drawable.icon_5, R.drawable.icon_6, R.drawable.icon_7, R.drawable.icon_8};
    private static int FLAG[] = {R.drawable.flags, R.drawable.flag};

    private  int home = 0;
    private  int guest = 1;
    private String idComputer = null;

    //Enable select button RPS
    private boolean enable_Select_RPS = false;
    //Store Counter round of Match
    private int counter_Set = 1;
    //Status None = -1;
    private static final int NONE = -1;

    //Store Room
    private String room;
    //Counter 10s
    private int counter_10s = 1;
    //Enable Random
    private boolean random = true;
    //For Open or Hide RPS
    private int before = NONE;

    //For Again
    private boolean enable_Again = true;
    //For Confirm
    private boolean confirm_Again = false;

    private boolean onSound = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_battle);
        Init();
        onSocket();
        MyLog.writeLog("Battle-----> Create");
        Sound.getInstance().playBgBattle(this);
        setViewProfileVS();
        startVS();
    }

    //Show Panel VS
    private void setViewProfileVS() {
        MyLog.writeLog("Battle-----> Set View Profile VS");

        //Set Title Guest
        txtRobbinVsVisitor.setText(getString(R.string.visitor));
        //Set Title Home
        txtRobbinVsHome.setText(getString(R.string.home));

        //Set Name Home
        txtNameHome.setText(infoUserBattle.get(home).getNickName());
        //Set Location Home
        txtLocationHome.setText(infoUserBattle.get(home).getAddress());
        //Set Star Home
        txtStarHome.setText(infoUserBattle.get(home).getStar() + "");

        //Set Name Guest
        txtNameGuset.setText(infoUserBattle.get(guest).getNickName());
        //Set Location Guest
        txtLocationGuset.setText(infoUserBattle.get(guest).getAddress());
        //Set Star Guest
        txtStarGuset.setText(infoUserBattle.get(guest).getStar() + "");
    }

    //Show VS
    private void startVS() {
        MyLog.writeLog("Battle--------->Show Animation VS IN");
        lnVS.setVisibility(View.VISIBLE);
        //VS IN
        presenterAnimation.vs_in_Animation(lnVS);
    }

    //Show Background
    private void showBG1() {
        MyLog.writeLog("Battle--------->Show Animation Background Main");
        lnBG1.setVisibility(View.VISIBLE);
        presenterAnimation.bg1_Animation(lnBG1);
    }

    //Show profile
    private void showProfileGuest() {
        MyLog.writeLog("Battle--------->Show Animation Profile Guest");
        //Info
        //Set Name Guest
        txtProfileName.setText(infoUserBattle.get(guest).getNickName());
        //Set Location
        txtProfileLocation.setText(infoUserBattle.get(guest).getAddress());
        lnProfileGuest.setVisibility(View.VISIBLE);
        presenterAnimation.profile_Animation(lnProfileGuest);
        MyLog.writeLog("Battle--------->Stop Animation Profile Guest");
        //Score
        show_Score();
    }

    //Hide Profile when finish match
    private void hideProfileGuest() {
        MyLog.writeLog("Battle--------->Hide Animation Profile Guest");
        lnProfileGuest.clearAnimation();
        lnProfileGuest.setVisibility(View.GONE);
        MyLog.writeLog("Battle--------->Stop Hide Animation Profile Guest");
    }

    //Show Score 2 user
    private void show_Score() {
        MyLog.writeLog("Battle--------->Show Animation Score");
        //Set Life
        txtVisitorLife.setText(infoUserBattle.get(guest).getLife() + "");
        //Set Star
        txtVisitorStar.setText(infoUserBattle.get(guest).getMaxChampion() + "/" + infoUserBattle.get(guest).getChampion());

        //Set Life
        txtHomeLife.setText(infoUserBattle.get(home).getLife() + "");
        //SetStar
        txtHomeStar.setText(infoUserBattle.get(home).getMaxChampion() + "/" + infoUserBattle.get(home).getChampion());
        lnStarLife.setVisibility(View.VISIBLE);
        presenterAnimation.score_Animation(lnStarLife);
        MyLog.writeLog("Battle--------->Stop Animation Score");
    }

    //Hide Score When finish match
    private void hideScore() {
        MyLog.writeLog("Battle--------->Hide Animation Score");
        lnStarLife.clearAnimation();
        lnStarLife.setVisibility(View.GONE);
        MyLog.writeLog("Battle--------->Stop Hide Animation Score");
    }

    //
    private void showButtonRPS() {
        MyLog.writeLog("Battle--------->Show Animation Button RPS");
        lnRPS.setVisibility(View.VISIBLE);
        presenterAnimation.rps_in_Animation(lnRPS);
        MyLog.writeLog("Battle--------->Stop Animation Button RPS");
    }

    //RPS IN
    private void inRPS() {
        MyLog.writeLog("Battle--------->RPS --->In");
        lnRPS.clearAnimation();
        lnRPS.setVisibility(View.VISIBLE);
        presenterAnimation.rps_in_Animation(lnRPS);
        lnIcon.clearAnimation();
        lnIcon.setVisibility(View.VISIBLE);
        presenterAnimation.ln_icon_out_Animation(lnIcon);
        MyLog.writeLog("Battle--------->RPS --->In------>Stop");
    }

    //RPS OUT
    private void outRPS() {
        MyLog.writeLog("Battle--------->RPS --->Out");
        lnRPS.clearAnimation();
        lnRPS.setVisibility(View.VISIBLE);
        presenterAnimation.rps_out_Animation(lnRPS);
        lnIcon.clearAnimation();
        lnIcon.setVisibility(View.VISIBLE);
        presenterAnimation.ln_icon_in_Animation(lnIcon);
        MyLog.writeLog("Battle--------->RPS --->Out--->Stop");
    }

    //Set Default
    private void setDefaultRPS() {
        MyLog.writeLog("Battle--------->Clear RPS");
        //hide btn Rock
        btnRock.setVisibility(View.INVISIBLE);
        //move to default
        imgRock.setTranslationY(0f);
        btnPaper.setVisibility(View.INVISIBLE);
        imgPaper.setTranslationY(0f);
        btnScrissor.setVisibility(View.INVISIBLE);
        imgScrissor.setTranslationY(0f);
    }

    //Emit Join
    private void EMIT_JOIN(String room) {
        RealModel join = new RealModel();
        join.setIdDevice(Utils.getIdDevice(this));
        join.setRoom(room);
        AppSocket.mSocket.emit(Key.JOIN, join.toJSON());
        MyLog.writeLog("EMIT_JOIN:-----/////----->: ");
    }

    //Emit RPS
    private void EMIT_RPS(String idDevice, String room, int rps) {
        RealModel emitRps = new RealModel();
        emitRps.setIdDevice(idDevice);
        emitRps.setRoom(room);
        emitRps.setValue(rps);
        AppSocket.mSocket.emit(Key.RPS, emitRps.toJSON());
        MyLog.writeLog("EMIT_RPS:-----/////----->");
    }

    //Emit ICON
    private void EMIT_ICON(String idDevice, String room, int icon) {
        RealModel emitIcon = new RealModel();
        emitIcon.setIdDevice(idDevice);
        emitIcon.setRoom(room);
        emitIcon.setValue(icon);
        AppSocket.mSocket.emit(Key.ICON, emitIcon.toJSON());
        MyLog.writeLog("EMIT_ICON:-----/////----->");
    }

    //Emit AGAIN
    private void EMIT_AGAIN(String idDevice, String room) {
        RealModel again = new RealModel();
        again.setIdDevice(idDevice);
        again.setRoom(room);
        AppSocket.mSocket.emit(Key.AGAIN, again.toJSON());
        MyLog.writeLog("EMIT_AGAIN:-----/////----->");
    }

    //Emit OKAGAIN
    private void EMIT_CONFIRM_AGAIN(String idDevice, String room) {
        RealModel again = new RealModel();
        again.setIdDevice(idDevice);
        again.setRoom(room);
        AppSocket.mSocket.emit(Key.CONFIRM_AGAIN, again.toJSON());
        MyLog.writeLog("CONFIRM_AGAIN:-----/////----->");
    }

    //Emit LEAVE
    private void EMIT_LEAVE(String idDevice, String room) {
        RealModel leave = new RealModel();
        leave.setIdDevice(idDevice);
        leave.setRoom(room);
        AppSocket.mSocket.emit(Key.LEAVE, leave.toJSON());
        MyLog.writeLog("EMIT_LEAVE:-----/////----->");
    }

    //Move Home RPS
    private void moveUnknownHomeRps(int rps) {
        MyLog.writeLog("Battle------>Move Home Rps: -->" + rps);
        imgUnknownHome.setVisibility(View.VISIBLE);
        imgUnknownHome.setImageResource(R.drawable.unknown_green);
        presenterAnimation.rps_home_Translate(imgUnknownHome, rps);
    }

    //Move Guest RPS
    private void moveUnknownGuestRps(int rps) {
        MyLog.writeLog("Battle------>Move Guest Rps: -->" + rps);
        imgUnknownGuest.setVisibility(View.VISIBLE);
        imgUnknownGuest.setImageResource(R.drawable.unknown_green);
        presenterAnimation.rps_guest_Translate(imgUnknownGuest, rps);
    }

    //Move Home Icon
    private void moveHomeIcon(int icon) {
        MyLog.writeLog("Battle------>Move Home Icon: -->" + icon);
        imgIconHome.setVisibility(View.VISIBLE);
        imgIconHome.setImageResource(ICON[icon]);
        presenterAnimation.icon_home_Translate(imgIconHome);
        pesenterTimerTask.startTimer(3, imgIconHome);
    }

    //Move Guest Icon
    private void moveGuestIcon(int icon) {
        MyLog.writeLog("Battle------>Move Guest Icon: -->" + icon);
        imgIconGuest.setVisibility(View.VISIBLE);
        imgIconGuest.setImageResource(ICON[icon]);
        presenterAnimation.icon_guest_Translate(imgIconGuest);
        pesenterTimerTask.startTimer(3, imgIconGuest);
    }

    //Show message
    private void showReady() {
        MyLog.writeLog("Battle--------->Show Animation Ready");
        txtReady.setVisibility(View.VISIBLE);
        txtReady.setText("Vong" + counter_Set);
        presenterAnimation.ready_Anmation(txtReady);
    }

    //Use for User not select RPS
    private void randomSelect() {
        if (random) {
            //Disable Select Rps
            enable_Select_RPS = false;

            MyLog.writeLog("Battle-----> Start Random");
            //Random
            Random random = new Random();
            int r = random.nextInt(3) + 1;
            MyLog.writeLog("Battle-----> Stop Random");
            //Set Default RPS
            setDefaultRPS();
            //Emit to Server
            EMIT_RPS(Utils.getIdDevice(this), room, r);
        }
    }

    //Counter 10s
    private void startCounter() {
        MyLog.writeLog("Battle ---------> Start Counter");
        lnCircular.setVisibility(View.VISIBLE);
        txtProgressBar.setText(counter_10s + "");
        presenterAnimation.count_Animation(txtProgressBar);
    }

    private void showWifi() {
        imgWifi.setVisibility(View.VISIBLE);
        presenterAnimation.no_wifi_Animation(imgWifi);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //btn Rock
            case R.id.button_rock:
                //clear default img Rock
                imgRock.setTranslationY(0f);
                //hide btn Rock
                view.setVisibility(View.INVISIBLE);
                //emit to Server
                EMIT_RPS(Utils.getIdDevice(this), room, Params.ROCK);
                //disable select image rps
                enable_Select_RPS = false;
                //disable random
                random = false;
                //clear Counter
                txtProgressBar.clearAnimation();
                //clear Counter
                lnCircular.setVisibility(View.GONE);
                //Set sound
                if (onSound) {
                    Sound.getInstance().playRPSMove(this);
                }
                break;
            //btn Papaer
            case R.id.button_paper: //
                imgPaper.setTranslationY(0f);
                view.setVisibility(View.INVISIBLE);
                EMIT_RPS(Utils.getIdDevice(this), room, Params.PAPER);
                enable_Select_RPS = false;
                //disable random
                random = false;
                //Clear Counter
                txtProgressBar.clearAnimation();
                lnCircular.setVisibility(View.GONE);
                if (onSound) {
                    Sound.getInstance().playRPSMove(this);
                }
                break;
            //btn Scissor
            case R.id.button_scissor: //
                imgScrissor.setTranslationY(0f);
                view.setVisibility(View.INVISIBLE);
                EMIT_RPS(Utils.getIdDevice(this), room, Params.SCISSOR);
                enable_Select_RPS = false;
                //disable random
                random = false;
                //Clear Counter
                txtProgressBar.clearAnimation();
                lnCircular.setVisibility(View.GONE);
                if (onSound) {
                    Sound.getInstance().playRPSMove(this);
                }
                break;
            //img Rock
            case R.id.imageView_rock:
                //Hide Paper
                btnPaper.setVisibility(View.INVISIBLE);
                //Hide Scissor
                btnScrissor.setVisibility(View.INVISIBLE);
                //Set default animation img Paper
                imgPaper.setTranslationY(0f);
                //Set default animation img Scissor
                imgScrissor.setTranslationY(0f);
                //Check anable Select
                if (enable_Select_RPS) {
                    //move img Rock 20f
                    imgRock.setTranslationY(-20f);
                    //Show btn Rock
                    btnRock.setVisibility(View.VISIBLE);
                    //Set Sound
                    Sound.getInstance().playClickButton(this);
                } else {
                    //Set Animation not enable
                    presenterAnimation.select_Animation(imgRock);
                    //Set sound not enable
                    Sound.getInstance().playDisableButton(this);
                }
                break;
            //img Papaer
            case R.id.imageView_paper:
                btnRock.setVisibility(View.INVISIBLE);
                imgRock.setTranslationY(0f);
                btnScrissor.setVisibility(View.INVISIBLE);
                imgScrissor.setTranslationY(0f);
                if (enable_Select_RPS) {
                    imgPaper.setTranslationY(-20f);
                    btnPaper.setVisibility(View.VISIBLE);
                    Sound.getInstance().playClickButton(this);
                } else {
                    presenterAnimation.select_Animation(imgPaper);
                    Sound.getInstance().playDisableButton(this);
                }
                break;
            //img Scissor
            case R.id.imageView_scrissor: //Scissor
                btnRock.setVisibility(View.INVISIBLE);
                imgRock.setTranslationY(0f);
                btnPaper.setVisibility(View.INVISIBLE);
                imgPaper.setTranslationY(0f);
                if (enable_Select_RPS) {
                    btnScrissor.setVisibility(View.VISIBLE);
                    imgScrissor.setTranslationY(-20f);
                    Sound.getInstance().playClickButton(this);
                } else {
                    presenterAnimation.select_Animation(imgScrissor);
                    Sound.getInstance().playDisableButton(this);
                }
                break;
            //Show Icon
            case R.id.imageView_show_icon:
                Sound.getInstance().playClickButton(this);
                setDefaultRPS();
                outRPS();
                break;
            //Close Icon
            case R.id.button_icon_close:
                Sound.getInstance().playClickButton(this);
                inRPS();
                break;
            //Icon1
            case R.id.icon_1:
                EMIT_ICON(Utils.getIdDevice(this), room, Params.ICON1);
                if (onSound) {
                    Sound.getInstance().playIconMove(this);
                }
                inRPS();
                break;
            //Icon2
            case R.id.icon_2:
                EMIT_ICON(Utils.getIdDevice(this), room, Params.ICON2);
                if (onSound) {
                    Sound.getInstance().playIconMove(this);
                }
                inRPS();
                break;
            //Icon3
            case R.id.icon_3:
                EMIT_ICON(Utils.getIdDevice(this), room, Params.ICON3);
                if (onSound) {
                    Sound.getInstance().playIconMove(this);
                }
                inRPS();
                break;
            //Icon4
            case R.id.icon_4:
                EMIT_ICON(Utils.getIdDevice(this), room, Params.ICON4);
                if (onSound) {
                    Sound.getInstance().playIconMove(this);
                }
                inRPS();
                break;
            //Icon5
            case R.id.icon_5:
                EMIT_ICON(Utils.getIdDevice(this), room, Params.ICON5);
                if (onSound) {
                    Sound.getInstance().playIconMove(this);
                }
                inRPS();
                break;
            //Icon6
            case R.id.icon_6:
                EMIT_ICON(Utils.getIdDevice(this), room, Params.ICON6);
                if (onSound) {
                    Sound.getInstance().playIconMove(this);
                }
                inRPS();
                break;
            //Icon7
            case R.id.icon_7:
                EMIT_ICON(Utils.getIdDevice(this), room, Params.ICON7);
                if (onSound) {
                    Sound.getInstance().playIconMove(this);
                }
                inRPS();
                break;
            //Icon8
            case R.id.icon_8:
                EMIT_ICON(Utils.getIdDevice(this), room, Params.ICON8);
                if (onSound) {
                    Sound.getInstance().playIconMove(this);
                }
                inRPS();
                break;
            //btn Again
            case R.id.button_again:
                if (confirm_Again) {
                    EMIT_CONFIRM_AGAIN(Utils.getIdDevice(this), room);
                } else {
                    if (enable_Again && idComputer == null) {
                        enable_Again = false;
                        //Emit to Server
                        EMIT_AGAIN(Utils.getIdDevice(this), room);

                    } else {
                        presenterAnimation.blink(btnAgain);
                        Sound.getInstance().playDisableButton(this);
                    }
                }

                break;
            //btn Next
            case R.id.button_next:
                Sound.getInstance().playClickButton(this);
                //Emit to Server
                EMIT_LEAVE(Utils.getIdDevice(this), room);

                break;
        }
    }

    @Override
    public void finishAnimation(View view, final int status) {
        switch (view.getId()) {
            //Pro file VS
            case R.id.linnear_vs:
                //Finish Profile VS in
                if (status == Params.PROFILE_VS_IN) {
                    MyLog.writeLog("Battle--------->Stop Animation VS PROFILE_VS_IN");

                    //EMIT JOIN TO SERVER, SERVER RETURN START VS OUT
                    EMIT_JOIN(room);

//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            //Start Profile VS out
//                            MyLog.writeLog("Battle --------> Show Animation VS OUT");
//                            presenterAnimation.vs_out_Animation(lnVS);
//                        }
//                    }, 1000);

                    //Check Sound
                    if (onSound) {
                        Sound.getInstance().playVs(this);
                    }
                    //Finish Profile VS out
                } else if (status == Params.PROFILE_VS_OUT) {
                    MyLog.writeLog("Battle--------->Stop Animation VS PROFILE_VS_OUT");
                    view.clearAnimation();
                    view.setVisibility(View.GONE);
                    //Show Background Main
                    showBG1();
                }
                break;
            //Finish Show Background main
            case R.id.linnear_bg1:
                MyLog.writeLog("Battle--------->Stop Animation Background Main");
                //Show Profile Guest
                showProfileGuest();
                //Show Button RPS
                showButtonRPS();
                //Show Ready Set
                showReady();
                break;
            //Finish Ready
            case R.id.textView_ready:
                view.clearAnimation();
                view.setVisibility(View.GONE);
                //New Round
                if (status == Params.NEW_ROUND) {

                    MyLog.writeLog("Battle--------->Stop Animation Ready NEW ROUND");
                    //Enable Select RPS
                    enable_Select_RPS = true;
                    //Start Counter
                    //startCounter();

                    /*
                    /START COMPUTER
                    */
                    if (idComputer != null) {
                        getComputer();
                    }

                    //Finish Round
                } else if (status == Params.FINISH_ROUND) {
                    MyLog.writeLog("Battle--------->Stop Animation Ready FINISH ROUND");
                    //Renew Ser
                    renewSet();
                    //Show Couter next Set
                    counter_Set++;
                    showReady();
                }
                break;
            //Finish counter
            case R.id.textView_counter:
                MyLog.writeLog("Battle---------->Stop Counter");
                //clear counter
                lnCircular.setVisibility(View.GONE);
                txtProgressBar.setText(null);
                //Enable Timeout
                randomSelect();
                break;
            // FINISH MOVE HOME RPS
            case R.id.imageView_unknown_home:
                MyLog.writeLog("Battle----->Move Home RPS Finish");
                //Check Open or Hide
                if (before == NONE) {
                    //Store RPS
                    before = status;
                } else {
                    imgUnknownHome.setImageResource(RPS[status]);
                    imgUnknownGuest.setImageResource(RPS[before]);
                }
                break;
            // FINISH MOVE GUEST RPS
            case R.id.imageView_unknown_guest:
                MyLog.writeLog("Battle----->Move Guest RPS Finish");
                //Check Open or Hide
                if (before == NONE) {
                    //Store RPS
                    before = status;
                } else {
                    imgUnknownHome.setImageResource(RPS[before]);
                    imgUnknownGuest.setImageResource(RPS[status]);
                }
                break;
            case R.id.linnear_icon:
                view.clearAnimation();
                view.setVisibility(View.GONE);
                break;
            //Image Wifi
            case R.id.imageView_signal_wifi:
                imgWifi.clearAnimation();
                view.setVisibility(View.GONE);
                //check no select guest then show match over win
                //Sound
                Sound.getInstance().playMatchWin(this);
                //send to guest lost
                //set again = leafe a room
                enable_Again = true;
                break;
        }
    }

    @Override
    public void repeatAnimation(View view) {
        if (view.getId() == R.id.textView_counter) {
            counter_10s++;
            txtProgressBar.setText(counter_10s + "");
            MyLog.writeLog("Battle------>Counter: " + counter_10s);
        }
    }

    //Hide icon
    @Override
    public void timerFinish(View view) {
        switch (view.getId()) {
            case R.id.imageView_icon_home:
                view.clearAnimation();
                view.setVisibility(View.INVISIBLE);
                break;

            case R.id.imageView_icon_guest:
                view.clearAnimation();
                view.setVisibility(View.INVISIBLE);
                break;
        }
    }

    //Set Flag 1-8
    private void setFlag18(int flag) {
        if (flag == Params.FLAG_WIN) {
            flag_8.setImageResource(FLAG[Params.FLAG_WIN]);
            flag_1.setImageResource(FLAG[Params.FLAG_LOST]);
        } else {
            flag_8.setImageResource(FLAG[Params.FLAG_LOST]);
            flag_1.setImageResource(FLAG[Params.FLAG_WIN]);
        }
        presenterAnimation.blink(flag_1);
        presenterAnimation.blink(flag_8);
    }

    //Set Flag 2-7
    private void setFlag27(int flag) {
        if (flag == Params.FLAG_WIN) {
            flag_7.setImageResource(FLAG[Params.FLAG_WIN]);
            flag_2.setImageResource(FLAG[Params.FLAG_LOST]);
        } else {
            flag_7.setImageResource(FLAG[Params.FLAG_LOST]);
            flag_2.setImageResource(FLAG[Params.FLAG_WIN]);
        }
        presenterAnimation.blink(flag_2);
        presenterAnimation.blink(flag_7);
    }

    //Set Flag 3-6
    private void setFlag36(int flag) {
        if (flag == Params.FLAG_WIN) {
            flag_6.setImageResource(FLAG[Params.FLAG_WIN]);
            flag_3.setImageResource(FLAG[Params.FLAG_LOST]);
        } else {
            flag_6.setImageResource(FLAG[Params.FLAG_LOST]);
            flag_3.setImageResource(FLAG[Params.FLAG_WIN]);
        }
        presenterAnimation.blink(flag_3);
        presenterAnimation.blink(flag_6);
    }

    //Set Flag 4-5
    private void setFlag45(int flag) {
        if (flag == Params.FLAG_WIN) {
            flag_5.setImageResource(FLAG[Params.FLAG_WIN]);
            flag_4.setImageResource(FLAG[Params.FLAG_LOST]);
        } else {
            flag_5.setImageResource(FLAG[Params.FLAG_LOST]);
            flag_4.setImageResource(FLAG[Params.FLAG_WIN]);
        }
        presenterAnimation.blink(flag_4);
        presenterAnimation.blink(flag_5);
    }

    //Set Flag WIn
    private void setFlagWin(int position) {
        if (position == 1) {
            setFlag45(Params.FLAG_WIN);
        } else if (position == 2) {
            setFlag36(Params.FLAG_WIN);
        } else if (position == 3) {
            setFlag27(Params.FLAG_WIN);
        } else if (position == 4) {
            setFlag18(Params.FLAG_WIN);
        }
    }

    //Set Flag Lost
    private void setFlagLost(int position) {
        if (position == 1) {
            setFlag45(Params.FLAG_LOST);
        } else if (position == 2) {
            setFlag36(Params.FLAG_LOST);
        } else if (position == 3) {
            setFlag27(Params.FLAG_LOST);
        } else if (position == 4) {
            setFlag18(Params.FLAG_LOST);
        }
    }

    //Clear ALl Flag
    private void clearAllFlag() {
        flag_1.setImageResource(0);
        flag_2.setImageResource(0);
        flag_3.setImageResource(0);
        flag_4.setImageResource(0);
        flag_5.setImageResource(0);
        flag_6.setImageResource(0);
        flag_7.setImageResource(0);
        flag_8.setImageResource(0);
    }

    //Clear 2 button RPS result
    private void hideRPSResult() {
        imgUnknownGuest.setImageResource(0);
        imgUnknownGuest.setVisibility(View.GONE);
        imgUnknownHome.setImageResource(0);
        imgUnknownHome.setVisibility(View.GONE);
    }

    //Show message win, lost draw
    private void show_Result_Set(String txt) {
        txtReady.setVisibility(View.VISIBLE);
        txtReady.setText(txt);
        presenterAnimation.result_Animation(txtReady);
    }

    //Show Panel match over
    private void show_Result_Match(String txt) {
        lnOver.setVisibility(View.VISIBLE);
        lnBG2.setVisibility(View.VISIBLE);
        txtRobbinMatchOver.setText(getString(R.string.match_over));
        txtMatchOver.setText(txt);
        presenterAnimation.over_Animation(lnOver);
    }

    //Renew Set
    private void renewSet() {
        enable_Select_RPS = false;
        hideRPSResult();
        counter_10s = 1;
        random = true;
        before = NONE;
    }

    //Renew Match
    private void renewMatch() {
        counter_Set = 1;
        renewSet();
        clearAllFlag();
        hideScore();
        hideProfileGuest();

    }

    //Show BodyMessage leave room, again...
    private void show_Message(final String msg) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                txtReady.setVisibility(View.VISIBLE);
                txtReady.setTextSize(30f);
                txtReady.setText(msg);
                presenterAnimation.messege_Animation(txtReady);
            }
        }, 1);
    }

    //Init
    private void Init() {
        lnVS = (LinearLayout) findViewById(R.id.linnear_vs);
        lnBG1 = (LinearLayout) findViewById(R.id.linnear_bg1);
        lnProfileGuest = (LinearLayout) findViewById(R.id.linnear_profile_guest);
        lnStarLife = (LinearLayout) findViewById(R.id.linnear_star_life);
        txtVisitorLife = (TextView) findViewById(R.id.textView_visitor_life);
        txtVisitorStar = (TextView) findViewById(R.id.textView_visitor_star);
        txtHomeLife = (TextView) findViewById(R.id.textView_home_life);
        txtHomeStar = (TextView) findViewById(R.id.textView_home_star);
        lnIcon = (LinearLayout) findViewById(R.id.linnear_icon);
        lnRPS = (LinearLayout) findViewById(R.id.linnear_rps);
        lnOver = (LinearLayout) findViewById(R.id.linnear_gameover);
        lnBG2 = (LinearLayout) findViewById(R.id.linnear_bg2);
        //progressBar = (ProgressBar) findViewById(R.id.circular_progress_bar);
        imgWifi = (ImageView) findViewById(R.id.imageView_signal_wifi);
        txtProgressBar = (TextView) findViewById(R.id.textView_counter);
        txtReady = (TextView) findViewById(R.id.textView_ready);
        lnCircular = (LinearLayout) findViewById(R.id.linnear_circular_progress);
        imgUnknownHome = (ImageView) findViewById(R.id.imageView_unknown_home);
        imgUnknownGuest = (ImageView) findViewById(R.id.imageView_unknown_guest);
        imgIconHome = (ImageView) findViewById(R.id.imageView_icon_home);
        imgIconGuest = (ImageView) findViewById(R.id.imageView_icon_guest);
        btnIconClose = (Button) findViewById(R.id.button_icon_close);
        btnIconClose.setOnClickListener(this);
        imgShowIcon = (ImageView) findViewById(R.id.imageView_show_icon);
        imgShowIcon.setOnClickListener(this);

        txtProfileName = (TextView) findViewById(R.id.textView_guest_name);
        txtProfileLocation = (TextView) findViewById(R.id.textView_guest_location);

        txtRobbinVsVisitor = (TextView) findViewById(R.id.textView_robbin_vs_visitor);
        txtRobbinVsHome = (TextView) findViewById(R.id.textView_robbin_vs_home);
        txtRobbinMatchOver = (TextView) findViewById(R.id.textView_robbin_match_over);

        imgFlagVisitor = (ImageView) findViewById(R.id.imageView_flag_national_visitor);
        imgFlagHome = (ImageView) findViewById(R.id.imageView_flag_national_home);

        icon_1 = (ImageView) findViewById(R.id.icon_1);
        icon_1.setOnClickListener(this);
        icon_2 = (ImageView) findViewById(R.id.icon_2);
        icon_2.setOnClickListener(this);
        icon_3 = (ImageView) findViewById(R.id.icon_3);
        icon_3.setOnClickListener(this);
        icon_4 = (ImageView) findViewById(R.id.icon_4);
        icon_4.setOnClickListener(this);
        icon_5 = (ImageView) findViewById(R.id.icon_5);
        icon_5.setOnClickListener(this);
        icon_6 = (ImageView) findViewById(R.id.icon_6);
        icon_6.setOnClickListener(this);
        icon_7 = (ImageView) findViewById(R.id.icon_7);
        icon_7.setOnClickListener(this);
        icon_8 = (ImageView) findViewById(R.id.icon_8);
        icon_8.setOnClickListener(this);

        flag_1 = (ImageView) findViewById(R.id.flag_1);
        flag_2 = (ImageView) findViewById(R.id.flag_2);
        flag_3 = (ImageView) findViewById(R.id.flag_3);
        flag_4 = (ImageView) findViewById(R.id.flag_4);
        flag_5 = (ImageView) findViewById(R.id.flag_5);
        flag_6 = (ImageView) findViewById(R.id.flag_6);
        flag_7 = (ImageView) findViewById(R.id.flag_7);
        flag_8 = (ImageView) findViewById(R.id.flag_8);

        btnRock = (Button) findViewById(R.id.button_rock);
        btnRock.setVisibility(View.GONE);
        btnRock.setOnClickListener(this);
        btnPaper = (Button) findViewById(R.id.button_paper);
        btnPaper.setVisibility(View.GONE);
        btnPaper.setOnClickListener(this);
        btnScrissor = (Button) findViewById(R.id.button_scissor);
        btnScrissor.setVisibility(View.GONE);
        btnScrissor.setOnClickListener(this);
        imgRock = (ImageView) findViewById(R.id.imageView_rock);
        imgRock.setOnClickListener(this);
        imgPaper = (ImageView) findViewById(R.id.imageView_paper);
        imgPaper.setOnClickListener(this);
        imgScrissor = (ImageView) findViewById(R.id.imageView_scrissor);
        imgScrissor.setOnClickListener(this);

        txtNameHome = (TextView) findViewById(R.id.textView_name_home);
        txtNameGuset = (TextView) findViewById(R.id.textView_name_guest);
        txtLocationHome = (TextView) findViewById(R.id.textView_location_home);
        txtLocationGuset = (TextView) findViewById(R.id.textView_location_guest);

        txtStarHome = (TextView) findViewById(R.id.textView_star_home);
        txtStarGuset = (TextView) findViewById(R.id.textView_star_guest);
        txtMatchOver = (TextView) findViewById(R.id.textView_match_over);
        btnAgain = (Button) findViewById(R.id.button_again);
        btnAgain.setOnClickListener(this);
        btnNext = (Button) findViewById(R.id.button_next);
        btnNext.setOnClickListener(this);

        //Get Intent
        if (getIntent().getParcelableExtra(Utils.getIntentBattle()) != null) {
            infoBattle = getIntent().getParcelableExtra(Utils.getIntentBattle());
            if (infoBattle != null) {
                room = infoBattle.getRoom();
                infoUserBattle = infoBattle.getInfoUserBattle();
                checkComputer();
            }
        }
    }

    private void checkComputer() {
        if (infoUserBattle.get(home).getIdDevice().equals(Utils.getIdDevice(this))) {
            home = 0;
            guest = 1;
            if (infoUserBattle.get(guest).getIdDevice().substring(0, PresenterComputer.COMPUTER.length()).equals(PresenterComputer.COMPUTER)) {
                //Start Computer
                idComputer = infoUserBattle.get(guest).getIdDevice();
            }
        } else {
            home = 1;
            guest = 0;
            if (infoUserBattle.get(guest).getIdDevice().substring(0, PresenterComputer.COMPUTER.length()).equals(PresenterComputer.COMPUTER)) {
                //Start Computer
                idComputer = infoUserBattle.get(guest).getIdDevice();
            }
        }
    }

    private void getComputer() {
        new PresenterComputer(new ViewComputer() {
            @Override
            public void getComputer(int type, int value) {
                if (type == PresenterComputer.COMPUTER_TYPE_RPS) {
                    //EMIT RPS
                    EMIT_RPS(idComputer, room, value);
                } else {
                    //EMIT ICON
                    EMIT_ICON(idComputer, room, value);
                }
            }
        }).startComputer();
    }

    private void onSocket() {
        ((AppSocket) getApplication()).listener(new CallbackBattle() {
            @Override
            public void onBattleJoin(final Object object) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyLog.writeLog("ON_JOIN:-----/////----->" + object.toString());
                        Gson gson = new Gson();
                        RealModel join = gson.fromJson(object.toString(), RealModel.class);
                        if (join != null && join.getRoom().equals(room)) {
                            presenterAnimation.vs_out_Animation(lnVS);
                        }
                    }
                });
            }

            @Override
            public void onBattleRps(final Object object) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyLog.writeLog("ON_RPS:-----/////----->" + object.toString());
                        Gson gson = new Gson();
                        RealModel rps = gson.fromJson(object.toString(), RealModel.class);
                        //Check yourself
                        if (Utils.getIdDevice(Battle.this).equals(rps.getIdDevice())) {
                            moveUnknownHomeRps(rps.getValue());
                        } else {
                            moveUnknownGuestRps(rps.getValue());
                        }
                    }
                });
            }

            @Override
            public void onBattleIcon(final Object object) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyLog.writeLog("ON_ICON:-----/////-----> " + object.toString());
                        Gson gson = new Gson();
                        RealModel icon = gson.fromJson(object.toString(), RealModel.class);
                        //Check yourself
                        if (Utils.getIdDevice(Battle.this).equals(icon.getIdDevice())) {
                            moveHomeIcon(icon.getValue());
                        } else {
                            moveGuestIcon(icon.getValue());
                        }
                    }
                });
            }

            @Override
            public void onBattleFlag(final Object object) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyLog.writeLog("ON_FLAG:-----/////----->" + object.toString());
                        Gson gson = new Gson();
                        final RealModel flagWin = gson.fromJson(object.toString(), RealModel.class);
                        switch (flagWin.getStatus()) {
                            case Params.STATUS_DRAW:
                                show_Result_Set("Draw");
                                break;
                            case Params.STATUS_LOST:
                                if (Utils.getIdDevice(Battle.this).equals(flagWin.getIdDevice())) {
                                    show_Result_Set("You Lost");
                                    setFlagLost(flagWin.getPosition());
                                } else {
                                    show_Result_Set("You Win");
                                    setFlagWin(flagWin.getPosition());
                                }
                                break;
                            case Params.STATUS_WIN:
                                if (Utils.getIdDevice(Battle.this).equals(flagWin.getIdDevice())) {
                                    show_Result_Set("You Win");
                                    setFlagWin(flagWin.getPosition());
                                } else {
                                    show_Result_Set("You Lost");
                                    setFlagLost(flagWin.getPosition());
                                }
                                break;
                            case Params.STATUS_WINNER:
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (Utils.getIdDevice(Battle.this).equals(flagWin.getIdDevice())) {
                                            renewMatch();
                                            show_Result_Match("WINNER");
                                        } else {
                                            renewMatch();
                                            show_Result_Match("LOSER");
                                        }
                                    }
                                }, 800);

                                break;
                            case Params.STATUS_LOSER:
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (Utils.getIdDevice(Battle.this).equals(flagWin.getIdDevice())) {
                                            renewMatch();
                                            show_Result_Match("LOSER");
                                        } else {
                                            renewMatch();
                                            show_Result_Match("WINNER");
                                        }
                                    }
                                }, 800);

                                break;
                        }
                    }
                });
            }

            @Override
            public void onBattleAgain(final Object object) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyLog.writeLog("ON_AGAIN:-----/////----->" + object.toString());
                        Gson gson = new Gson();
                        RealModel again = gson.fromJson(object.toString(), RealModel.class);
                        if (!Utils.getIdDevice(Battle.this).equals(again.getIdDevice())) {
                            show_Message("Ban co muon choi lai khong");
                            confirm_Again = true;
                        }
                    }
                });
            }

            @Override
            public void onBattleConfirm(final Object object) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyLog.writeLog("ON_AGAIN:-----/////----->" + object.toString());

                        Intent i = new Intent(Battle.this, Again.class);
                        i.putExtra(Utils.getIntentPlayAgain(), room);
                        startActivity(i);
                        finish();

                    }
                });
            }

            @Override
            public void onBattleLeave(final Object object) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        MyLog.writeLog("ON_LEAVE:-----/////----->" + object.toString());
                        Gson gson = new Gson();
                        RealModel leave = gson.fromJson(object.toString(), RealModel.class);
                        if (!Utils.getIdDevice(Battle.this).equals(leave.getIdDevice())) {
                            //Disable Again
                            enable_Again = false;
                            show_Message("Doi thu roi khoi phong");
                        } else {

                            finish();

                        }
                    }
                });
            }

            @Override
            public void onBattleDisconnect(final Object object) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyLog.writeLog("ON_DISCONNECT:-----/////----->" + object.toString());
                        Gson gson = new Gson();
                        RealModel disconnect = gson.fromJson(object.toString(), RealModel.class);
                        if (Utils.getIdDevice(Battle.this).equals(disconnect.getIdDevice())) {

                            renewMatch();
                            show_Result_Match("WINNER");
                            enable_Again = false;

                        }
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Show Dialog Exit App

    }
}
