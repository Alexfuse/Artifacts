package org.artifacts.services;

import org.artifacts.entity.Comment;
import org.artifacts.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Service
public class CommentService {

    @PersistenceContext
    private EntityManager em;

    private CommentRepository commentRepository;
    @Autowired
    public void setCommentRepository(CommentRepository commentRepository)
    {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Comment save(Comment comment)
    {

        return commentRepository.save(comment);
    }

    @Transactional
    public Object findById(UUID id) {
        return commentRepository.findById(id);
    }

    public void deleteById(UUID id) {
         commentRepository.deleteById(id);
    }
}
