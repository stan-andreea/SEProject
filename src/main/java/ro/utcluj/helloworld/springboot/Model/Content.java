package ro.utcluj.helloworld.springboot.Model;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;


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

    @Column(name = "tags")
    public String tags;


    public Content(String title, String artist, int id) {
        this.title = title;
        this.artist = artist;
        this.description = "";
        this.link = "";
        this.tags = "";
        this.likes = 0;
        this.dislikes = 0;
        this.views = 0;
        this.image = "";
        this.id = id;
    }

    public ArrayList<Tag> getTagsAsList() {
        ArrayList<Tag> tags = new ArrayList<>();
        for (String tag : this.tags.split(",")) {
            try {
                tags.add(Tag.valueOf(tag.trim()));
            }
            catch (Exception ignored) {
            }
        }
        return tags;
    }

    public void setTagsFromList(ArrayList<Tag> tags) {
        if(tags == null) {
            this.tags = "";
        }
        StringBuilder tagsString = new StringBuilder();
        for (Tag tag : tags) {
            tagsString.append(tag.toString()).append(", ");
        }
        this.tags = tagsString.substring(0, tagsString.length() - 2);
    }

    public void addTag(Tag tag) {
        ArrayList<Tag> tags = getTagsAsList();
        tags.add(tag);
        setTagsFromList(tags);
    }

    public void removeTag(Tag tag) {
        ArrayList<Tag> tags = getTagsAsList();
        tags.remove(tag);
        setTagsFromList(tags);
    }
}
