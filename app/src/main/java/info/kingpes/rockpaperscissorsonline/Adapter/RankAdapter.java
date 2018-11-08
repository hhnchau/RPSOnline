package info.kingpes.rockpaperscissorsonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import info.kingpes.rockpaperscissorsonline.R;
import info.kingpes.rockpaperscissorsonline.models.RankUser;
import info.kingpes.rockpaperscissorsonline.utils.Utils;

/**
 * Created by Chau Huynh on 03/02/02017.
 */

public class RankAdapter extends BaseAdapter {
    private Context myContext;
    private int myLayout;
    private List<RankUser> rankUser;

    public RankAdapter(Context myContext, int myLayout, List<RankUser> rankUser) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.rankUser = rankUser;
    }

    @Override
    public int getCount() {
        return rankUser.size();
    }

    @Override
    public Object getItem(int i) {
        return rankUser.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder {
        private TextView txtPosition, txtName, txtLocation, txtStar;
        private LinearLayout lnRank;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = view;
        ViewHolder viewHolder = new ViewHolder();

        if (rowView == null) {
            rowView = inflater.inflate(myLayout, null);
            //Init
            viewHolder.txtPosition = (TextView) rowView.findViewById(R.id.textView_award_position);
            viewHolder.txtName = (TextView) rowView.findViewById(R.id.textView_award_name);
            viewHolder.txtLocation = (TextView) rowView.findViewById(R.id.textView_award_location);
            viewHolder.txtStar = (TextView) rowView.findViewById(R.id.textView_award_star);
            viewHolder.lnRank = (LinearLayout) rowView.findViewById(R.id.linnear_row_rank);

            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        if (rankUser != null) {
            if (rankUser.get(i).getIdDevice().equals(Utils.getIdDevice(myContext))) {
                //viewHolder.txtPosition.setTextColor(R.drawable.bg_row_item);
            }
            viewHolder.txtName.setText(rankUser.get(i).getNickName());
            viewHolder.txtLocation.setText(rankUser.get(i).getAddress());
            viewHolder.txtStar.setText(rankUser.get(i).getMaxChampion() + "/" + rankUser.get(i).getChampion());
            viewHolder.txtPosition.setText(rankUser.get(i).getRank() + ".");
        }

        return rowView;
    }
}
