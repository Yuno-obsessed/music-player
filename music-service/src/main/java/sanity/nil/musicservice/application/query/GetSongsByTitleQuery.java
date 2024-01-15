package sanity.nil.musicservice.application.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.musicservice.application.dto.query.SongItemQueryDTO;
import sanity.nil.musicservice.application.interfaces.SongReader;

import java.util.List;

@RequiredArgsConstructor
public class GetSongsByTitleQuery {

    private final SongReader songReader;

    public List<SongItemQueryDTO> handle(String title) {
        return songReader.getAllByTitle(title);
    }
}
