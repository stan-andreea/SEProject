package ro.utcluj.helloworld.springboot.Logic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.utcluj.helloworld.springboot.Model.Content;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContentRepositoryTest {

    @Autowired
    private ContentRepository contentRepository;

    @Test
    void saveMethod(){
        Content content = new Content();
        content.setTitle("Broscuta pe lac");
        content.setDescription("Nice");
        content.setDislikes(0);
        content.setImage("image.png");
        content.setLikes(0);
        content.setViews(0);

        Content savedContent = contentRepository.save(content);
        System.out.println(savedContent.getId());
        System.out.println(savedContent.toString());
    }

}