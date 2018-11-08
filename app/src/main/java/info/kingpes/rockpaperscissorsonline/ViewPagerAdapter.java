package info.kingpes.rockpaperscissorsonline;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;


public class ViewPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.CustomTabProvider {

    private ArrayList<ViewPagerObject> tabs;
    private Context context;

    public ViewPagerAdapter(FragmentManager fm,Context context, ArrayList<ViewPagerObject> tabs) {
        super(fm);
        this.context = context;
        this.tabs = tabs;
    }

    @Override
    public View getCustomTabView(ViewGroup viewGroup, int i) {
        RelativeLayout tabLayout = (RelativeLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_icons_tablayout, null);

        ImageView tabIcon = (ImageView) tabLayout.findViewById(R.id.my_icon_tabStrip);
        //TextView tabTitle = (TextView) tabLayout.findViewById(R.id.my_title_tabStrip);
        TextView badge = (TextView) tabLayout.findViewById(R.id.badge);

        ViewPagerObject tab = tabs.get(i);

        tabIcon.setImageResource(tab.icon);

//        if(tab.title.equals("")){
//            tabTitle.setVisibility(View.GONE);
//        }else {
//            tabTitle.setVisibility(View.VISIBLE);
//            tabTitle.setText(tab.title.toUpperCase());
//        }

        if (tab.notifications > 0) {
            badge.setVisibility(View.VISIBLE);
            badge.setText(String.valueOf(tab.notifications));
        } else {
            badge.setVisibility(View.GONE);
        }

        return tabLayout;
    }

    @Override
    public void tabSelected(View view) {
        RelativeLayout tabLayout = (RelativeLayout) view;

        TextView badge = (TextView) tabLayout.findViewById(R.id.badge);
        badge.setVisibility(View.GONE);

        ImageView imv = (ImageView) tabLayout.findViewById(R.id.my_icon_tabStrip);
        imv.setScaleX(1.62f);
        imv.setScaleY(1.25f);
    }

    @Override
    public void tabUnselected(View view) {
        RelativeLayout tabLayout = (RelativeLayout) view;
        ImageView imv = (ImageView) tabLayout.findViewById(R.id.my_icon_tabStrip);
        imv.setScaleX(1f);
        imv.setScaleY(1f);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Frg_One();
            case 1:
                return new Frg_Two();
            case 2:
                return new Frg_Three();
            case 3:
                return new Frg_Four();
            case 4:
                return new Frg_Five();
        }
        return new Frg_One();
    }

    @Override
    public int getCount() {
        return tabs.size();
    }
}