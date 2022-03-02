package org.artifacts.entity;

import org.artifacts.converter.UuidConverter;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "artifact")
public class Artifact implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    @Convert(converter = UuidConverter.class)
    //@Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "created",nullable = false)
    //@GeneratedValue
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "userID")
    private String userID;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    //@Autowired
    public Artifact()
    {

    }

    public Artifact(UUID id,String userID, Date created, String category, String description){
        this.id = id;
        this.created = created;
        this.userID = userID;
        this.category = category;
        this.description = description;
    }

    public Artifact(String id,String userID, String category, String description){
        this.id = UUID.fromString(id);
        this.userID = userID;
        this.category = category;
        this.description = description;
    }

    public Artifact(String userID, String category, String description){
        this.created = new Date();
        this.userID = userID;
        this.category = category;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public void setCreated(Date created)
    {
        this.created = created;
    }
}
