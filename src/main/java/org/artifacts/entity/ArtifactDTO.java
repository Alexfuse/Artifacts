package org.artifacts.entity;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class ArtifactDTO {
    private UUID id;
    private Date created;
    private String userID;
    private String category;
    private String description;

    @Autowired
    public ArtifactDTO()
    {

    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public UUID getId() {
        return id;
    }
}
