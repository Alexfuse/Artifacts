package org.artifacts.repository;


import org.artifacts.entity.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArtifactRepository extends JpaRepository<Artifact, UUID> {

    //Optional<Artifact> findById(UUID id);
    List<Artifact> findByCategory(String category);
    List<Artifact> findByUserID(String userId);
    //List<Artifact> finByDescription(String description);
}
