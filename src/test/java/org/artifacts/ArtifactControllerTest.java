package org.artifacts;

import static org.assertj.core.api.Assertions.assertThat;
import org.artifacts.controller.ArtifactController;
import org.artifacts.entity.Artifact;
import org.artifacts.repository.ArtifactRepository;
import org.artifacts.services.ArtifactService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ArtifactControllerTest {

    @InjectMocks
    ArtifactController artifactController;

    @Mock
    ArtifactService artifactService;

    @Test
    public void testAddArtifact()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(artifactService.save(any(Artifact.class))).thenReturn(new Artifact());

        Artifact artifact = new Artifact("test","test","test");
        ResponseEntity<Object> responseEntity = artifactController.addArtifact(artifact);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isNotEmpty();
    }

    @Test
    public void testGetArtifact()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(artifactService.findById(UUID.fromString("23333ec9-9dd4-4da9-8a9e-4a783db79b5c"))).thenReturn(Optional.of(new Artifact()));

        Optional<Artifact> optionalArtifact = artifactController.getArtifact("23333ec9-9dd4-4da9-8a9e-4a783db79b5c");

        assertThat(optionalArtifact.get().getId()).isEqualTo(UUID.fromString("23333ec9-9dd4-4da9-8a9e-4a783db79b5c"));
    }

}
