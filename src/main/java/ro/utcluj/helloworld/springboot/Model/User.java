package ro.utcluj.helloworld.springboot.Model;


import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
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

    @Column(name = "username")
    private String username;

    //TODO private ArrayList<Integer> likes;

    //TODO private ArrayList<Integer> dislikes;

    public boolean userLikes(int parseInt) {
        //TODO
        return false;
    }
    public boolean userDislikes(int parseInt) {
        //TODO
        return false;
    }
    public void removeDislike(Integer songId) {
        //TODO
    }
    public void removeLike(Integer songId) {
        //TODO
    }
    public void addLike(Integer songId) {
        //todo
    }
    public void addDislike(Integer songId) {
        //todo
    }
}
