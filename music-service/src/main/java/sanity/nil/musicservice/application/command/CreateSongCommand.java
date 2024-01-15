package sanity.nil.musicservice.application.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.musicservice.application.dto.command.CreateSongCommandDTO;
import sanity.nil.musicservice.application.dto.response.CreatedSongDTO;
import sanity.nil.musicservice.application.interfaces.SongDAO;

import java.util.UUID;

@RequiredArgsConstructor
public class CreateSongCommand {

    private final SongDAO songDAO;

    public CreatedSongDTO handle(CreateSongCommandDTO dto) {
        UUID id = songDAO.create(dto);
        return new CreatedSongDTO(id);
    }
}
