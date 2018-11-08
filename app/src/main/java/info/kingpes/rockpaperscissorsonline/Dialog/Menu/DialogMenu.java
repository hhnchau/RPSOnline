package info.kingpes.rockpaperscissorsonline.Dialog.Menu;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import info.kingpes.rockpaperscissorsonline.Font.Font;
import info.kingpes.rockpaperscissorsonline.R;
import info.kingpes.rockpaperscissorsonline.Storage.Storage;

/**
 * Created by Chau Huynh on 17/02/02017.
 */

public class DialogMenu extends Dialog {
    private Button btnFacebook, btnSound, btnHelp, btnInfo, btnClose;
    private TextView txtMsg;
    private Font font;
    private ViewMenu viewMenu;
    private Storage storage;

    public DialogMenu(Context context, ViewMenu viewMenu) {
        super(context);
        this.viewMenu = viewMenu;
        storage = new Storage();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog_menu);
        Init();
        onClick();
    }

    private void Init() {
        font = new Font(getContext());
        btnFacebook = (Button) findViewById(R.id.button_menu_facebook);
        btnFacebook.setTypeface(font.getFontDimbo());
        btnFacebook.setText(getContext().getString(R.string.facebook));
        btnSound = (Button) findViewById(R.id.button_menu_sound);
        btnSound.setTypeface(font.getFontDimbo());
        if (storage.getSoundEnable()) {
            btnSound.setText(getContext().getString(R.string.sound_on));
        } else {
            btnSound.setText(getContext().getString(R.string.sound_off));
        }
        btnHelp = (Button) findViewById(R.id.button_menu_help);
        btnHelp.setTypeface(font.getFontDimbo());
        btnHelp.setText(getContext().getString(R.string.help));
        btnInfo = (Button) findViewById(R.id.button_menu_info);
        btnInfo.setTypeface(font.getFontDimbo());
        btnInfo.setText(getContext().getString(R.string.info));
        btnClose = (Button) findViewById(R.id.button_menu_close);
        btnClose.setTypeface(font.getFontDimbo());
        btnClose.setText(getContext().getString(R.string.close));
        txtMsg = (TextView) findViewById(R.id.textView_menu_msg);
        txtMsg.setTypeface(font.getFontDimbo());
    }

    private void onClick() {
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewMenu.facebookClick();
            }
        });
        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (storage.getSoundEnable()) {
                    btnSound.setText(getContext().getString(R.string.sound_on));
                } else {
                    btnSound.setText(getContext().getString(R.string.sound_off));
                }
                viewMenu.soundClick();
            }
        });
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewMenu.helpClick();
            }
        });
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewMenu.infoClick();
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewMenu.closeClick();
                dismiss();
            }
        });
    }
}
