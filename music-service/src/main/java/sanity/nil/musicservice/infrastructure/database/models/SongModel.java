package sanity.nil.musicservice.infrastructure.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "songs")
@Entity
public class SongModel extends BaseModel {

    @Column(name = "title", length = 200)
    private String title;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "song_genre",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<GenreModel> genres;

    @Column(name = "plays")
    private Long plays;

    @Column(name = "duration")
    private Integer duration;

//    public Author
}
