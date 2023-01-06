package ro.utcluj.helloworld.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.utcluj.helloworld.springboot.Logic.ContentService;
import ro.utcluj.helloworld.springboot.Logic.UserRepository;

@Controller
public class LoginController {
    @Autowired
    ContentService contentService;

    public LoginController(ContentService contentService) {
        this.contentService = contentService;
    }



    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }



}
