package ro.utcluj.helloworld.springboot.Logic;

import org.springframework.stereotype.Service;
import ro.utcluj.helloworld.springboot.Model.Content;

import java.util.List;

@Service
public interface ContentService {

    public List<Content> getAllContent();

    public Content getContentById(int id);

    public void addContent(Content content);

    public void deleteContent(int id);
}
