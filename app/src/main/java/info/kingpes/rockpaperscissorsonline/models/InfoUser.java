package info.kingpes.rockpaperscissorsonline.models;

/**
 * Created by Chau Huynh on 6/5/2017.
 */

public class InfoUser {
    private String nickName;
    private String address;
    private int star;
    private int life;
    private int win;
    private int lost;
    private int draw;
    private int maxChampion;
    private int champion;
    private int spin;
    private int rank;

    public InfoUser() {
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getMaxChampion() {
        return maxChampion;
    }

    public void setMaxChampion(int maxChampion) {
        this.maxChampion = maxChampion;
    }

    public int getChampion() {
        return champion;
    }

    public void setChampion(int champion) {
        this.champion = champion;
    }

    public int getSpin() {
        return spin;
    }

    public void setSpin(int spin) {
        this.spin = spin;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
