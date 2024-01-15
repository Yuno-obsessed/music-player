package sanity.nil.musicservice.application.interfaces;

import sanity.nil.musicservice.application.dto.command.CreateSongCommandDTO;

import java.util.UUID;

public interface SongDAO {

    UUID create(CreateSongCommandDTO dto);
}
