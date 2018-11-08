package info.kingpes.rockpaperscissorsonline.Model;

/**
 * Created by Chau Huynh on 03/02/02017.
 */

public class rankObject {
    private String id;
    private String name;
    private String location;
    private int star;

    public rankObject() {
    }

    public rankObject(String id, String name, String location, int star) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.star = star;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
