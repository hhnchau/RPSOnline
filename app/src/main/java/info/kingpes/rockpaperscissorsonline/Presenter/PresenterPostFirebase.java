package info.kingpes.rockpaperscissorsonline.Presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import info.kingpes.rockpaperscissorsonline.Interface.Interface.InterfacePostFirebase;
import info.kingpes.rockpaperscissorsonline.Interface.View.ViewPostFirebase;
import info.kingpes.rockpaperscissorsonline.Model.userObject;
import info.kingpes.rockpaperscissorsonline.Util.Helper;

/**
 * Created by Chau Huynh on 23/01/02017.
 */

public class PresenterPostFirebase implements InterfacePostFirebase {
    private ViewPostFirebase viewPostFirebase;
    private Helper helper;

    public PresenterPostFirebase(ViewPostFirebase viewPostFirebase) {
        this.viewPostFirebase = viewPostFirebase;
    }

    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    @Override
    public void createUser(String name, String location) {
        if (location != null) {
            helper = new Helper((Context) viewPostFirebase);
            userObject user = new userObject();
            user.setLocation(location);
            user.setAvatar("img");
            user.setName(name);
            user.setLife(10);
            user.setWheel(3);
            mData.child(helper.getRoot()).child(helper.getUser()).child(helper.getId()).setValue(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            viewPostFirebase.postOK();
                        }
                    });
        }
    }

    @Override
    public void deleteUser(String id) {
        helper = new Helper((Context) viewPostFirebase);
        mData.child(helper.getRoot()).child(helper.getUser()).child(id).removeValue();
    }

    @Override
    public void registerRoomServer() {
        helper = new Helper((Context) viewPostFirebase);
        Map<String, Object> room = new HashMap<>();
        room.put("id", helper.getId());
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getServer()).child(helper.getId()).setValue(room);
    }

    @Override
    public void removeIdRoomServer(String id) {
        helper = new Helper((Context) viewPostFirebase);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getServer()).child(id).removeValue();
    }

    @Override
    public void connectToGuest(String idGuest) {
        helper = new Helper((Context) viewPostFirebase);
        Map<String, Object> connect = new HashMap<>();
        connect.put("id", helper.getId());
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getConnect()).child(idGuest).setValue(connect);
    }

    @Override
    public void deleteConnect(String id) {
        helper = new Helper((Context) viewPostFirebase);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getConnect()).child(id).removeValue();
    }

    @Override
    public void setRPS(String idGuset, int rps) {
        helper = new Helper((Context) viewPostFirebase);
        Map<String, Object> r = new HashMap<>();
        r.put("rps", rps);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getRPS()).child(idGuset).setValue(r);
    }

    @Override
    public void deleteRPS(String id) {
        helper = new Helper((Context) viewPostFirebase);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getRPS()).child(id).removeValue();
    }

    @Override
    public void setIcon(String idGuset, int icon) {
        helper = new Helper((Context) viewPostFirebase);
        Map<String, Object> i = new HashMap<>();
        i.put("icon", icon);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getIcon()).child(idGuset).setValue(i);
    }

    @Override
    public void deleteIcon(String id) {
        helper = new Helper((Context) viewPostFirebase);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getIcon()).child(id).removeValue();
    }

    @Override
    public void setFlag(String idGuset, int flag) {
        helper = new Helper((Context) viewPostFirebase);
        Map<String, Object> f = new HashMap<>();
        f.put("flag", flag);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getFlag()).child(idGuset).setValue(f);
    }

    @Override
    public void deletaFlag(String id) {
        helper = new Helper((Context) viewPostFirebase);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getFlag()).child(id).removeValue();

    }

    @Override
    public void setAgain(String idGuest, int again) {
        helper = new Helper((Context) viewPostFirebase);
        Map<String, Object> a = new HashMap<>();
        a.put("again", again);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getAgain()).child(idGuest).setValue(a);
    }

    @Override
    public void deleteAgain(String id) {
        helper = new Helper((Context) viewPostFirebase);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getAgain()).child(id).removeValue();

    }

    @Override
    public void setSync(String idGuest, int sync) {
        helper = new Helper((Context) viewPostFirebase);
        Map<String, Object> s = new HashMap<>();
        s.put("sync", sync);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getSync()).child(idGuest).setValue(s);
    }

    @Override
    public void deleteSync(String id) {
        helper = new Helper((Context) viewPostFirebase);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getSync()).child(id).removeValue();
    }

    @Override
    public void setOffline(String idGuest, int off) {
        helper = new Helper((Context) viewPostFirebase);
        Map<String, Object> o = new HashMap<>();
        o.put("off", off);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getOffline()).child(idGuest).setValue(o);

    }

    @Override
    public void deleteOffline(String id) {
        helper = new Helper((Context) viewPostFirebase);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getOffline()).child(id).removeValue();
    }

    @Override
    public void addStart(String id, int star_old, int star_add) {
        helper = new Helper((Context) viewPostFirebase);
        star_old = star_old + star_add;
        Map<String, Object> ad = new HashMap<>();
        ad.put("star", star_old);
        mData.child(helper.getRoot()).child(helper.getUser()).child(id).updateChildren(ad);
    }

    @Override
    public void subStart(String id, int star_old, int star_sub) {
        helper = new Helper((Context) viewPostFirebase);
        star_old = star_old - star_sub;
        if (star_old < 0) {
            star_old = 0;
        }
        Map<String, Object> su = new HashMap<>();
        su.put("star", star_old);
        mData.child(helper.getRoot()).child(helper.getUser()).child(id).updateChildren(su);
    }

    @Override
    public void addLife(String id, int life_old, int life_add) {
        helper = new Helper((Context) viewPostFirebase);
        life_old = life_old + life_add;
        Map<String, Object> ad_li = new HashMap<>();
        ad_li.put("life", life_old);
        mData.child(helper.getRoot()).child(helper.getUser()).child(id).updateChildren(ad_li);
    }

    @Override
    public void subLife(String id, int life_old, int life_sub) {
        helper = new Helper((Context) viewPostFirebase);
        life_old = life_old - life_sub;
        if (life_old < 0) {
            life_old = 0;
        }
        Map<String, Object> su_li = new HashMap<>();
        su_li.put("life", life_old);
        mData.child(helper.getRoot()).child(helper.getUser()).child(id).updateChildren(su_li);
    }

    @Override
    public void addWin(String id, int win_old, int win_add) {
        helper = new Helper((Context) viewPostFirebase);
        win_old = win_old + win_add;
        Map<String, Object> ad_w = new HashMap<>();
        ad_w.put("win", win_old);
        mData.child(helper.getRoot()).child(helper.getUser()).child(id).updateChildren(ad_w);
    }

    @Override
    public void subWin(String id, int win_old, int win_sub) {
        helper = new Helper((Context) viewPostFirebase);
        win_old = win_old - win_sub;
        if (win_old < 0) {
            win_old = 0;
        }
        Map<String, Object> su_w = new HashMap<>();
        su_w.put("win", win_old);
        mData.child(helper.getRoot()).child(helper.getUser()).child(id).updateChildren(su_w);
    }

    @Override
    public void addLost(String id, int lost_old, int lost_add) {
        helper = new Helper((Context) viewPostFirebase);
        lost_old = lost_old + lost_add;
        Map<String, Object> ad_lost = new HashMap<>();
        ad_lost.put("lost", lost_old);
        mData.child(helper.getRoot()).child(helper.getUser()).child(id).updateChildren(ad_lost);
    }

    @Override
    public void subLost(String id, int lost_old, int lost_sub) {
        helper = new Helper((Context) viewPostFirebase);
        lost_old = lost_old - lost_sub;
        if (lost_old < 0) {
            lost_old = 0;
        }
        Map<String, Object> su_lost = new HashMap<>();
        su_lost.put("lost", lost_old);
        mData.child(helper.getRoot()).child(helper.getUser()).child(id).updateChildren(su_lost);
    }

}
