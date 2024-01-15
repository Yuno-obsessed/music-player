package sanity.nil.musicservice.application.service;

import lombok.AllArgsConstructor;
import sanity.nil.musicservice.application.query.GetSongByIDQuery;
import sanity.nil.musicservice.application.query.GetSongsByTitleQuery;

@AllArgsConstructor
public class SongQueryService {

    public GetSongByIDQuery getSongByIDQuery;
    public GetSongsByTitleQuery getSongsByTitleQuery;
}
