package info.kingpes.rockpaperscissorsonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import info.kingpes.rockpaperscissorsonline.MainGame;
import info.kingpes.rockpaperscissorsonline.R;
import info.kingpes.rockpaperscissorsonline.Util.Timestamp;
import info.kingpes.rockpaperscissorsonline.models.MessageChat;
import info.kingpes.rockpaperscissorsonline.utils.Utils;

/**
 * Created by Chau Huynh on 05/12/02016.
 */

public class MessageAdapter extends BaseAdapter {
    private Context myContext;
    private int myLayout;
    private List<MessageChat> listMessage;

    public MessageAdapter(Context myContext, int myLayout, List<MessageChat> listMessage) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.listMessage = listMessage;
    }

    @Override
    public int getCount() {
        return listMessage.size();
    }

    @Override
    public Object getItem(int position) {
        return listMessage.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        LinearLayout Right, Left;
        TextView RMessage, LMessage, RName, LName, RLocation, LLocation, RTimes, LTimes;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = convertView;
        ViewHolder viewHolder = new ViewHolder();

        if (rowView == null) {
            rowView = inflater.inflate(myLayout, null);
            //Init
            viewHolder.Right = (LinearLayout) rowView.findViewById(R.id.row_chat_right);
            viewHolder.Left = (LinearLayout) rowView.findViewById(R.id.row_chat_left);
            viewHolder.RMessage = (TextView) rowView.findViewById(R.id.txt_msg_right);
            viewHolder.RName = (TextView) rowView.findViewById(R.id.txt_name_right);
            viewHolder.RLocation = (TextView) rowView.findViewById(R.id.txt_location_right);
            viewHolder.RTimes = (TextView) rowView.findViewById(R.id.txt_times_right);
            viewHolder.LMessage = (TextView) rowView.findViewById(R.id.txt_msg_left);
            viewHolder.LName = (TextView) rowView.findViewById(R.id.txt_name_left);
            viewHolder.LLocation = (TextView) rowView.findViewById(R.id.txt_location_left);
            viewHolder.LTimes = (TextView) rowView.findViewById(R.id.txt_times_left);

            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        //IsMine
        if (listMessage.get(position).getIdDevice().equals(Utils.getIdDevice(myContext))) {
            viewHolder.Right.setVisibility(View.VISIBLE);
            viewHolder.Left.setVisibility(View.GONE);
            viewHolder.RMessage.setText(listMessage.get(position).getMessage());
            viewHolder.RName.setText(listMessage.get(position).getNickName());
            viewHolder.RLocation.setText(listMessage.get(position).getAddress());
            //long now = System.currentTimeMillis();

            long time = MainGame.currentTime - listMessage.get(position).getTime();
            viewHolder.RTimes.setText(Timestamp.convert(time));
        } else {
            viewHolder.Right.setVisibility(View.GONE);
            viewHolder.Left.setVisibility(View.VISIBLE);
            viewHolder.LMessage.setText(listMessage.get(position).getMessage());
            viewHolder.LName.setText(listMessage.get(position).getNickName());
            viewHolder.LLocation.setText(listMessage.get(position).getAddress());
            //long now = System.currentTimeMillis();

            long time = MainGame.currentTime - listMessage.get(position).getTime();
            viewHolder.LTimes.setText(Timestamp.convert(time));
        }
        return rowView;
    }
}
