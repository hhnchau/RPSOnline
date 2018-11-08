package info.kingpes.rockpaperscissorsonline;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import info.kingpes.rockpaperscissorsonline.Adapter.MessageAdapter;
import info.kingpes.rockpaperscissorsonline.Dialog.Profile.DialogGuestProfile;
import info.kingpes.rockpaperscissorsonline.Location.Country;
import info.kingpes.rockpaperscissorsonline.log.MyLog;
import info.kingpes.rockpaperscissorsonline.models.MessageChat;
import info.kingpes.rockpaperscissorsonline.socket.AppSocket;
import info.kingpes.rockpaperscissorsonline.socket.CallbackFrg_One;
import info.kingpes.rockpaperscissorsonline.socket.CallbackSettings;
import info.kingpes.rockpaperscissorsonline.utils.Key;
import info.kingpes.rockpaperscissorsonline.utils.Utils;

/**
 * Created by Chau Huynh on 07/01/02017.
 */

public class Frg_One extends BaseFragment implements View.OnClickListener {
    private View view;
    private Button btnType, btnChat, btnSend;
    private LinearLayout lnButton, lnInput;
    private EditText edtInput;
    private ListView listView;
    private List<MessageChat> list_message;
    private MessageAdapter adapter_message;

    private InputMethodManager input;
    private boolean softKey = false;

    //Loop Timer
    private class LoopCurrentTime extends TimerTask {

        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MainGame.currentTime = MainGame.currentTime + 60000;
                    updateListView();
                }
            });
        }
    }

    private LoopCurrentTime loopCurrentTime;
    private Timer timer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_one, container, false);
        init();
        on_Message_Chat();
        setScreenName("Frg_One");
        //Start Loop Timer
        timer.schedule(loopCurrentTime, 0, 60000);

        //loopCurrentTime.cancel();
        //timer.cancel();

        return view;
    }

    @Override
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    private void onClickHideSoftKey() {
        //OnClick Listview
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (softKey) {
                    //check keyboard is show to close
                    if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                        hideKeyBoard();
                    }
                }
                return false;
            }
        });
    }

    private void onItemClick() {
        //Item Click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    private void eventEnterClick() {
        //Listener Enter Press
        edtInput.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            hideKeyBoard();
                            if (!edtInput.getText().toString().trim().equals("")) {
                            }
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }

    private void init() {
        timer = new Timer();
        loopCurrentTime = new LoopCurrentTime();

        input = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        btnType = (Button) view.findViewById(R.id.button_type);
        btnType.setText(getString(R.string.local));
        btnType.setTag(getString(R.string.local));
        btnType.setOnClickListener(this);
        btnChat = (Button) view.findViewById(R.id.button_chat);
        btnChat.setText(getString(R.string.chat));
        btnChat.setOnClickListener(this);
        btnSend = (Button) view.findViewById(R.id.button_send);
        btnSend.setOnClickListener(this);
        lnButton = (LinearLayout) view.findViewById(R.id.ln_my_button);
        lnInput = (LinearLayout) view.findViewById(R.id.ln_input);
        edtInput = (EditText) view.findViewById(R.id.edittext_input);
        listView = (ListView) view.findViewById(R.id.listView_one);
        list_message = new ArrayList<MessageChat>();
        adapter_message = new MessageAdapter(getActivity(), R.layout.row_chat, list_message);
        //View view = View.inflate(getActivity(), R.layout.row_tab_two, null);
        //listView.addHeaderView(view);
        //listView.addFooterView(view);
        listView.setAdapter(adapter_message);
    }

    private void setViewPage(final boolean set, final String msg) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (set) {

                } else {

                }
            }
        }, 1);
    }

    private void clearListView() {
        list_message.clear();
    }

    private void changeTypeList() {
        if (isGlobal()) {
            btnType.setText(getString(R.string.local));
            btnType.setTag(getString(R.string.local));
            updateLocalList();
        } else {
            btnType.setText(getString(R.string.global));
            btnType.setTag(getString(R.string.global));
            updateGlobalList();
        }
    }

    private boolean isGlobal() {
        if (btnType.getTag().equals(getString(R.string.global))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_chat:
                showKeyboard();
                break;
            case R.id.button_type:
                changeTypeList();
                break;
            case R.id.button_send:
                hideKeyBoard();
                if (!edtInput.getText().toString().trim().equals("")) {
                    String country = Country.getCountry(getActivity());
                    String msg = edtInput.getText().toString().trim();
                    if (isGlobal()) {
                        emit_Message_Chat(getString(R.string.global), Utils.getIdDevice(getActivity()), "NickName", country, msg, MainGame.currentTime);
                    } else {
                        emit_Message_Chat(country, Utils.getIdDevice(getActivity()), "NickName", country, msg, MainGame.currentTime);
                    }
                }
                break;
        }
    }

    private void showProfileGuset(String id) {
        DialogGuestProfile dialogGuestProfile = new DialogGuestProfile(getActivity(), id);
        dialogGuestProfile.show();
    }

    private void showKeyboard() {
        lnButton.setVisibility(View.GONE);
        lnInput.setVisibility(View.VISIBLE);
        //show
        input.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        edtInput.requestFocus();
        MainGame.tabs.setVisibility(View.GONE);
        softKey = true;
    }

    private void hideKeyBoard() {
        lnButton.setVisibility(View.VISIBLE);
        lnInput.setVisibility(View.GONE);
        //Hide
        input.hideSoftInputFromWindow(view.getWindowToken(), 0);
        MainGame.tabs.setVisibility(View.VISIBLE);
        softKey = false;
    }

    private void updateGlobalList() {
        clearListView();
        list_message.addAll(MainGame.list_global);
        adapter_message.notifyDataSetChanged();
    }

    private void updateLocalList() {
        clearListView();
        list_message.addAll(MainGame.list_local);
        adapter_message.notifyDataSetChanged();
    }

    public void onResume() {
        super.onResume();

        //Check Type, UpdateView
        if (isGlobal()) {
            updateGlobalList();
        } else {
            updateLocalList();
        }

    }

    private void updateListView() {
        if (isGlobal()) {
            updateGlobalList();
        } else {
            updateLocalList();
        }
    }

    private void emit_Message_Chat(String room, String idDevice, String nickName, String address, String msg, long time) {
        MyLog.writeLog("EMIT_MESSAGE_CHAT:-----/////----->: ");
        MessageChat messageChat = new MessageChat();
        messageChat.setRoom(room);
        messageChat.setIdDevice(idDevice);
        messageChat.setNickName(nickName);
        messageChat.setAddress(address);
        messageChat.setMessage(msg);
        messageChat.setTime(time);
        AppSocket.mSocket.emit(Key.MESSAGE_CHAT, messageChat.toJSON());
    }

    private void on_Message_Chat() {
        ((AppSocket) getActivity().getApplication()).listener(new CallbackFrg_One() {
            @Override
            public void onListener(final Object object) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyLog.writeLog("ON_MESSAGE_CHAT:-----/////----->" + object.toString());
                        Gson gson = new Gson();
                        MessageChat messageChat = gson.fromJson(object.toString(), MessageChat.class);

                        //Check Type BodyMessage
                        if (messageChat != null) {
                            if (messageChat.getRoom().equals(getString(R.string.global))) {
                                //Store GLOBAL
                                MainGame.list_global.add(messageChat);
                            } else {
                                //Store LOCAL
                                MainGame.list_local.add(messageChat);
                            }
                        }

                        //Check Type, UpdateView
                        updateListView();

                        //Set Viewpage
                        MainGame.setViewPage(0, "N");
                    }
                });
            }
        });
    }
}
