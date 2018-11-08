package info.kingpes.rockpaperscissorsonline;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Chau Huynh on 07/01/02017.
 */

public class ViewPageChange {
    private LinearLayout tabs;

    public ViewPageChange(LinearLayout tabs) {
        this.tabs = tabs;
    }

    public void notifyTabStripChanged(int position, String notificationsCount) {
        //LinearLayout tabHost = (LinearLayout) tabs.getChildAt(0);
        RelativeLayout tabLayout = (RelativeLayout) tabs.getChildAt(position);
        TextView badge = (TextView) tabLayout.findViewById(R.id.badge);
        if (!notificationsCount.equals("")) {
            badge.setVisibility(View.VISIBLE);
            badge.setText(notificationsCount);
        } else {
            badge.setVisibility(View.GONE);
        }
    }
}
