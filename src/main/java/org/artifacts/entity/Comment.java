package org.artifacts.entity;

import org.artifacts.converter.UuidConverter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;
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

    @Column(name = "artefactID", columnDefinition = "uuid")
    //@Convert(converter = UuidConverter.class)
    @JoinColumn(name = "artifact.id")
    private UUID artefactId;

    @Column(name = "userID")
    private String userID;

    @Column(name = "content")
    private String content;

    public Comment(){

    }

    public Comment(UUID artefactId, String userID, String content)
    {
        this.artefactId = artefactId;
        this.userID = userID;
        this.content = content;
    }

    public Comment(UUID id,UUID artefactId, String userID, String content)
    {
        this.id = id;
        this.artefactId = artefactId;
        this.userID = userID;
        this.content = content;
    }

    public Comment(String id,String artefactId, String userID, String content)
    {
        this.id = UUID.fromString(id);
        this.artefactId = UUID.fromString(artefactId);
        this.userID = userID;
        this.content = content;
    }
    public Comment(String artefactId, String userID, String content)
    {
        this.artefactId = UUID.fromString(artefactId);
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

    public UUID getArtefactId() {
        return artefactId;
    }

    public void setArtefactId(UUID artefactId) {
        this.artefactId = artefactId;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
