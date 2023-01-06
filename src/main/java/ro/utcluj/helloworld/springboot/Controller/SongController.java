package ro.utcluj.helloworld.springboot.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.utcluj.helloworld.springboot.Model.Content;
import ro.utcluj.helloworld.springboot.Model.CountDemo;
import ro.utcluj.helloworld.springboot.Model.Song;

import java.security.PublicKey;

@Controller
public class SongController {

    @GetMapping("/songs/{id}")
    public String songPage(@PathVariable String id, Model model, Authentication authentication) {
        // retrieve song data from a database or service

        Content song = new Content("hello song ",2);
        model.addAttribute("song", song);
        model.addAttribute("count",CountDemo.counter);
        model.addAttribute("id",id);
        return "song";
    }

}