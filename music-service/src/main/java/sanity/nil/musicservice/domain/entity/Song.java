package sanity.nil.musicservice.domain.entity;

import sanity.nil.musicservice.domain.vo.SongID;

import java.util.List;

public class Song {

    private SongID songID;
    private String title;
    private List<Genre> genres;

    public Song(String title, List<Genre> genres) {
        this.title = title;
        this.genres = genres;
    }
}
