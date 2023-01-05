package ro.utcluj.helloworld.springboot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ro.utcluj.helloworld.springboot.Model.Song;

@Controller
public class SongController {
    @GetMapping("/songs/{id}")
    public String songPage(@PathVariable String id, Model model) {
        // retrieve song data from a database or service
        Song song = new Song("hello song" + id);
        model.addAttribute("song", song);
        return "song";
    }
}