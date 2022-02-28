package org.artifacts.services;

import org.artifacts.entity.Artifact;
import org.artifacts.entity.ArtifactBackup;
import org.artifacts.repository.ArtifactBackupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class ArtifactBackupService {
    @PersistenceContext
    private EntityManager em;

    private ArtifactBackupRepository artifactBackupRepository;

    @Autowired
    public void setArtifactBackupRepository(ArtifactBackupRepository artifactBackupRepository)
    {
        this.artifactBackupRepository = artifactBackupRepository;
    }



    @Transactional
    public void save(ArtifactBackup artifactBackup)
    {
        em.persist(artifactBackup);
    }
}
