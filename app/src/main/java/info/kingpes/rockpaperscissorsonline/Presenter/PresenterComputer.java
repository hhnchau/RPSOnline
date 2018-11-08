package info.kingpes.rockpaperscissorsonline.Presenter;

import android.content.Context;
import android.os.CountDownTimer;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import info.kingpes.rockpaperscissorsonline.Interface.Interface.InterfaceComputer;
import info.kingpes.rockpaperscissorsonline.Interface.View.ViewComputer;
import info.kingpes.rockpaperscissorsonline.Interface.View.ViewGetFirebase;
import info.kingpes.rockpaperscissorsonline.Location.Country;
import info.kingpes.rockpaperscissorsonline.Model.userObject;
import info.kingpes.rockpaperscissorsonline.Util.Helper;

/**
 * Created by Chau Huynh on 27/01/02017.
 */

public class PresenterComputer implements InterfaceComputer {
    private ViewComputer viewComputer;

    public PresenterComputer(ViewComputer viewComputer) {
        this.viewComputer = viewComputer;
    }

    private CountDownTimer cTimer;

    //Params
    public static final int COMPUTER_TYPE_ICON = 2;
    public static final int COMPUTER_TYPE_RPS = 1;
    public static final String COMPUTER = "computer";

    @Override
    public void startComputer() {
        Random random = new Random();
        int r = random.nextInt(3) + 1;
        switch (r) {
            case 1:
                type1();
                break;
            case 2:
                type2();
                break;
            case 3:
                type3();
                break;
        }
    }

    private void type1() {
        cTimer = null;
        cTimer = new CountDownTimer(10 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                switch ((int) (millisUntilFinished / 1000)) {
                    case 9:
                        viewComputer.getComputer(COMPUTER_TYPE_ICON, getIcon());
                        break;
                    case 8:
                        break;
                    case 7:
                        viewComputer.getComputer(COMPUTER_TYPE_RPS, getRps());
                        break;
                    case 6:
                        break;
                    case 5:
                        viewComputer.getComputer(COMPUTER_TYPE_ICON, getIcon());
                        break;
                    case 4:
                        break;
                    case 3:
                        break;
                    case 2:
                        break;
                    case 1:
                        break;
                }
            }

            public void onFinish() {

            }
        };
        cTimer.start();
    }

    private void type2() {
        cTimer = null;
        cTimer = new CountDownTimer(10 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                switch ((int) (millisUntilFinished / 1000)) {
                    case 9:
                        viewComputer.getComputer(COMPUTER_TYPE_ICON, getIcon());
                        break;
                    case 8:
                        break;
                    case 7:
                        viewComputer.getComputer(COMPUTER_TYPE_ICON, getIcon());
                        break;
                    case 6:
                        break;
                    case 5:
                        viewComputer.getComputer(COMPUTER_TYPE_ICON, getIcon());
                        break;
                    case 4:
                        break;
                    case 3:
                        viewComputer.getComputer(COMPUTER_TYPE_RPS, getRps());
                        break;
                    case 2:
                        break;
                    case 1:
                        break;
                }
            }

            public void onFinish() {

            }
        };
        cTimer.start();
    }

    private void type3() {
        cTimer = null;
        cTimer = new CountDownTimer(10 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                switch ((int) (millisUntilFinished / 1000)) {
                    case 9:

                        break;
                    case 8:
                        break;
                    case 7:
                        viewComputer.getComputer(COMPUTER_TYPE_RPS, getRps());
                        break;
                    case 6:
                        break;
                    case 5:
                        viewComputer.getComputer(COMPUTER_TYPE_ICON, getIcon());
                        break;
                    case 4:
                        break;
                    case 3:

                        break;
                    case 2:
                        break;
                    case 1:
                        break;
                }
            }

            public void onFinish() {

            }
        };
        cTimer.start();
    }

    private int getRps() {
        Random random = new Random();
        int r = random.nextInt(3) + 1;
        return r;
    }

    private int getIcon() {
        Random random = new Random();
        int r = random.nextInt(8) + 1;
        return r;
    }
}
