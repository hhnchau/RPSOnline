package info.kingpes.rockpaperscissorsonline.Model;

/**
 * Created by Chau Huynh on 02/02/02017.
 */

public class messageObject {
    public String id;
    private String key;
    private String name;
    private String location;
    private String message;
    private long times;

    public messageObject() {
    }

    public messageObject(String id, String key, String name, String location, String message, long times) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.location = location;
        this.message = message;
        this.times = times;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }
}
