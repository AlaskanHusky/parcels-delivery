package by.parcelsdelivery.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "parcel")
public class ParcelEntity {

    private int id;
    private String sender;
    private String message;
    private int[] path;
    public enum status {DELIVERED, TRANSIT, ON_NEXT_POINT}

    @Id
    @Column(name = "parcel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "sender")
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Column(name = "path")
    public int[] getPath() {
        return path;
    }

    public void setPath(int[] path) {
        this.path = path;
    }

}
