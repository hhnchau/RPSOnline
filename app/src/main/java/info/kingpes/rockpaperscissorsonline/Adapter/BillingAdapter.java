package info.kingpes.rockpaperscissorsonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import info.kingpes.rockpaperscissorsonline.R;
import info.kingpes.rockpaperscissorsonline.models.InfoMarket;

/**
 * Created by Chau Huynh on 03/02/02017.
 */

public class BillingAdapter extends BaseAdapter {
    private Context myContext;
    private int myLayout;
    private List<InfoMarket> arayBilling;
    private int IMG[] = {R.drawable.package_1, R.drawable.package_2, R.drawable.package_3, R.drawable.package_4};
    public BillingAdapter(Context myContext, int myLayout, List<InfoMarket> arayBilling) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.arayBilling = arayBilling;
    }

    @Override
    public int getCount() {
        return arayBilling.size();
    }

    @Override
    public Object getItem(int i) {
        return arayBilling.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder {
        private TextView txtPackageName, txtPackageDecs, txtCurrency;
        private ImageView img;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = view;
        ViewHolder viewHolder = new ViewHolder();

        if (rowView == null) {
            rowView = inflater.inflate(myLayout, null);
            //Init
            viewHolder.txtPackageName = (TextView) rowView.findViewById(R.id.textView_billing_name);
            viewHolder.txtPackageDecs = (TextView) rowView.findViewById(R.id.textView_billing_decscription);
            viewHolder.txtCurrency = (TextView) rowView.findViewById(R.id.textView_billing_currency);
            viewHolder.img = (ImageView) rowView.findViewById(R.id.imageView_billing);

            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        viewHolder.txtPackageName.setText(arayBilling.get(i).getTitle());
        viewHolder.txtPackageDecs.setText(arayBilling.get(i).getDes());
        viewHolder.txtCurrency.setText(arayBilling.get(i).getPrice() + "");
        //viewHolder.img.setImageResource(IMG[arayBilling.get(i).getPic()]);

        return rowView;
    }
}
