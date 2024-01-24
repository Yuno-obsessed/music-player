package sanity.nil.musicservice.infrastructure.database.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "albums")
@Entity
public class AlbumModel extends BaseModel{

    @Column(name = "title", length = 200)
    private String title;

    @OneToMany(mappedBy = "album")
    private Set<SongModel> songs = new HashSet<>();
}
