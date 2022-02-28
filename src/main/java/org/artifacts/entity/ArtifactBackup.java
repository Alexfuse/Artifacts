package org.artifacts.entity;

import com.google.gson.Gson;
import org.artifacts.converter.UuidConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "artifact_backup")
public class ArtifactBackup implements Serializable {
    @Id
    @Column(name = "id",columnDefinition = "uuid")
    @Convert(converter = UuidConverter.class)
    private UUID id;

    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Column(name = "data")
    private String data;

    public ArtifactBackup()
    {

    }

    public ArtifactBackup(UUID id, String data)
    {
        this.id = id;
        this.data = data;
        this.updated = new Date();
    }

    public ArtifactBackup(UUID id, Artifact artifact)
    {
        this.id = id;
        this.data = new Gson().toJson(artifact);
        this.updated = new Date();
    }

    public ArtifactBackup(UUID id, ArtifactDTO artifact)
    {
        this.id = id;
        this.data = new Gson().toJson(artifact);
        this.updated = new Date();
    }

    public ArtifactBackup(UUID id, String data, Date updated)
    {
        this.id = id;
        this.data = data;
        this.updated = updated;
    }

}
