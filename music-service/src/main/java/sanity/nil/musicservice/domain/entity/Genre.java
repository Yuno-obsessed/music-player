package sanity.nil.musicservice.domain.entity;

import java.util.UUID;

public class Genre {
    private UUID id;
    private String name;

    public Genre(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
