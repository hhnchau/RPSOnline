package info.kingpes.rockpaperscissorsonline.Dialog.Notify;

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

/**
 * Created by Chau Huynh on 17/02/02017.
 */

public class DialogNotify extends Dialog {
    private TextView txt;
    private Button btnCancel, btnOk;
    private Font font;
    private String input;
    private boolean exit;
    private ViewNotify viewNotify;

    public DialogNotify(Context context, ViewNotify viewNotify, String input, boolean exit) {
        super(context);
        this.input = input;
        this.viewNotify = viewNotify;
        this.exit = exit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog_notify);
        Init();
        setText(input);
        onClick();
    }

    private void setText(String msg) {
        txt.setText(msg);
    }

    private void Init() {
        font = new Font(getContext());
        txt = (TextView) findViewById(R.id.textView_notify_message);
        txt.setTypeface(font.getFontSuperMario());
        btnCancel = (Button) findViewById(R.id.button_notify_cancel);
        btnCancel.setTypeface(font.getFontDimbo());
        btnCancel.setText(getContext().getString(R.string.cancel));
        btnOk = (Button) findViewById(R.id.button_notify_ok);
        btnOk.setTypeface(font.getFontDimbo());
        btnOk.setText(getContext().getString(R.string.ok));
    }

    private void onClick() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewNotify.cancelClick(exit);
                dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewNotify.okClick(exit);
                dismiss();
            }
        });
    }
}
