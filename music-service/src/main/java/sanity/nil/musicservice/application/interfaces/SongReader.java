package sanity.nil.musicservice.application.interfaces;

import sanity.nil.musicservice.application.dto.query.SongItemQueryDTO;
import sanity.nil.musicservice.application.dto.query.SongQueryDTO;

import java.util.List;
import java.util.UUID;

public interface SongReader {

    SongQueryDTO getByID(UUID id);

    List<SongItemQueryDTO> getAllByTitle(String title);
}
