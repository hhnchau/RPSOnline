package info.kingpes.rockpaperscissorsonline.models;

import java.util.List;

/**
 * Created by Chau Huynh on 6/15/2017.
 */

public class InfoGame {
    private InfoUser infoUser;
    private List<RankUser> rankUser;

    public InfoGame() {
    }

    public InfoUser getInfoUser() {
        return infoUser;
    }

    public void setInfoUser(InfoUser infoUser) {
        this.infoUser = infoUser;
    }

    public List<RankUser> getRankUser() {
        return rankUser;
    }

    public void setRankUser(List<RankUser> rankUser) {
        this.rankUser = rankUser;
    }
}
