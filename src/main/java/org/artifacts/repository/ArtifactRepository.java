package org.artifacts.repository;


import org.artifacts.entity.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ArtifactRepository extends JpaRepository<Artifact, UUID> {


    @Query("SELECT a FROM Artifact a WHERE a.category = ?1 ORDER BY a.created, a.userid")
    List<Artifact> findByCategory(String category);

    @Query("SELECT a FROM Artifact a WHERE a.description = ?1 ORDER BY a.created, a.category, a.userid")
    List<Artifact> findByDescription(String description);
    @Query("SELECT a FROM Artifact a WHERE a.userid = ?1 ORDER BY a.created, a.category")
    List<Artifact> findByUserId(String userId);
    //List<Artifact> findByUserIdOrderByCreatedAscCategoryAsc(String userId);
    //List<Artifact> findByDescriptionOrderByCreatedAscCategoryAsc(String description);
    //List<Artifact> finByDescription(String description);
}
