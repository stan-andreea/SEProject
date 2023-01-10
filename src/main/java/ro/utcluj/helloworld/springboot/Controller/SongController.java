package ro.utcluj.helloworld.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.utcluj.helloworld.springboot.Logic.ContentService;
import ro.utcluj.helloworld.springboot.Logic.UserService;
import ro.utcluj.helloworld.springboot.Model.Content;

@Controller
public class SongController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContentService contentService;

    @GetMapping("/songs/{id}")
    public String songPage(@PathVariable String id, Model model, Authentication authentication) {
        // retrieve song data from a database or service
        Content song  = contentService.getContentById(Integer.parseInt(id));
        song.setViews(song.getViews() + 1);
        contentService.addContent(song);
        model.addAttribute("song", song);
        model.addAttribute("id",id);
        return "song";
    }

}