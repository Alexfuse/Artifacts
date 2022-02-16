package org.artifacts.entity;

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
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "artefactID")
    @Type(type="org.hibernate.type.PostgresUUIDType")
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
}
