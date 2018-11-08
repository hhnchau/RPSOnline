package info.kingpes.rockpaperscissorsonline.Time;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import info.kingpes.rockpaperscissorsonline.Model.timeObject;
import info.kingpes.rockpaperscissorsonline.Util.Helper;

/**
 * Created by Chau Huynh on 05/02/02017.
 */

public class PresenterTime implements InterfaceTime {
    private ViewTime viewTime;
    private Helper helper;

    public PresenterTime(ViewTime viewTime) {
        this.viewTime = viewTime;
        helper = new Helper();
    }

    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();


    @Override
    public void setJoinTime(String id) {
        timeObject join = new timeObject();
        join.setJoinTime(ServerValue.TIMESTAMP);
        mData.child(helper.getRoot()).child(helper.getTime()).child(helper.getJoin()).child(id).setValue(join);
    }

    @Override
    public void setBonusTime(String id) {
        timeObject bonus = new timeObject();
        bonus.setBonusTime(ServerValue.TIMESTAMP);
        mData.child(helper.getRoot()).child(helper.getTime()).child(helper.getBonus()).child(id).setValue(bonus);
    }

    @Override
    public void getBonusTime(String id) {
        mData.child(helper.getRoot()).child(helper.getTime()).child(helper.getBonus()).child(id).child(helper.getBonusTime())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {
                            Long times = (Long) dataSnapshot.getValue();
                            viewTime.bonusTime(times);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    @Override
    public void checkBonusTime(final String id, final long timeServer) {
        timeObject check = new timeObject();
        check.setCheckBonusTime(ServerValue.TIMESTAMP);
        mData.child(helper.getRoot()).child(helper.getTime()).child(helper.getCheck()).child(id).setValue(check)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isComplete()) {
                            mData.child(helper.getRoot()).child(helper.getTime()).child(helper.getCheck()).child(id).child(helper.getCheckBonusTime())
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.getValue() != null) {
                                                long times = (long) dataSnapshot.getValue();
                                                long bonus = timeServer + 86400000;
                                                if (times < bonus) {
                                                    viewTime.checkBonus(false);
                                                } else {
                                                    viewTime.checkBonus(true);
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                        }
                    }
                });

    }
}
