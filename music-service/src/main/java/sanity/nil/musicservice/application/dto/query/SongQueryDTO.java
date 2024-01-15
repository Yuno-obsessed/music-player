package sanity.nil.musicservice.application.dto.query;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class SongQueryDTO {

    public UUID id;
    public String title;
    public List<String> genres;
    public String albumName;
    public Integer duration;
    public boolean saved;
    public boolean playable;
    public Long plays;
}
