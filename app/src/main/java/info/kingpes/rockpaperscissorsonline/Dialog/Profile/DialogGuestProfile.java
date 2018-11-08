package info.kingpes.rockpaperscissorsonline.Dialog.Profile;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import info.kingpes.rockpaperscissorsonline.Font.Font;
import info.kingpes.rockpaperscissorsonline.Model.userObject;
import info.kingpes.rockpaperscissorsonline.R;
import info.kingpes.rockpaperscissorsonline.Util.Area;
import info.kingpes.rockpaperscissorsonline.Util.Helper;

/**
 * Created by Chau Huynh on 06/02/02017.
 */

public class DialogGuestProfile extends Dialog implements ViewDialogGuestProfile {
    private String idGuest;
    private TextView txtName, txtLocation, txtStar, txtStar1, txtWin, txtLost, txtLife, txtWheel, txtPosition, txtRobbinProfile;
    private PresenterDialogGuestProfile presenterDialogGuestProfile;
    private Font font;
    private Helper helper;
    private RelativeLayout relativeLayout;

    public DialogGuestProfile(Context context, String idGuest) {
        super(context);
        this.idGuest = idGuest;
        presenterDialogGuestProfile = new PresenterDialogGuestProfile(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_guest_profile);
        Init();
        presenterDialogGuestProfile.getProfileGuest(idGuest);

        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                dismiss();
                return false;
            }
        });


    }

    private void Init() {
        font = new Font(this.getContext());
        helper = new Helper();
        relativeLayout = (RelativeLayout) findViewById(R.id.view_dialog_profile);
        txtName = (TextView) findViewById(R.id.textView_name_visitor);
        txtName.setTypeface(font.getFontDimbo());
        txtLocation = (TextView) findViewById(R.id.textView_location_visitor);
        txtLocation.setTypeface(font.getFontDimbo());
        txtStar = (TextView) findViewById(R.id.textView_star_visitor);
        txtStar.setTypeface(font.getFontDimbo());
        txtStar1 = (TextView) findViewById(R.id.textView_star1_visitor);
        txtStar1.setTypeface(font.getFontDimbo());
        txtWin = (TextView) findViewById(R.id.textView_win_visitor);
        txtWin.setTypeface(font.getFontDimbo());
        txtLost = (TextView) findViewById(R.id.textView_lost_visitor);
        txtLost.setTypeface(font.getFontDimbo());
        txtLife = (TextView) findViewById(R.id.textView_life_visitor);
        txtLife.setTypeface(font.getFontDimbo());
        txtWheel = (TextView) findViewById(R.id.textView_wheel_visitor);
        txtWheel.setTypeface(font.getFontDimbo());
        txtPosition = (TextView) findViewById(R.id.textView_position_visitor);
        txtPosition.setTypeface(font.getFontDimbo());
        txtRobbinProfile = (TextView) findViewById(R.id.textView_robbin_profile_guest);
        txtRobbinProfile.setTypeface(font.getFontAtarian());
        txtRobbinProfile.setText(getContext().getString(R.string.visitor));
    }

    @Override
    public void profileGuest(userObject userGuest) {
        if (userGuest != null) {
            txtName.setText(userGuest.getName());
            txtLocation.setText(userGuest.getLocation());
            txtStar.setText(userGuest.getStar() + "");
            txtWin.setText(getContext().getString(R.string.win) + "  " + userGuest.getWin() + "");
            txtLost.setText(getContext().getString(R.string.lost) + "  " + userGuest.getLost() + "");
            txtStar1.setText(getContext().getString(R.string.star) + "  " + userGuest.getStar() + "");
            txtLife.setText(getContext().getString(R.string.life) + "  " + userGuest.getLife() + "");
            txtWheel.setText(getContext().getString(R.string.wheel) + "  " + userGuest.getWheel() + "");
            txtPosition.setText(Area.getArea(userGuest.getStar()) + "");

        }
    }
}
