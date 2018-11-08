package info.kingpes.rockpaperscissorsonline.socket;

/**
 * Created by Chau Huynh on 6/17/2017.
 */

public interface CallbackBattle {
    void onBattleJoin(Object object);
    void onBattleRps(Object object);
    void onBattleIcon(Object object);
    void onBattleFlag(Object object);
    void onBattleAgain(Object object);
    void onBattleConfirm(Object object);
    void onBattleLeave(Object object);
    void onBattleDisconnect(Object object);
}
