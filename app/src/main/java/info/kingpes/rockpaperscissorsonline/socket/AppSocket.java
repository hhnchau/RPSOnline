package info.kingpes.rockpaperscissorsonline.socket;

import android.app.Application;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

import info.kingpes.rockpaperscissorsonline.log.MyLog;
import info.kingpes.rockpaperscissorsonline.utils.Key;

/**
 * Created by Chau Huynh on 5/16/2017.
 */

public class AppSocket extends Application {
    public static Socket mSocket;

    {
        try {
            mSocket = IO.socket(Url.ADDRESS);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            MyLog.writeLog("AppSocket " + e);
        }
    }

    //Call Back
    private CallbackSettings callbackSettings;

    public void listener(CallbackSettings callbackSettings) {
        this.callbackSettings = callbackSettings;
    }

    //Call Back
    private CallbackFrg_One callbackFrg_one;

    public void listener(CallbackFrg_One callbackFrg_one) {
        this.callbackFrg_one = callbackFrg_one;
    }

    //Call Back
    private CallbackFrg_Two callbackFrg_two;

    public void listener(CallbackFrg_Two callbackFrg_two) {
        this.callbackFrg_two = callbackFrg_two;
    }

    //Call Back
    private CallbackFrg_Three callbackFrg_three;

    public void listener(CallbackFrg_Three callbackFrg_three) {
        this.callbackFrg_three = callbackFrg_three;
    }

    //Call Back
    private CallbackFrg_Four callbackFrg_four;

    public void listener(CallbackFrg_Four callbackFrg_four) {
        this.callbackFrg_four = callbackFrg_four;
    }

    //Call Back
    private CallbackFrg_Five callbackFrg_five;

    public void listener(CallbackFrg_Five callbackFrg_five) {
        this.callbackFrg_five = callbackFrg_five;
    }

    //Call Back
    private CallbackSearch callbackSearch;

    public void listener(CallbackSearch callbackSearch) {
        this.callbackSearch = callbackSearch;
    }

    //Call Back
    private CallbackAgain callbackAgain;

    public void listener(CallbackAgain callbackAgain) {
        this.callbackAgain = callbackAgain;
    }

    //Call Back
    private CallbackBattle callbackBattle;

    public void listener(CallbackBattle callbackBattle) {
        this.callbackBattle = callbackBattle;
    }

    //SplashScreen
    private Emitter.Listener onSettings;

    {
        onSettings = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                callbackSettings.onListener(args[0]);
            }
        };
    }

    //MainGame
    private Emitter.Listener onInfoGame;

    {
        onInfoGame = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                callbackSettings.onListener(args[0]);
            }
        };
    }


    //Frg_One
    private Emitter.Listener onMessageChat;

    {
        onMessageChat = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                callbackFrg_one.onListener(args[0]);
            }
        };
    }

    //Frg_Two
    private Emitter.Listener onGetRankUser;

    {
        onGetRankUser = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                callbackFrg_two.onListener(args[0]);
            }
        };
    }

    //Frg_Three
    private Emitter.Listener onGetInfoUser;

    {
        onGetInfoUser = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                callbackFrg_three.onListener(args[0]);
            }
        };
    }

    //Frg_Four
    private Emitter.Listener onLuckyLife;

    {
        onLuckyLife = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                callbackFrg_four.onListener(args[0]);
            }
        };
    }

    //Frg_Five
    private Emitter.Listener onBuyLucky;

    {
        onBuyLucky = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                callbackFrg_five.onBuyLucky(args[0]);
            }
        };
    }

    //Frg_Five
    private Emitter.Listener onBuyLife;

    {
        onBuyLife = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                callbackFrg_five.onBuyLive(args[0]);
            }
        };
    }

    //Search
    private Emitter.Listener onSearch;

    {
        onSearch = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                callbackSearch.onListener(args[0]);
            }
        };
    }

    //Again
    private Emitter.Listener onAgain;

    {
        onAgain = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                callbackAgain.onListener(args[0]);
            }
        };
    }

    //Battle Join
    private Emitter.Listener onJOIN;

    {
        onJOIN = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                callbackBattle.onBattleJoin(args[0]);
            }
        };
    }

    //Battle Rps
    private Emitter.Listener onRPS;

    {
        onRPS = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                callbackBattle.onBattleRps(args[0]);
            }
        };
    }

    //Battle Icon
    private Emitter.Listener onICON;

    {
        onICON = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                callbackBattle.onBattleIcon(args[0]);
            }
        };
    }

    //Battle Flag
    private Emitter.Listener onFLAG;

    {
        onFLAG = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                callbackBattle.onBattleFlag(args[0]);
            }
        };
    }

    //Battla Again
    private Emitter.Listener onAGAIN;

    {
        onAGAIN = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                callbackBattle.onBattleAgain(args[0]);
            }
        };
    }

    //Battle Confirm
    private Emitter.Listener onCONFIRMAGAIN;

    {
        onCONFIRMAGAIN = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                callbackBattle.onBattleConfirm(args[0]);
            }
        };
    }

    //Battle Leave
    private Emitter.Listener onLEAVE;

    {
        onLEAVE = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                callbackBattle.onBattleLeave(args[0]);
            }
        };
    }

    //Battle Disconnect
    private Emitter.Listener onDISCONNECT;

    {
        onDISCONNECT = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                callbackBattle.onBattleDisconnect(args[0]);
            }
        };
    }


    @Override
    public void onCreate() {
        super.onCreate();
        //Connect
        if (!mSocket.connected()) {
            mSocket.connect();
            MyLog.writeLog("Socket.connect");
        }

        AppSocket.mSocket.on(Key.SETTINGS, onSettings);

        //AppSocket.mSocket.on(Key.INFO_GAME, onInfoGame);

        AppSocket.mSocket.on(Key.MESSAGE_CHAT, onMessageChat);

        AppSocket.mSocket.on(Key.RANK_USER, onGetRankUser);

        AppSocket.mSocket.on(Key.INFO_USER, onGetInfoUser);

        AppSocket.mSocket.on(Key.INFO_USER, onLuckyLife);

        AppSocket.mSocket.on(Key.LUCKY, onBuyLucky);

        AppSocket.mSocket.on(Key.LIFE, onBuyLife);

        AppSocket.mSocket.on(Key.ENTER_WAITING_ROOM, onSearch);

        AppSocket.mSocket.on(Key.ENTER_AGAIN, onAgain);

        AppSocket.mSocket.on(Key.JOIN, onJOIN);

        AppSocket.mSocket.on(Key.RPS, onRPS);

        AppSocket.mSocket.on(Key.ICON, onICON);

        AppSocket.mSocket.on(Key.FLAG, onFLAG);

        AppSocket.mSocket.on(Key.AGAIN, onAGAIN);

        AppSocket.mSocket.on(Key.CONFIRM_AGAIN, onCONFIRMAGAIN);

        AppSocket.mSocket.on(Key.LEAVE, onLEAVE);

        AppSocket.mSocket.on(Key.DISCONNECT, onDISCONNECT);
    }
}
