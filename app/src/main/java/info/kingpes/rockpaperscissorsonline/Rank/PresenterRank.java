package info.kingpes.rockpaperscissorsonline.Rank;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.kingpes.rockpaperscissorsonline.Model.rankObject;
import info.kingpes.rockpaperscissorsonline.Util.Helper;

/**
 * Created by Chau Huynh on 06/02/02017.
 */

public class PresenterRank implements InterfaceRank {
    private ViewRank viewRank;
    private Helper helper;

    public PresenterRank(ViewRank viewRank) {
        this.viewRank = viewRank;
        helper = new Helper();
    }

    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    @Override
    public void deleteRank(String id) {
        mData.child(helper.getRoot()).child(helper.getRank()).child(id).removeValue();
    }

    @Override
    public void setRank(String id, String name, String location, int star) {
        Map<String, Object> rank = new HashMap<>();
        rank.put("id", id);
        rank.put("name", name);
        rank.put("location", location);
        rank.put("star", star);
        mData.child(helper.getRoot()).child(helper.getRank()).child(id).setValue(rank);
    }

    @Override
    public void getRank(final String id, int[] area) {
        final List<rankObject> rankList = new ArrayList<>();
        mData.child(helper.getRoot()).child(helper.getRank()).orderByChild("star").startAt(area[0]).endAt(area[1])
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot != null) {
                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                rankObject rank = data.getValue(rankObject.class);
                                rankList.add(rank);
                            }
                        }

                        Collections.sort(rankList, new Comparator<rankObject>() {
                            @Override
                            public int compare(rankObject o1, rankObject o2) {
                                //return o2.getName().compareToIgnoreCase(o1.getName());
                                //Sorting Ascending Order
                                //return (o1.getStar() < o2.getStar()) ? -1 : (o1.getStar() > o2.getStar()) ? 1 : 0;
                                //Sorting Descending Order
                                return (o1.getStar() > o2.getStar()) ? -1 : (o1.getStar() > o2.getStar()) ? 1 : 0;

                            }
                        });

                        int myPosition = 0;
                        for (int i = 0; i < rankList.size(); i++) {
                            if (rankList.get(i).getId().equals(id)) {
                                myPosition = i;
                            }
                        }
                        viewRank.getRank(rankList, myPosition);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}
