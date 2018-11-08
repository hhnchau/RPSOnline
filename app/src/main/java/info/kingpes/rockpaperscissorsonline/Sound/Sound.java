package info.kingpes.rockpaperscissorsonline.Sound;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;

import info.kingpes.rockpaperscissorsonline.R;
import info.kingpes.rockpaperscissorsonline.Storage.Storage;
import info.kingpes.rockpaperscissorsonline.socket.AppSocket;

import static android.content.Context.AUDIO_SERVICE;

/**
 * Created by Chau Huynh on 15/02/02017.
 */

public class Sound extends AppSocket {
    private static SoundPool soundPool;
    public static final int MAX_STREAM = 20;
    private static int logo;
    private static int vs;
    private static int bg_battle;
    private static int bg_main;
    private static int set_win, set_draw, set_lost, match_win, match_lost;
    private static int rps_move, icon_move;
    private static int disable_button;
    private static int click_button;
    private static int battle;
    private static int life;
    private static int add_life;

    private static Sound instance;

    public static Sound getInstance() {
        if (instance == null) {
            instance = new Sound();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 21) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setAudioAttributes(audioAttributes).setMaxStreams(MAX_STREAM);
            soundPool = builder.build();
        } else {
            soundPool = new SoundPool(MAX_STREAM, AudioManager.STREAM_MUSIC, 0);
        }
        vs = soundPool.load(getApplicationContext(), R.raw.vs, 1);
        bg_battle = soundPool.load(getApplicationContext(), R.raw.bg_battle, 1);
        set_win = soundPool.load(getApplicationContext(), R.raw.set_win, 1);
        set_draw = soundPool.load(getApplicationContext(), R.raw.set_draw, 1);
        set_lost = soundPool.load(getApplicationContext(), R.raw.set_lost, 1);
        match_win = soundPool.load(getApplicationContext(), R.raw.match_win, 1);
        match_lost = soundPool.load(getApplicationContext(), R.raw.match_lost, 1);
        icon_move = soundPool.load(getApplicationContext(), R.raw.icon_move, 1);
        rps_move = soundPool.load(getApplicationContext(), R.raw.rps_move, 1);
        disable_button = soundPool.load(getApplicationContext(), R.raw.disable_button, 1);
        click_button = soundPool.load(getApplicationContext(), R.raw.click_button, 1);
        logo = soundPool.load(getApplicationContext(), R.raw.logo, 1);
        bg_main = soundPool.load(getApplicationContext(), R.raw.bg_main, 1);
        battle = soundPool.load(getApplicationContext(), R.raw.battle, 1);
        life = soundPool.load(getApplicationContext(), R.raw.life, 1);
        add_life = soundPool.load(getApplicationContext(), R.raw.add_life, 1);
    }


    public void playLogo(Context context) {
        if (Storage.getInstance(context).getSoundEnable()) {
            if (soundPool != null) {
                soundPool.play(logo, 1.0f, 1.0f, 1, 0, 1.0f);
            }
        }
    }

    public void playVs(Context context) {
        if (Storage.getInstance(context).getSoundEnable()) {
            if (soundPool != null) {
                soundPool.play(vs, 1.0f, 1.0f, 1, 0, 1.0f);
            }
        }
    }

    public void playBgMain(Context context) {
        if (Storage.getInstance(context).getSoundEnable()) {
            if (soundPool != null) {
                soundPool.play(bg_main, 1.0f, 1.0f, 1, -1, 1.0f);
            }
        }
    }

    public void playBgBattle(Context context) {
        if (Storage.getInstance(context).getSoundEnable()) {
            if (soundPool != null) {
                soundPool.play(bg_battle, 1.0f, 1.0f, 1, -1, 1.0f);
            }
        }
    }

    public void playSetWin(Context context) {
        if (Storage.getInstance(context).getSoundEnable()) {
            if (soundPool != null) {
                soundPool.play(set_win, 1.0f, 1.0f, 1, 0, 1.0f);
            }
        }
    }

    public void playSetDraw(Context context) {
        if (Storage.getInstance(context).getSoundEnable()) {
            if (soundPool != null) {
                soundPool.play(set_draw, 1.0f, 1.0f, 1, 0, 1.0f);
            }
        }
    }

    public void playSetLost(Context context) {
        if (Storage.getInstance(context).getSoundEnable()) {
            if (soundPool != null) {
                soundPool.play(set_lost, 1.0f, 1.0f, 1, 0, 1.0f);
            }
        }
    }

    public void playMatchWin(Context context) {
        if (Storage.getInstance(context).getSoundEnable()) {
            if (soundPool != null) {
                soundPool.play(match_win, 1.0f, 1.0f, 1, 0, 1.0f);
            }
        }
    }

    public void playMatchLost(Context context) {
        if (Storage.getInstance(context).getSoundEnable()) {
            if (soundPool != null) {
                soundPool.play(match_lost, 1.0f, 1.0f, 1, 0, 1.0f);
            }
        }
    }

    public void playRPSMove(Context context) {
        if (Storage.getInstance(context).getSoundEnable()) {
            if (soundPool != null) {
                soundPool.play(rps_move, 1.0f, 1.0f, 1, 0, 1.0f);
            }
        }
    }

    public void playIconMove(Context context) {
        if (Storage.getInstance(context).getSoundEnable()) {
            if (soundPool != null) {
                soundPool.play(icon_move, 1.0f, 1.0f, 1, 0, 1.0f);
            }
        }
    }

    public void playDisableButton(Context context) {
        if (Storage.getInstance(context).getSoundEnable()) {
            if (soundPool != null) {
                soundPool.play(disable_button, 1.0f, 1.0f, 1, 0, 1.0f);
            }
        }
    }

    public void playClickButton(Context context) {
        if (Storage.getInstance(context).getSoundEnable()) {
            if (soundPool != null) {
                soundPool.play(click_button, 1.0f, 1.0f, 1, 0, 1.0f);
            }
        }
    }

    public void playBattle(Context context) {
        if (Storage.getInstance(context).getSoundEnable()) {
            if (soundPool != null) {
                soundPool.play(battle, 1.0f, 1.0f, 1, 0, 1.0f);
            }
        }
    }

    public void playLife(Context context) {
        if (Storage.getInstance(context).getSoundEnable()) {
            if (soundPool != null) {
                soundPool.play(life, 1.0f, 1.0f, 1, 0, 1.0f);
            }
        }
    }

    public void playAddLife(Context context) {
        if (Storage.getInstance(context).getSoundEnable()) {
            if (soundPool != null) {
                soundPool.play(add_life, 1.0f, .0f, 1, 0, 1.0f);
            }
        }
    }

    public void pause(Context context) {
        if (Storage.getInstance(context).getSoundEnable()) {
            if (soundPool != null) {
                soundPool.autoPause();
            }
        }
    }

    public void resume(Context context) {
        if (Storage.getInstance(context).getSoundEnable()) {
            if (soundPool != null) {
                soundPool.autoResume();
            }
        }
    }

    public void clear(Context context) {
        if (Storage.getInstance(context).getSoundEnable()) {
            if (soundPool != null) {
                soundPool.release();
                soundPool = null;
            }
        }
    }
}
