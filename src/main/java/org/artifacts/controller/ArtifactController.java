package org.artifacts.controller;

import org.artifacts.entity.*;
import org.artifacts.services.ArtifactBackupService;
import org.artifacts.services.ArtifactService;
import org.artifacts.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/artifact")
public class ArtifactController {


    private ArtifactService artifactService;

    private ArtifactBackupService artifactBackupService;

    @Autowired
    public void setArtifactService(ArtifactService artifactService){
        this.artifactService=artifactService;
    }

    @Autowired
    public void setArtifactBackupService(ArtifactBackupService artifactBackupService){
        this.artifactBackupService=artifactBackupService;
    }


    @RequestMapping(path = "/{UUID}",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Optional<Artifact> getArtifact(@PathVariable("UUID") String uuid)
    {
        UUID id = UUID.fromString(uuid);
        return artifactService.findById(id);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public ResponseEntity<Object> addArtifact(@RequestBody Artifact artifact)
    {
        artifact.setCreated(new Date());
        Artifact newArtifact =  artifactService.save(artifact);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{UUID}")
                .buildAndExpand(newArtifact.getId())
                .toUri();

        //Send location in response
        return ResponseEntity.created(location).build();
    }
    @RequestMapping(
            method = RequestMethod.PUT,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Object updateArtifact(@RequestBody ArtifactDTO artifact)
    {
        if(artifact.getId() != null)
        {
            Artifact updArtifact = new Artifact(artifact.getId(),artifact.getUserID(),artifact.getCreated(),artifact.getCategory(),artifact.getDescription());
            artifactBackupService.save(new ArtifactBackup(updArtifact.getId(), updArtifact));
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
