package ro.utcluj.helloworld;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import ro.utcluj.helloworld.springboot.Logic.ContentService;
import ro.utcluj.helloworld.springboot.Logic.UserRepository;
import ro.utcluj.helloworld.springboot.Model.Content;


@SpringBootApplication
@EnableCaching
@EnableAsync
@ConfigurationPropertiesScan
public class HelloworldApplication {
    @Autowired
    ContentService contentService;

    @Autowired
    private UserRepository userRepo;

    public HelloworldApplication(ContentService contentService) {
        this.contentService = contentService;
        //contentService.addContent(new Content("song name",1));
        //contentService.addContent(new Content("Hehe",2));
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloworldApplication.class, args);
    }

}
