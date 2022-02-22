package org.artifacts.services;

import org.artifacts.entity.Comment;
import org.artifacts.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
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
}
