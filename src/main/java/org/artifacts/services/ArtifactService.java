package org.artifacts.services;

import org.artifacts.entity.Artifact;
import org.artifacts.repository.ArtifactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArtifactService {
    @Autowired
    private ArtifactRepository artifactRepository;

    /*public ArtifactService(ArtifactRepository artifactRepository) {
        this.artifactRepository = artifactRepository;
    }*/
    @Transactional
    public Optional<Artifact> findById(UUID id)
    {
        return artifactRepository.findById(id);
    }
    @Transactional
    public Artifact save(Artifact artifact)
    {
        return artifactRepository.saveAndFlush(artifact);
    }
    @Transactional
    public void deleteById (UUID id)
    {
        artifactRepository.deleteById(id);
    }
}
