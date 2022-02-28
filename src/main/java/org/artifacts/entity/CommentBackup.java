package org.artifacts.entity;

import com.google.gson.Gson;
import org.artifacts.converter.UuidConverter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "comment_backup")
public class CommentBackup {
    @Id
    @Column(name = "id",columnDefinition = "uuid")
    @Convert(converter = UuidConverter.class)
    private UUID id;

    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Column(name = "data")
    private String data;

    public CommentBackup()
    {

    }

    public CommentBackup(UUID id, String data)
    {
        this.id = id;
        this.data = data;
        this.updated = new Date();
    }

    public CommentBackup(UUID id, Comment comment)
    {
        this.id = id;
        this.data = new Gson().toJson(comment);
        this.updated = new Date();
    }

    public CommentBackup(UUID id, CommentDTO comment)
    {
        this.id = id;
        this.data = new Gson().toJson(comment);
        this.updated = new Date();
    }

    public CommentBackup(UUID id, String data, Date updated)
    {
        this.id = id;
        this.data = data;
        this.updated = updated;
    }
}
