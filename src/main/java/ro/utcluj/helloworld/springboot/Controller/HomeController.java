package ro.utcluj.helloworld.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.utcluj.helloworld.springboot.Logic.ContentService;
import ro.utcluj.helloworld.springboot.Logic.Recommandation.RecommenderSystem;
import ro.utcluj.helloworld.springboot.Logic.UserService;
import ro.utcluj.helloworld.springboot.Model.Content;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ContentService contentService;

    @Autowired
    UserService userService;

    public HomeController(ContentService contentService) {
        this.contentService = contentService;
    }

    @RequestMapping(value={"", "/", "home"})
    public String homePage(Model model, Authentication authentication) {
        RecommenderSystem recommenderSystem = new RecommenderSystem(userService,contentService);
        List<Content> recommendedSongs = recommenderSystem.getRecommmandationList(userService.getUserByUserName(authentication.getName()));
        model.addAttribute("songs",recommendedSongs);
        return "home";
    }
}
