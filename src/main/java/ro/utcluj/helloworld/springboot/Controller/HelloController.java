package ro.utcluj.helloworld.springboot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ro.utcluj.helloworld.springboot.Model.Song;

@Controller
public class HelloController {
    //gets html from a default 'resources/public' or 'resources/static' folder
    @RequestMapping("/")
    public ModelAndView getWelcomePageAsModel() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        return modelAndView;
    }

}
