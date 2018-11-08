package info.kingpes.rockpaperscissorsonline.models;

import java.util.List;

/**
 * Created by Chau Huynh on 6/6/2017.
 */

public class RankUser {
    private String idDevice;
    private String nickName;
    private String address;
    private int champion;
    private int maxChampion;
    private int rank;

    public RankUser() {
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
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

    public int getChampion() {
        return champion;
    }

    public void setChampion(int champion) {
        this.champion = champion;
    }

    public int getMaxChampion() {
        return maxChampion;
    }

    public void setMaxChampion(int maxChampion) {
        this.maxChampion = maxChampion;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
