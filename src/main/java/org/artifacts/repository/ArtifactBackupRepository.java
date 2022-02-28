package org.artifacts.repository;

import org.artifacts.entity.ArtifactBackup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArtifactBackupRepository extends JpaRepository<ArtifactBackup, UUID> {

}
