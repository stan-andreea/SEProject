package ro.utcluj.helloworld.springboot.Model;


import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(
        name = "content",
        schema = "musicapp"
)
public class Content {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @Column(name = "title")
    public String title;

    @Column(name = "artist")
    public String artist;

    @Column(name = "description")
    public String description;

    @Column(name = "link")
    public String link;

    @Column(name = "likes")
    public int likes;

    @Column(name = "dislikes")
    public int dislikes;

    @Column(name = "views")
    public int views;

    @Column(name = "image")
    public String image;

    public Content(String title, String artist, int id) {
        this.title = title;
        this.artist = artist;
        this.description = "";
        this.link = "";
        this.likes = 0;
        this.dislikes = 0;
        this.views = 0;
        this.image = "";
        this.id = id;
    }
}
