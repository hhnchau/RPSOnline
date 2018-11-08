package info.kingpes.rockpaperscissorsonline;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import info.kingpes.rockpaperscissorsonline.Dialog.Menu.DialogMenu;
import info.kingpes.rockpaperscissorsonline.Dialog.Menu.ViewMenu;
import info.kingpes.rockpaperscissorsonline.Sound.Sound;
import info.kingpes.rockpaperscissorsonline.Storage.Storage;
import info.kingpes.rockpaperscissorsonline.log.MyLog;
import info.kingpes.rockpaperscissorsonline.models.InfoGame;
import info.kingpes.rockpaperscissorsonline.models.InfoMarket;
import info.kingpes.rockpaperscissorsonline.models.InfoSetting;
import info.kingpes.rockpaperscissorsonline.models.InfoUser;
import info.kingpes.rockpaperscissorsonline.models.MessageChat;
import info.kingpes.rockpaperscissorsonline.models.RankUser;
import info.kingpes.rockpaperscissorsonline.models.RealModel;
import info.kingpes.rockpaperscissorsonline.socket.AppSocket;
import info.kingpes.rockpaperscissorsonline.socket.CallbackSettings;
import info.kingpes.rockpaperscissorsonline.utils.Key;
import info.kingpes.rockpaperscissorsonline.utils.Utils;

public class MainGame extends FragmentActivity implements View.OnClickListener, ViewMenu {
    private static TextView txtStar, txtLife;
    private ImageView imgAddLife, imgSetting;
    public static LinearLayout lnLife;
    public static PagerSlidingTabStrip tabs;
    private ViewPager pager;
    FragmentPagerAdapter adapter;

    private Storage storage;

    private InfoSetting infoSetting;
    public static List<MessageChat> list_local;
    public static List<MessageChat> list_global;
    public static int bonus = 0;
    public static List<InfoMarket> list_market;
    //private static InfoUser infoUser;
    //private static List<RankUser> list_rankUser;
    public static long currentTime = 0;


    private DataChange dataChange;

    public interface DataChange {
        void updateRanking(List<RankUser> list_rankUser);
        void updateInfoUser(InfoUser infoUser);
    }

    public void onDataChange(DataChange dataChange) {
        this.dataChange = dataChange;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_game);
        Init();
        //on_InfoGame();
        SetupTabLayout();
        //Set ViewPage
        //ViewPageChange viewPageChange = new ViewPageChange((LinearLayout) tabs.getChildAt(0));
        //viewPageChange.notifyTabStripChanged(3, "New");

        //Start Sound
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //start sound
                Sound.getInstance().playBgMain(MainGame.this);
            }
        }, 300);
    }

    //Setup Tablayout
    private void SetupTabLayout() {
        ArrayList<ViewPagerObject> tabsList = new ArrayList<>();
        tabsList.add(new ViewPagerObject("", R.drawable.tab0, 0));
        tabsList.add(new ViewPagerObject("", R.drawable.tab1, 0));
        tabsList.add(new ViewPagerObject("", R.drawable.tab2_normal, 0));
        tabsList.add(new ViewPagerObject("", R.drawable.tab3, 0));
        tabsList.add(new ViewPagerObject("", R.drawable.tab4, 0));

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), this, tabsList);
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
        pager.setOffscreenPageLimit(5);
        pager.setCurrentItem(2);
    }

    //Init
    private void Init() {
        storage = new Storage();
        lnLife = (LinearLayout) findViewById(R.id.linnear_life);
        txtStar = (TextView) findViewById(R.id.textView_star);
        txtLife = (TextView) findViewById(R.id.textView_life);
        imgAddLife = (ImageView) findViewById(R.id.button_add_life);
        imgAddLife.setOnClickListener(this);
        imgSetting = (ImageView) findViewById(R.id.button_setting);
        imgSetting.setOnClickListener(this);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.my_tabStrip);
        pager = (ViewPager) findViewById(R.id.my_viewPager);

        list_local = new ArrayList<MessageChat>();
        list_global = new ArrayList<MessageChat>();
        list_market = new ArrayList<InfoMarket>();
        //list_rankUser = new ArrayList<RankUser>();
        //Get Intent
        Intent intent = getIntent();
        infoSetting = intent.getParcelableExtra(Utils.getIntentMainGame());
        list_market.addAll(infoSetting.getInfoMarket());
        currentTime = infoSetting.getCurrentTime();
        bonus = infoSetting.getBonus();
        List<MessageChat> listMessageChat = new ArrayList<MessageChat>();
        listMessageChat.addAll(infoSetting.getMessageChat());
        for (int i = 0; i < listMessageChat.size(); i++) {
            if (listMessageChat.get(i).getRoom().equals(getString(R.string.global))) {
                list_global.add(listMessageChat.get(i));
            } else {
                list_local.add(listMessageChat.get(i));
            }
        }
    }

    public static void setHeader(int maxChampion, int champion, int life) {
        if (maxChampion != -1 && champion != -1) {
            txtStar.setText(maxChampion + "/" + champion);
        }
        txtLife.setText(life + "");
    }

    public static void setViewPage(int p, String s) {
        //Set ViewPage
        ViewPageChange viewPageChange = new ViewPageChange((LinearLayout) tabs.getChildAt(0));
        viewPageChange.notifyTabStripChanged(p, s);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_add_life) {
            pager.setCurrentItem(4);
        } else {
            showMenu();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sound.getInstance().resume(this);

        //emit_InfoGame(Utils.getIdDevice(this));

    }

    @Override
    protected void onStop() {
        super.onStop();
        Sound.getInstance().pause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Sound.getInstance().clear(this);
    }

    private void showMenu() {
        DialogMenu dialogMenu = new DialogMenu(this, this);
        dialogMenu.show();
    }

    @Override
    public void facebookClick() {
        Utils.shareFacebook(this, "test");
    }

    @Override
    public void soundClick() {
        if (storage.getSoundEnable()) {
            //turn off sound
            Sound.getInstance().clear(this);
            storage.setSoundEnable(false);

        } else {
            storage.setSoundEnable(true);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Sound.getInstance().playBgMain(MainGame.this);
                }
            }, 1000);
        }
    }

    @Override
    public void helpClick() {

    }

    @Override
    public void infoClick() {

    }

    @Override
    public void closeClick() {

    }

    private void emit_InfoGame(String idDevice) {
        MyLog.writeLog("EMIT_INFO_GAME:-----/////----->: ");
        RealModel getInfoUser = new RealModel();
        getInfoUser.setIdDevice(idDevice);
        AppSocket.mSocket.emit(Key.INFO_GAME, getInfoUser.toJSON());
    }

    private void on_InfoGame() {
        ((AppSocket) getApplication()).listener(new CallbackSettings() {
            @Override
            public void onListener(final Object object) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyLog.writeLog("ON_INFO_GAME:-----/////----->" + object.toString());
                        Gson gson = new Gson();
                        InfoGame infoGame = gson.fromJson(object.toString(), InfoGame.class);

//                        infoUser = infoGame.getInfoUser();
//                        dataChange.updateInfoUser(infoUser);
//
//                        list_rankUser.addAll(infoGame.getRankUser());
//                        dataChange.updateRanking(list_rankUser);
                    }
                });
            }
        });
    }
}
