package org.artifacts.entity;

import java.util.UUID;

public class CommentDTO {

    private UUID id;
    private UUID artifactId;
    private String userID;
    private String content;

    public CommentDTO(){

    }



    public CommentDTO(UUID id, UUID artifactId, String userID, String content)
    {
        this.id = id;
        this.artifactId = artifactId;
        this.userID = userID;
        this.content = content;
    }

    public CommentDTO(String id, String artifactId, String userID, String content)
    {
        this.id = UUID.fromString(id);
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
