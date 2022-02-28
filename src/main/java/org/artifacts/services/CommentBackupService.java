package org.artifacts.services;

import org.artifacts.entity.CommentBackup;
import org.artifacts.repository.CommentBackupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class CommentBackupService {
    @PersistenceContext
    private EntityManager em;

    private CommentBackupRepository commentBackupRepository;

    @Autowired
    public void setCommentBackupRepository(CommentBackupRepository commentBackupRepository)
    {
        this.commentBackupRepository = commentBackupRepository;
    }

    @Transactional
    public void save(CommentBackup commentBackup)
    {
        em.persist(commentBackup);
    }
}
