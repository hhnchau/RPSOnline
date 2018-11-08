package info.kingpes.rockpaperscissorsonline.Rank;

/**
 * Created by Chau Huynh on 06/02/02017.
 */

public interface InterfaceRank {
    void deleteRank(String id);

    void setRank(String id, String name, String location, int star);

    void getRank(String id, int[] area);
}
