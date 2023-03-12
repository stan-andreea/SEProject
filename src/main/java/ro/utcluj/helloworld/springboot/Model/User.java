package ro.utcluj.helloworld.springboot.Model;


import lombok.*;
import org.aspectj.weaver.ArrayReferenceType;
import ro.utcluj.helloworld.springboot.Logic.ContentRepository;
import ro.utcluj.helloworld.springboot.Logic.ContentService;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Column(name = "likes", nullable = false, length = 1024)
    private String likes = new String();

    @Column(name = "dislikes", nullable = false, length = 1024)
    private String dislikes = new String();

    public ArrayList<Integer> getLikesAsList() {
        if(Objects.equals(likes, ""))
            return new ArrayList<>();
        return (ArrayList<Integer>) Stream.of(likes.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public ArrayList<Integer> getDislikesAsList() {
        if(Objects.equals(dislikes, ""))
                return new ArrayList<>();
        return (ArrayList<Integer>) Stream.of(dislikes.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public void setLikesAsList(ArrayList<Integer> likes) {
        this.likes = likes.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    public void setDislikesAsList(ArrayList<Integer> dislikes) {
        this.dislikes = dislikes.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    public boolean userLikes(int id) {
        System.out.println(getLikesAsList().contains(id));
        return getLikesAsList().contains(id);
    }

    public boolean userDislikes(int id) {
        return getDislikesAsList().contains(id);
    }

    public void removeDislike(Integer songId) {
        ArrayList<Integer> dislikes = new ArrayList<>(getDislikesAsList());
        dislikes.remove(songId);
        setDislikesAsList(dislikes);
    }

    public void removeLike(Integer songId) {
        ArrayList<Integer> likes = new ArrayList<>(getLikesAsList());
        likes.remove(songId);
        setLikesAsList(likes);
    }

    public void addLike(Integer songId) {
        ArrayList<Integer> likes = new ArrayList<>(getLikesAsList());
        likes.add(songId);
        setLikesAsList(likes);
    }

    public void addDislike(Integer songId) {
        ArrayList<Integer> dislikes = new ArrayList<>(getDislikesAsList());
        dislikes.add(songId);
        setDislikesAsList(dislikes);
    }

    public User(String username) {
        this.username = username;
    }
}
