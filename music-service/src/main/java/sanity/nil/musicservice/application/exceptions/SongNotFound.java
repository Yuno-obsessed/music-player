package sanity.nil.musicservice.application.exceptions;

import java.util.UUID;

public class SongNotFound extends RuntimeException{

    public SongNotFound(UUID id) {
        super("Song with id not found " + id.toString());
    }
}
