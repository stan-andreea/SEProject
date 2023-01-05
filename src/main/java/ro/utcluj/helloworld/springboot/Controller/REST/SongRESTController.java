package ro.utcluj.helloworld.springboot.Controller.REST;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.utcluj.helloworld.springboot.Model.CountDemo;

@RestController
public class SongRESTController {
    @PostMapping("/like")
    public String likeSubmit(){
        System.out.println("like");
        CountDemo.increment();
        return CountDemo.counter.toString();
    }
}
