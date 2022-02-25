package org.artifacts.controller;

import org.artifacts.entity.Artifact;
import org.artifacts.entity.ArtifactDTO;
import org.artifacts.entity.Comment;
import org.artifacts.entity.MissingParamError;
import org.artifacts.services.ArtifactService;
import org.artifacts.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/artifact")
public class ArtifactController {


    private ArtifactService artifactService;

    @Autowired
    public void setArtifactService(ArtifactService artifactService){
        this.artifactService=artifactService;
    }

   

    @RequestMapping(path = "/test")
    public String test()
    {
        return "0";
    }

    @RequestMapping(path = "/{UUID}",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Object getArtifact(@PathVariable("UUID") String uuid)
    {
        UUID id = UUID.fromString(uuid);
        return artifactService.findById(id);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Object addArtifact(@RequestBody Artifact artifact)
    {
        artifact.setCreated(new Date());
        return artifactService.save(artifact);
    }
    @RequestMapping(
            method = RequestMethod.PUT,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Object updateArtifact(@RequestBody ArtifactDTO artifact)
    {
        if(artifact.getId() != null)
        {
            Artifact updArtifact = new Artifact(artifact.getId(),artifact.getUserID(),artifact.getCategory(),artifact.getDescription());
            return artifactService.save(updArtifact);
        }
        else
            return new MissingParamError("Id");
    }

    @RequestMapping(path = "/{UUID}",
            method = RequestMethod.DELETE,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteArtifact(@PathVariable("UUID") String uuid)
    {
        UUID id = UUID.fromString(uuid);
        artifactService.deleteById(id);
    }

    @RequestMapping(path = "/search",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Artifact> searchArtifact(@RequestBody Map<String,String> searchObject)
    {
        System.out.println(searchObject);
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


}
