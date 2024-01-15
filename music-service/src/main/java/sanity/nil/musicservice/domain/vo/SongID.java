package sanity.nil.musicservice.domain.vo;

import java.util.UUID;

public class SongID {

    private UUID id;

    public SongID(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
