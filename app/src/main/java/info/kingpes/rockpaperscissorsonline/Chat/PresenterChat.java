package info.kingpes.rockpaperscissorsonline.Chat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.kingpes.rockpaperscissorsonline.Model.lastObject;
import info.kingpes.rockpaperscissorsonline.Model.messageObject;
import info.kingpes.rockpaperscissorsonline.Util.Helper;

/**
 * Created by Chau Huynh on 06/02/02017.
 */

public class PresenterChat implements InterfaceChat {
    private ViewChat viewChat;
    private Helper helper;

    public PresenterChat(ViewChat viewChat) {
        this.viewChat = viewChat;
        helper = new Helper();
    }

    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    @Override
    public void sendMessage(String id, String name, String location, String msg, boolean global) {
        if (location != null) {
            String key = mData.child(helper.getRoot()).child(helper.getChat()).push().getKey();
            Map<String, Object> message = new HashMap<>();
            message.put("id", id);
            message.put("key", key);
            message.put("name", name);
            message.put("location", location);
            message.put("message", msg);
            message.put("times", ServerValue.TIMESTAMP);
            if (global) {
                mData.child(helper.getRoot()).child(helper.getChat()).child(helper.getLocal()).child(location).child(key).setValue(message);
            } else {
                mData.child(helper.getRoot()).child(helper.getChat()).child(helper.getGlobal()).child(key).setValue(message);
            }
            lastObject last = new lastObject();
            last.setLast(msg);
            if (global) {
                mData.child(helper.getRoot()).child(helper.getChat()).child(helper.getLast()).child(helper.getLocal()).child(location).setValue(last);
            } else {
                mData.child(helper.getRoot()).child(helper.getChat()).child(helper.getLast()).child(helper.getGlobal()).setValue(last);
            }
        }
    }

    @Override
    public void getMessageLocal(String location) {
        if (location != null) {
            final List<messageObject> msg = new ArrayList<>();
            mData.child(helper.getRoot()).child(helper.getChat()).child(helper.getLocal()).child(location).limitToLast(200)
                    .addChildEventListener(new ChildEventListener() {
                                               @Override
                                               public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                                   if (dataSnapshot != null) {
                                                       messageObject messageObject = dataSnapshot.getValue(messageObject.class);
                                                       msg.add(new messageObject(
                                                               messageObject.getId(),
                                                               messageObject.getKey(),
                                                               messageObject.getName(),
                                                               messageObject.getLocation(),
                                                               messageObject.getMessage(),
                                                               messageObject.getTimes()
                                                       ));
                                                   }
                                                   viewChat.getMessage(msg, true);
                                               }

                                               @Override
                                               public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                                               }

                                               @Override
                                               public void onChildRemoved(DataSnapshot dataSnapshot) {

                                               }

                                               @Override
                                               public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                               }

                                               @Override
                                               public void onCancelled(DatabaseError databaseError) {

                                               }
                                           }

                    );
        }

    }

    @Override
    public void getMessageGlobal() {
        final List<messageObject> msg = new ArrayList<>();
        mData.child(helper.getRoot()).child(helper.getChat()).child(helper.getGlobal()).limitToLast(200)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        if (dataSnapshot != null) {
                            messageObject messageObject = dataSnapshot.getValue(messageObject.class);
                            msg.add(new messageObject(
                                    messageObject.getId(),
                                    messageObject.getKey(),
                                    messageObject.getName(),
                                    messageObject.getLocation(),
                                    messageObject.getMessage(),
                                    messageObject.getTimes()
                            ));
                        }
                        viewChat.getMessage(msg, false);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public void getUpdateLocal(String location) {
        if (location != null) {
            mData.child(helper.getRoot()).child(helper.getChat()).child(helper.getLast()).child(helper.getLocal()).child(location).child(helper.getlast())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() != null) {
                                String msg = dataSnapshot.getValue().toString();
                                viewChat.updateNew(msg, true);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
        }
    }

    @Override
    public void getUpdateGlobal() {
        mData.child(helper.getRoot()).child(helper.getChat()).child(helper.getLast()).child(helper.getGlobal()).child(helper.getlast())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {
                            String msg = dataSnapshot.getValue().toString();
                            viewChat.updateNew(msg, false);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}
