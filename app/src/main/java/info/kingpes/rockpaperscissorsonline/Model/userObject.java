package info.kingpes.rockpaperscissorsonline.Model;

/**
 * Created by Chau Huynh on 24/01/02017.
 */

public class userObject {
    private String name;
    private String location;
    private String avatar;
    private int star;
    private int life;
    private int win;
    private int lost;
    private int wheel;

    public userObject() {
    }

    public userObject(String name, String location, String avatar, int star, int life, int win, int lost, int wheel) {
        this.name = name;
        this.location = location;
        this.avatar = avatar;
        this.star = star;
        this.life = life;
        this.win = win;
        this.lost = lost;
        this.wheel = wheel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public int getWheel() {
        return wheel;
    }

    public void setWheel(int wheel) {
        this.wheel = wheel;
    }
}
