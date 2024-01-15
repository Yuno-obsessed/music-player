package sanity.nil.musicservice.application.dto.query;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class SongItemQueryDTO {

    public UUID id;
    public String title;
    public Integer duration;
}
