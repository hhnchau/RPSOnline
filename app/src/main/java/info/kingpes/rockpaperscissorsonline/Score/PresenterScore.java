package info.kingpes.rockpaperscissorsonline.Score;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import info.kingpes.rockpaperscissorsonline.Util.Helper;

/**
 * Created by Chau Huynh on 08/02/02017.
 */

public class PresenterScore implements InterfaceScore {
    private ViewScore viewScore;
    private Helper helper;

    public PresenterScore(ViewScore viewScore) {
        this.viewScore = viewScore;
        helper = new Helper();
    }

    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    @Override
    public void setUpdateLife(String id, int lifes) {
        mData.child(helper.getRoot()).child(helper.getUser()).child(id).child(helper.getlife()).setValue(lifes);
    }

    @Override
    public void setUpdateWheel(String id, int wheel) {
        mData.child(helper.getRoot()).child(helper.getUser()).child(id).child(helper.getwheel()).setValue(wheel);
    }

    @Override
    public void setUpdateArea(String id, int area) {
        mData.child(helper.getRoot()).child(helper.getUser()).child(id).child(helper.getarea()).setValue(area);
    }

    @Override
    public void getUpdateLife(String id) {
        mData.child(helper.getRoot()).child(helper.getUser()).child(id).child(helper.getlife())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null){
                            String life = dataSnapshot.getValue().toString();
                            viewScore.updateLife(Integer.parseInt(life));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public void getUpdateWheel(String id) {
        mData.child(helper.getRoot()).child(helper.getUser()).child(id).child(helper.getwheel())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null){
                            String wheel = dataSnapshot.getValue().toString();
                            viewScore.updateWheel(Integer.parseInt(wheel));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    @Override
    public void getUpdateArea(String id) {
        mData.child(helper.getRoot()).child(helper.getUser()).child(id).child(helper.getarea())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null){
                            String area = dataSnapshot.getValue().toString();
                            viewScore.updateArea(Integer.parseInt(area));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}
