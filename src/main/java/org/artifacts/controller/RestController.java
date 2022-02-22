package org.artifacts.controller;

import org.artifacts.entity.Artifact;
import org.artifacts.entity.ArtifactDTO;
import org.artifacts.entity.Comment;
import org.artifacts.services.ArtifactService;
import org.artifacts.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@org.springframework.web.bind.annotation.RestController
public class RestController {


    private ArtifactService artifactService;
    private CommentService commentService;
    @Autowired
    public void setArtifactService(ArtifactService artifactService){
        this.artifactService=artifactService;
    }

    @Autowired
    public void setCommentService(CommentService commentService){this.commentService = commentService;}

    @RequestMapping(path = "/test")
    public String test()
    {
        return "0";
    }

    @RequestMapping(path = "/artifact/{UUID}",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Object getArtifact(@PathVariable("UUID") String uuid)
    {
        UUID id = UUID.fromString(uuid);
        return artifactService.findById(id);
    }

    @RequestMapping(path = "/artifact",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public ArtifactDTO addArtifact(@RequestBody Artifact artifact)
    {
        artifact.setCreated(new Date());
        return artifactService.save(artifact);
    }
    @RequestMapping(path = "/artifact",
            method = RequestMethod.PUT,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public ArtifactDTO updateArtifact(@RequestBody Artifact artifact)
    {
        if(artifact.getCreated() == null)
            artifact.setCreated(new Date());
        return artifactService.save(artifact);
    }

    @RequestMapping(path = "/artifact/{UUID}",
            method = RequestMethod.DELETE,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteArtifact(@PathVariable("UUID") String uuid)
    {
        UUID id = UUID.fromString(uuid);
        artifactService.deleteById(id);
    }

    @RequestMapping(path = "/artifact/search",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Artifact> searchArtifact(@RequestBody Map<String,String> searchObject)
    {
        if(searchObject.containsKey("Category"))
           return artifactService.findByCategory(searchObject.get("Category"));
        if(searchObject.containsKey("UserID"))
           return artifactService.findByUserId(searchObject.get("UserID"));
        if(searchObject.containsKey("Description"))
           return artifactService.findByDescription( searchObject.get("Description"));
        if(searchObject.containsKey("Comment"))
           return artifactService.findByComment( searchObject.get("Comment"));
        return null;
    }

    @RequestMapping(path = "/comments",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Comment addComment(@RequestBody Comment comment)
    {
        return commentService.save(comment);
    }
}
