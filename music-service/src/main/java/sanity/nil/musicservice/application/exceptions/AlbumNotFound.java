package sanity.nil.musicservice.application.exceptions;

import java.util.UUID;

public class AlbumNotFound extends RuntimeException{

    public AlbumNotFound(UUID id) {
        super("Album with id not found " + id.toString());
    }
}
