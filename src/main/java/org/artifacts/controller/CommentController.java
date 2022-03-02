package org.artifacts.controller;

import org.artifacts.entity.Comment;
import org.artifacts.entity.CommentBackup;
import org.artifacts.entity.CommentDTO;
import org.artifacts.entity.MissingParamError;
import org.artifacts.services.CommentBackupService;
import org.artifacts.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;
    @Autowired
    public void setCommentService(CommentService commentService){this.commentService = commentService;}

    private CommentBackupService commentBackupService;
    @Autowired
    public void setCommentBackupService(CommentBackupService commentBackupService){this.commentBackupService = commentBackupService;}

    @RequestMapping(
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Object addComment(@RequestBody Comment comment)
    {
        Comment newComment = commentService.save(comment);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{UUID}")
                .buildAndExpand(newComment.getId())
                .toUri();

        //Send location in response
        return ResponseEntity.created(location).build();

    }

    @RequestMapping(
            method = RequestMethod.PUT,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Object updateComment(@RequestBody CommentDTO comment)
    {
        if(comment.getId() != null)
        {
            Comment updateComment = new Comment();
            updateComment.setId(comment.getId());
            updateComment.setArtifactId(comment.getArtifactId());
            updateComment.setUserID(comment.getUserID());
            updateComment.setContent(comment.getContent());
            commentBackupService.save(new CommentBackup(updateComment.getId(), updateComment));
            return commentService.save(updateComment);
        }
        else
            return new MissingParamError("Id");
    }

    @RequestMapping(path = "/{UUID}",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Object getComment(@PathVariable("UUID") String uuid)
    {
        UUID id = UUID.fromString(uuid);
        return commentService.findById(id);
    }

    @RequestMapping(path = "/{UUID}",
            method = RequestMethod.DELETE,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteComment(@PathVariable("UUID") String uuid)
    {
        UUID id = UUID.fromString(uuid);
        commentService.deleteById(id);
    }


}
