package info.kingpes.rockpaperscissorsonline.Presenter;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import info.kingpes.rockpaperscissorsonline.Interface.Interface.InterfaceGetFirebase;
import info.kingpes.rockpaperscissorsonline.Interface.View.ViewGetFirebase;
import info.kingpes.rockpaperscissorsonline.Model.roomObject;
import info.kingpes.rockpaperscissorsonline.Model.userObject;
import info.kingpes.rockpaperscissorsonline.Util.Helper;

/**
 * Created by Chau Huynh on 23/01/02017.
 */

public class PresenterGetFirebase implements InterfaceGetFirebase {
    private ViewGetFirebase viewGetFirebase;
    private Helper helper;

    public PresenterGetFirebase(ViewGetFirebase viewGetFirebase) {
        this.viewGetFirebase = viewGetFirebase;
    }

    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();


    @Override
    public void getIdGuest() {
        helper = new Helper((Context) viewGetFirebase);
        final List<roomObject> idList = new ArrayList<>();
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getServer())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            roomObject id = child.getValue(roomObject.class);
                            idList.add(id);
                        }
                        if (idList.size() != 1) {
                            if (dataSnapshot.getValue() != null) {
                                for (int i = 0; i < idList.size(); i++) {
                                    if (!idList.get(i).getId().equals(helper.getId())) {
                                        String idGuest = idList.get(i).getId();
                                        viewGetFirebase.getIdGuest(idGuest);
                                        i = idList.size();
                                    }
                                }

                            }
                        } else {
                            viewGetFirebase.getIdGuest(helper.getId());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    @Override
    public void getProfile(final String id) {
        helper = new Helper((Context) viewGetFirebase);
        mData.child(helper.getRoot()).child(helper.getUser()).child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {
                            userObject user = dataSnapshot.getValue(userObject.class);
                            viewGetFirebase.getProfile(id, user);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public void listenerConnect() {
        helper = new Helper((Context) viewGetFirebase);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getConnect()).child(helper.getId()).child(helper.getIdGuest())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {
                            String idGuest = dataSnapshot.getValue().toString();
                            viewGetFirebase.listenerConnecting(idGuest);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public void listenerRPS() {
        helper = new Helper((Context) viewGetFirebase);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getRPS()).child(helper.getId()).child(helper.getrps())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {
                            String rps = dataSnapshot.getValue().toString();
                            viewGetFirebase.listenerRPS(rps);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public void listenerIcon() {
        helper = new Helper((Context) viewGetFirebase);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getIcon()).child(helper.getId()).child(helper.geticon())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {
                            String icon = dataSnapshot.getValue().toString();
                            viewGetFirebase.listenerIcon(icon);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public void listenerFlag() {
        helper = new Helper((Context) viewGetFirebase);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getFlag()).child(helper.getId()).child(helper.getflag())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null){
                            String flag = dataSnapshot.getValue().toString();
                            viewGetFirebase.listenerFlag(flag);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public void listenerAgain() {
        helper = new Helper((Context) viewGetFirebase);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getAgain()).child(helper.getId()).child(helper.getagain())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {
                            String again = dataSnapshot.getValue().toString();
                            viewGetFirebase.listenerAgain(again);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public void listenerOffline() {
        helper = new Helper((Context) viewGetFirebase);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getOffline()).child(helper.getId()).child(helper.getoffline())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {
                            String off = dataSnapshot.getValue().toString();
                            viewGetFirebase.listenerVisitorOffline(off);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public void listenerSync() {
        helper = new Helper((Context) viewGetFirebase);
        mData.child(helper.getRoot()).child(helper.getBattle()).child(helper.getClient()).child(helper.getSync()).child(helper.getId()).child(helper.getsync())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {
                            String sync = dataSnapshot.getValue().toString();
                            viewGetFirebase.listenerSync(sync);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

}
