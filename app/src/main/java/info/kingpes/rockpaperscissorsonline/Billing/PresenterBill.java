package info.kingpes.rockpaperscissorsonline.Billing;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import info.kingpes.rockpaperscissorsonline.Model.billingObject;
import info.kingpes.rockpaperscissorsonline.Util.Helper;

/**
 * Created by Chau Huynh on 07/02/02017.
 */

public class PresenterBill implements InterfaceBill {
    private ViewBill viewBill;

    public PresenterBill(ViewBill viewBill) {
        this.viewBill = viewBill;
    }

    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    @Override
    public void setPackageBill() {
        Helper helper = new Helper();
        billingObject bill = new billingObject();
        String key = mData.child(helper.getRoot()).push().getKey();
        bill.setKeyBill(key);
        bill.setNameBill("Package 4");
        bill.setDecsBill("Include 1 wheels");
        bill.setPriceBill("4.99");
        bill.setImgBill(2);
        mData.child(helper.getRoot()).child(helper.getBilling()).child(key).setValue(bill);
    }

    @Override
    public void getPackageBill() {
        Helper helper = new Helper();
        final List<billingObject> packageList = new ArrayList<>();
        mData.child(helper.getRoot()).child(helper.getBilling())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot != null) {
                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                billingObject bill = data.getValue(billingObject.class);
                                packageList.add(bill);
                            }
                        }
                        viewBill.getPackageBilling(packageList);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}
