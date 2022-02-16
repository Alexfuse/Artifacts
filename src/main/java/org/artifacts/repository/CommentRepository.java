package org.artifacts.repository;


import org.artifacts.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    Optional<Comment> findByIdArtifact(UUID idArtifact);
}
