package org.artifacts.controller;

import org.artifacts.entity.Artifact;
import org.artifacts.services.ArtifactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ControllerRest {

    /*@Autowired
    private ArtifactRepository artifactRepository;*/


    private ArtifactService artifactService;
    @Autowired
    public void setArtifactService(ArtifactService artifactService){
        this.artifactService=artifactService;
    }

    @RequestMapping(path = "/artifact/{UUID}",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Object getArtifact(@PathVariable("UUID")UUID id)
    {
        return artifactService.findById(id);
    }

    @RequestMapping(path = "/artifact",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Artifact addArtifact(@RequestBody Artifact artifact)
    {
        return artifactService.save(artifact);
    }
    @RequestMapping(path = "/artifact",
            method = RequestMethod.PUT,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Artifact updateArtifact(@RequestBody Artifact artifact)
    {

        return artifactService.save(artifact);
    }

    @RequestMapping(path = "/artifact/{UUID}",
            method = RequestMethod.DELETE,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteArtifact(@PathVariable("UUID")UUID id)
    {
        artifactService.deleteById(id);
    }

}
