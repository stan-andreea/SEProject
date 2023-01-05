package ro.utcluj.helloworld.springboot.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.utcluj.helloworld.springboot.Model.CountDemo;
import ro.utcluj.helloworld.springboot.Model.Song;

import java.security.PublicKey;

@Controller
public class SongController {

    @GetMapping("/songs/{id}")
    public String songPage(@PathVariable String id, Model model, Authentication authentication) {
        // retrieve song data from a database or service

        Song song = new Song("hello song " + authentication.getName());
        model.addAttribute("song", song);
        model.addAttribute("count",CountDemo.counter);
        model.addAttribute("id",id);
        return "song";
    }

    /*@PostMapping("/like")
    public void likeSubmit(){
        System.out.println("like");
        countDemo.counter = countDemo.counter+1;
    }*/

}