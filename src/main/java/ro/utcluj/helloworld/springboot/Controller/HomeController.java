package ro.utcluj.helloworld.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.utcluj.helloworld.springboot.Logic.ContentService;
import ro.utcluj.helloworld.springboot.Model.Content;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ContentService contentService;

    public HomeController(ContentService contentService) {
        this.contentService = contentService;
    }

    @RequestMapping(value={"", "/", "home"})
    public String homePage(Model model) {
        List<Content> allSongs = contentService.getAllContent();
        model.addAttribute("songs",allSongs);
        return "home";
    }
}
