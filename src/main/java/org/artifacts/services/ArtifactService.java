package org.artifacts.services;

import org.artifacts.entity.Artifact;
import org.artifacts.entity.ArtifactDTO;
import org.artifacts.repository.ArtifactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArtifactService {

    private ArtifactRepository artifactRepository;
    @Autowired
    public void setArtifactRepository(ArtifactRepository artifactRepository)
    {
        this.artifactRepository = artifactRepository;
    }

    @Transactional
    public Optional<Artifact> findById(UUID id)
    {
        return artifactRepository.findById(id);
    }

    @Transactional
    public ArtifactDTO save(Artifact Artifact)
    {
        Artifact object = artifactRepository.save(Artifact);
        ArtifactDTO artifactDTO = new ArtifactDTO();
        artifactDTO.setId(object.getId());
        artifactDTO.setCategory(object.getCategory());
        artifactDTO.setCreated(object.getCreated());
        artifactDTO.setUserID(object.getUserID());
        artifactDTO.setDescription(object.getDescription());
        return artifactDTO;
    }
    @Transactional
    public void deleteById (UUID id)
    {
        artifactRepository.deleteById(id);
    }

    @Transactional
    public List<Artifact> findByCategory(String category)
    {
        return artifactRepository.findByCategory(category);
    }

    @Transactional
    public List<Artifact> findByUserId(String userId)
    {
        return artifactRepository.findByUserId(userId);
    }

    @Transactional
    public List<Artifact> findByDescription(String description)
    {
        return artifactRepository.findByDescription(description);
    }

}
