package org.artifacts.repository;

import org.artifacts.entity.CommentBackup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentBackupRepository extends JpaRepository<CommentBackup, UUID> {
}
