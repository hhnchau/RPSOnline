package info.kingpes.rockpaperscissorsonline.Presenter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * Created by Chau Huynh on 05/01/02017.
 */

public class PresenterAuthUser {
    private String result = null;
    //Create User
    public String createUser(final String mail, final String pass) {

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    //Log.d("Create User Firebase ", "Exist");
                    mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                //Log.d("Login Firebase ", "Fail");
                            } else {
                                //Log.d("Login Firebase ", "Successful");
                                result = "Login";
                            }
                        }
                    });
                } else {
                    //Log.d("Create User Firebase ", "Sussessful");
                    result = "Create";
                }
            }

        });
        return result;
    }

    //Read User
    private void readUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            String uid = user.getUid();
            Log.d("UserID", uid);
        }
    }

    //Update User
    private void updateUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName("Chau")
                    .setPhotoUri(Uri.parse("https://example.com/jane-q-user/getProfile.jpg"))
                    .build();
            user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        //Log.d("Update User ", "Successful");
                    } else {
                        //Log.d("Update User ", "Fail");
                    }
                }
            });
        }
    }

    //Delete User
    private void deleteUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        //Log.d("Delete User ", "Successful");
                    } else {
                        //Log.d("Delete User ", "Fail");
                    }
                }
            });
        }
    }
}
