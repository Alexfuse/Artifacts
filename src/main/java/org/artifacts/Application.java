package org.artifacts;


import org.artifacts.entity.Artifact;
import org.artifacts.repository.ArtifactRepository;
import org.artifacts.services.ArtifactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

   /* @Bean
    public CommandLineRunner demo(ArtifactRepository artifactRepository)
    {
        return (args) ->{
           try {
               artifactRepository.saveAndFlush(new Artifact("ALexander", "Books", "Old book from syberia"));
           }catch (Exception e)
           {
               System.out.println(e.getMessage());
           }
            artifactRepository.saveAndFlush(new Artifact("ALexander", "Vase", "Broken vase"));
            artifactRepository.saveAndFlush(new Artifact("ALexander", "Sword", "Big metal sword"));
        };
    }*/

    @Autowired
    private ArtifactService artifactService;
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    private void testJpaMethods()
    {
        artifactService.save(new Artifact("ALexander", "Vase", "Broken vase"));
    }


}
