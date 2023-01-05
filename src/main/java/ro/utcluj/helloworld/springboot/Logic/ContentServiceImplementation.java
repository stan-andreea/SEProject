package ro.utcluj.helloworld.springboot.Logic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcluj.helloworld.springboot.Model.Content;

import java.util.List;

@Service
@Transactional
public class ContentServiceImplementation implements ContentService {

    @Autowired
    ContentRepository contentRepository;

    @Override
    public List<Content> getAllContent() {
        return (List<Content>) contentRepository.findAll();
    }

    @Override
    public Content getContentById(int id) {
        return contentRepository.findById(id).get();
    }

    @Override
    public void addContent(Content content) {
       contentRepository.save(content);
    }

    @Override
    public void deleteContent(int id) {
        contentRepository.deleteById(id);
    }
}
