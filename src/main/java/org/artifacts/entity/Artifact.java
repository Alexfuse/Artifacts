package org.artifacts.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "artifact")
public class Artifact {
    @Id
    @Column(name = "id")
    @GeneratedValue
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "created")
    @GeneratedValue
    private Date created;

    @Column(name = "userID")
    private String userID;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    public Artifact()
    {

    }

    public Artifact(String userID, String category, String description){
        this.userID = userID;
        this.category = category;
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getUserID() {
        return userID;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
