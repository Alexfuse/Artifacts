package org.artifacts.entity;

import org.artifacts.converter.UuidConverter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "id")
    @GeneratedValue
    @Convert(converter = UuidConverter.class)
    private UUID id;

    @Column(name = "artifactid",columnDefinition = "uuid")//, columnDefinition = "uuid"
    //@Convert(converter = UuidConverter.class)
    @Convert(disableConversion = true)
    @JoinColumn(name = "artifact_id", nullable=true)
    private UUID artifactId;

    @Column(name = "userID")
    private String userID;

    @Column(name = "content")
    private String content;

    public Comment(){

    }

    public Comment(UUID artifactId, String userID, String content)
    {
        this.artifactId = artifactId;
        this.userID = userID;
        this.content = content;
    }

    public Comment(UUID id, UUID artifactId, String userID, String content)
    {
        this.id = id;
        this.artifactId = artifactId;
        this.userID = userID;
        this.content = content;
    }

    public Comment(String id, String artifactId, String userID, String content)
    {
        this.id = UUID.fromString(id);
        this.artifactId = UUID.fromString(artifactId);
        this.userID = userID;
        this.content = content;
    }
    public Comment(String artifactId, String userID, String content)
    {
        this.artifactId = UUID.fromString(artifactId);
        this.userID = userID;
        this.content = content;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public String getContent() {
        return content;
    }

    public UUID getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(UUID artifactId) {
        this.artifactId = artifactId;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
