package ro.utcluj.helloworld.springboot.Controller.REST;


import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.utcluj.helloworld.springboot.Logic.ContentService;
import ro.utcluj.helloworld.springboot.Logic.UserService;
import ro.utcluj.helloworld.springboot.Model.Content;
import ro.utcluj.helloworld.springboot.Model.CountDemo;
import ro.utcluj.helloworld.springboot.Model.User;

@RestController
public class SongRESTController {
    @PostMapping("/like/{id}")
    public String likeSubmit(@PathVariable String id, Model model, Authentication authentication){
        System.out.println("like");
        // like logic
        int songId = Integer.parseInt(id);
        Content content = ContentService.getContentById(songId);
        User user = UserService.getUserByUserName(authentication.getName());
        if(user.userDislikes(songId)){
            content.setDislikes(content.getDislikes()-1);
            user.removeDislike(songId);
        }
        if(!user.userLikes(songId)){
            content.setLikes(content.getLikes()+1);
            user.addLike(songId);
        }
        return Integer.toString(content.getLikes());
    }

    @PostMapping("/dislike/{id}")
    public String dislikeSubmit(@PathVariable String id, Model model, Authentication authentication){
        System.out.println("dislike");
        // dislike logic
        System.out.println(id+ "ala");
        int songId = Integer.parseInt(id);
        Content content = ContentService.getContentById(songId);
        User user = UserService.getUserByUserName(authentication.getName());

        if(user.userLikes(songId)){
            content.setLikes(content.getLikes()-1);
            user.removeLike(songId);
        }
        if(!user.userDislikes(songId)){
            content.setDislikes(content.getDislikes()+1);
            user.addDislike(songId);
        }
        return Integer.toString(content.getDislikes());
    }
}
