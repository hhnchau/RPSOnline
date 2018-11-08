package info.kingpes.rockpaperscissorsonline.Dialog.Profile;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import info.kingpes.rockpaperscissorsonline.Model.userObject;
import info.kingpes.rockpaperscissorsonline.Util.Helper;

/**
 * Created by Chau Huynh on 08/02/02017.
 */

public class PresenterDialogGuestProfile implements InterfaceDialogGuestProfile {
    private ViewDialogGuestProfile viewDialogGuestProfile;

    public PresenterDialogGuestProfile(ViewDialogGuestProfile viewDialogGuestProfile) {
        this.viewDialogGuestProfile = viewDialogGuestProfile;
    }

    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    @Override
    public void getProfileGuest(String idGuest) {
        Helper helper = new Helper();
        mData.child(helper.getRoot()).child(helper.getUser()).child(idGuest)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {
                            userObject user = dataSnapshot.getValue(userObject.class);
                            viewDialogGuestProfile.profileGuest(user);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}
