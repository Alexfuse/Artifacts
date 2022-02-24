package org.artifacts.repository;


import org.artifacts.entity.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ArtifactRepository extends JpaRepository<Artifact, UUID> {


    @Query("SELECT a FROM Artifact a WHERE a.category = ?1 ORDER BY a.created, a.userID")
    List<Artifact> findByCategory(String category);

    @Query("SELECT a FROM Artifact a WHERE a.description = ?1 ORDER BY a.created, a.category, a.userID")
    List<Artifact> findByDescription(String description);

    @Query(value = "SELECT * FROM artifact a WHERE a.userid = ?1 ORDER BY a.created, a.category, a.userID",nativeQuery = true)
    List<Artifact> findByUserId(String userId);

    @Query(value = "SELECT a.* FROM artifact a JOIN comments c ON c.artifactid = a.id WHERE c.content LIKE %?1% ORDER BY a.created, a.category, a.userID",nativeQuery = true)
    List<Artifact> findByComment( String comment);

    //List<Artifact> findByUserIdOrderByCreatedAscCategoryAsc(String userId);
    //List<Artifact> findByDescriptionOrderByCreatedAscCategoryAsc(String description);
    //List<Artifact> finByDescription(String description);
}
