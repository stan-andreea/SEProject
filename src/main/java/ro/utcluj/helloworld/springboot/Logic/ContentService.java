package ro.utcluj.helloworld.springboot.Logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcluj.helloworld.springboot.Model.Content;

import java.util.List;

@Service
public class ContentService {

    @Autowired
    static ContentRepository contentRepository;


    public List<Content> getAllContent() {
        return (List<Content>) contentRepository.findAll();
    }


    public static Content getContentById(int id) {
        return contentRepository.findById(id).get();
    }

    public void addContent(Content content) {
        contentRepository.save(content);
    }

    public void deleteContent(int id) {
        contentRepository.deleteById(id);
    }
}
