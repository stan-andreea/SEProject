package ro.utcluj.helloworld.springboot.Model;


import lombok.*;
import ro.utcluj.helloworld.springboot.Logic.ContentRepository;
import ro.utcluj.helloworld.springboot.Logic.ContentService;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(
        name = "user",
        schema = "musicapp"
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", nullable = false, unique = true, length = 45)
    private String username;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    private boolean enabled;


    //TODO private ArrayList<Integer> likes;


    @OneToMany
    @Column(name = "likes")
    private ArrayList<Content> likes ;

    @Column(name = "dislikes")
    @OneToMany
    private List<Content> dislikes = new ArrayList<>();



    public boolean userLikes(int id) {

        ContentService contentService = new ContentService();
        Content likedContent = contentService.getContentById(id);
        this.likes.add(likedContent);
        return true;
    }
    public boolean userDislikes(int id) {
        ContentService contentService = new ContentService();
        Content dislikedContent = contentService.getContentById(id);
        this.dislikes.add(dislikedContent);
        return true;
    }
    public void removeDislike(Integer songId) {
        ContentService contentService = new ContentService();
        Content dislikedContent = contentService.getContentById(songId);
        dislikes.remove(dislikedContent);
    }
    public void removeLike(Integer songId) {
        ContentService contentService = new ContentService();
        Content likedContent = contentService.getContentById(songId);
        likes.remove(likedContent);
    }
    public void addLike(Integer songId) {
        ContentService contentService = new ContentService();
        Content content = contentService.getContentById(songId);
        if(!dislikes.contains(content))
        {
            int currentLikes = content.getLikes();
            content.setLikes(currentLikes+1);
        }

    }
    public void addDislike(Integer songId) {
        ContentService contentService = new ContentService();
        Content content = contentService.getContentById(songId);
        if(!likes.contains(content)){
            int currentDislikes = content.getDislikes();
            content.setLikes(currentDislikes+1);
        }

    }

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
}
