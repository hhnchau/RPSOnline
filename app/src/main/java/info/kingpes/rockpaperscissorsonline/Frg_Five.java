package info.kingpes.rockpaperscissorsonline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import info.kingpes.rockpaperscissorsonline.Adapter.BillingAdapter;
import info.kingpes.rockpaperscissorsonline.log.MyLog;
import info.kingpes.rockpaperscissorsonline.models.InfoGame;
import info.kingpes.rockpaperscissorsonline.models.InfoMarket;
import info.kingpes.rockpaperscissorsonline.models.RealModel;
import info.kingpes.rockpaperscissorsonline.socket.AppSocket;
import info.kingpes.rockpaperscissorsonline.socket.CallbackFrg_Five;
import info.kingpes.rockpaperscissorsonline.utils.Key;
import info.kingpes.rockpaperscissorsonline.utils.Params;
import info.kingpes.rockpaperscissorsonline.utils.Utils;


/**
 * Created by Chau Huynh on 07/01/02017.
 */

public class Frg_Five extends BaseFragment {
    private View view;

    private ListView listView;
    private List<InfoMarket> listMarket;
    private BillingAdapter adapterMarket;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_five, container, false);
        init();
        onSocket();
        setListView();
        setScreenName("Frg_Five");
        onClick();

        return view;
    }

    private void init() {
        listView = (ListView) view.findViewById(R.id.listView_five);
        listMarket = new ArrayList<InfoMarket>();
        adapterMarket = new BillingAdapter(getActivity(), R.layout.row_billing, listMarket);
        //View view = View.inflate(getActivity(), R.layout.row_tab_two, null);
        //listView.addHeaderView(view);
        //listView.addFooterView(view);
        listView.setAdapter(adapterMarket);
    }

    public void setListView() {
        listMarket.addAll(MainGame.list_market);
        adapterMarket.notifyDataSetChanged();
    }

    private void onClick(){
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (listMarket.get(i).getType() == Params.MARKET_LIFE){

                }else if (listMarket.get(i).getType() == Params.MARKET_SPINNER){

                }
            }
        });
    }

    @Override
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    @Override
    public void onResume() {
        super.onResume();

        emit_BuyLucky(Utils.getIdDevice(getActivity()));
        emit_BuyLife(Utils.getIdDevice(getActivity()));
    }

    private void emit_BuyLucky(String idDevice) {
        MyLog.writeLog("EMIT_BUY_LIFE:-----/////----->: ");
        RealModel getInfoUser = new RealModel();
        getInfoUser.setIdDevice(idDevice);
        AppSocket.mSocket.emit(Key.LIFE, getInfoUser.toJSON());
    }

    private void emit_BuyLife(String idDevice) {
        MyLog.writeLog("EMIT_BUY_LIFE:-----/////----->: ");
        RealModel getInfoUser = new RealModel();
        getInfoUser.setIdDevice(idDevice);
        AppSocket.mSocket.emit(Key.LIFE, getInfoUser.toJSON());
    }

    private void onSocket(){
        ((AppSocket)getActivity().getApplication()).listener(new CallbackFrg_Five() {
            @Override
            public void onBuyLucky(final Object object) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyLog.writeLog("ON_BUY_LUCKY:-----/////----->" + object.toString());
                        Gson gson = new Gson();
                        InfoGame infoGame = gson.fromJson(object.toString(), InfoGame.class);
                    }
                });
            }

            @Override
            public void onBuyLive(final Object object) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyLog.writeLog("ON_BUY_LIFE:-----/////----->" + object.toString());
                        Gson gson = new Gson();
                        InfoGame infoGame = gson.fromJson(object.toString(), InfoGame.class);
                    }
                });
            }
        });
    }
}
