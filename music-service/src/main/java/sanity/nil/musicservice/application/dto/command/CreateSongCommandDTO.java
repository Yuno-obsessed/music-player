package sanity.nil.musicservice.application.dto.command;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class CreateSongCommandDTO {

    public String title;
    public UUID albumID;
    public UUID authorID;
    public List<Integer> genres;
    public Integer duration;
}
