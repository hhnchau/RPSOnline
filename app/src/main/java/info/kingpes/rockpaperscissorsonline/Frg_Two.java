package info.kingpes.rockpaperscissorsonline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import info.kingpes.rockpaperscissorsonline.Adapter.RankAdapter;
import info.kingpes.rockpaperscissorsonline.log.MyLog;
import info.kingpes.rockpaperscissorsonline.models.RankUser;
import info.kingpes.rockpaperscissorsonline.models.RealModel;
import info.kingpes.rockpaperscissorsonline.socket.AppSocket;
import info.kingpes.rockpaperscissorsonline.socket.CallbackFrg_Two;
import info.kingpes.rockpaperscissorsonline.socket.CallbackSettings;
import info.kingpes.rockpaperscissorsonline.utils.Key;
import info.kingpes.rockpaperscissorsonline.utils.Utils;

/**
 * Created by Chau Huynh on 07/01/02017.
 */

public class Frg_Two extends BaseFragment implements View.OnClickListener {
    private View view;
    private TextView txt;
    private ListView listView;
    private List<RankUser> rank;
    private RankAdapter adapter_rank;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_two, container, false);
        init();
        on_Get_Rank_User();
        setScreenName("Frg_Two");
        txt.setText("TEST");

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast.makeText(getActivity(), "TEST", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return view;
    }

    @Override
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    private void init() {
        listView = (ListView) view.findViewById(R.id.listView_two);
        rank = new ArrayList<RankUser>();
        adapter_rank = new RankAdapter(getActivity(), R.layout.row_rank, rank);
        View view = View.inflate(getActivity(), R.layout.header_listview_rank, null);
        Button btnLeft = (Button) view.findViewById(R.id.button_left_header_rank);
        btnLeft.setOnClickListener(this);
        Button btnRight = (Button) view.findViewById(R.id.button_right_header_rank);
        btnRight.setOnClickListener(this);
        txt = (TextView) view.findViewById(R.id.textView_header_rank);
        listView.addHeaderView(view);
        //listView.addFooterView(view);
        listView.setAdapter(adapter_rank);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_left_header_rank:

                break;
            case R.id.button_right_header_rank:

                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        emit_Get_Rank_User(Utils.getIdDevice(getActivity()));
    }

    private void emit_Get_Rank_User(String idDevice) {
        MyLog.writeLog("EMIT_GET_RANK_USER:-----/////----->: ");
        RealModel getInfoUser = new RealModel();
        getInfoUser.setIdDevice(idDevice);
        AppSocket.mSocket.emit(Key.RANK_USER, getInfoUser.toJSON());
    }

    private void on_Get_Rank_User(){
        ((AppSocket)getActivity().getApplication()).listener(new CallbackFrg_Two() {
            @Override
            public void onListener(final Object object) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyLog.writeLog("ON_GET_RANK_USER:-----/////----->" + object.toString());
                        Gson gson = new Gson();
                        Type listRankUser = new TypeToken<ArrayList<RankUser>>(){}.getType();
                        List<RankUser> rankUser = gson.fromJson(object.toString(), listRankUser);
                        rank.clear();
                        rank.addAll(rankUser);
                        adapter_rank.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
