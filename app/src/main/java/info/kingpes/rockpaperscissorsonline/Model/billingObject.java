package info.kingpes.rockpaperscissorsonline.Model;

/**
 * Created by Chau Huynh on 03/02/02017.
 */

public class billingObject {
    private String keyBill;
    private String nameBill;
    private String decsBill;
    private String priceBill;
    private int imgBill;

    public billingObject() {
    }

    public billingObject(String keyBill, String nameBill, String decsBill, String priceBill, int imgBill) {
        this.keyBill = keyBill;
        this.nameBill = nameBill;
        this.decsBill = decsBill;
        this.priceBill = priceBill;
        this.imgBill = imgBill;
    }

    public String getKeyBill() {
        return keyBill;
    }

    public void setKeyBill(String keyBill) {
        this.keyBill = keyBill;
    }

    public String getNameBill() {
        return nameBill;
    }

    public void setNameBill(String nameBill) {
        this.nameBill = nameBill;
    }

    public String getDecsBill() {
        return decsBill;
    }

    public void setDecsBill(String decsBill) {
        this.decsBill = decsBill;
    }

    public String getPriceBill() {
        return priceBill;
    }

    public void setPriceBill(String priceBill) {
        this.priceBill = priceBill;
    }

    public int getImgBill() {
        return imgBill;
    }

    public void setImgBill(int imgBill) {
        this.imgBill = imgBill;
    }
}
