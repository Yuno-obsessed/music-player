package sanity.nil.musicservice.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class CreatedSongDTO {

    @JsonProperty(value = "id", required = true)
    public UUID id;
}
