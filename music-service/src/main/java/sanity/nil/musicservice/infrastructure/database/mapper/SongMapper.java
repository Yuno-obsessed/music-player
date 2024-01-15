package sanity.nil.musicservice.infrastructure.database.mapper;

import com.google.common.collect.ImmutableCollection;
import sanity.nil.generated.grpc.*;
import sanity.nil.musicservice.application.dto.command.CreateSongCommandDTO;
import sanity.nil.musicservice.application.dto.query.SongItemQueryDTO;
import sanity.nil.musicservice.application.dto.query.SongQueryDTO;
import sanity.nil.musicservice.infrastructure.database.models.GenreModel;
import sanity.nil.musicservice.infrastructure.database.models.SongModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SongMapper {

    public static SongQuery convertSongQueryToPresentation(SongQueryDTO dto) {
        List<Genre> genres = new ArrayList<>();
        for (String genre : dto.genres) {
            genres.add(Genre.newBuilder().setName(genre).build());
        }
        return SongQuery.newBuilder()
                .setId(SongID.newBuilder().setId(dto.id.toString()))
                .setTitle(dto.title)
                .setAlbumName(dto.albumName)
                .setPlayCount(dto.plays)
                .addAllGenres(genres)
                .setSaved(dto.saved)
                .setDuration(dto.duration)
                .setPlayable(dto.playable)
                .build();
    }

    public static SongItemQuery convertSongItemQueryToPresentation(SongItemQueryDTO dto) {
        return SongItemQuery.newBuilder()
                .setId(SongID.newBuilder().setId(dto.id.toString()))
                .setTitle(dto.title)
                .setDuration(dto.duration)
                .build();
    }

    public static List<SongItemQuery> convertSongItemQueryListToPresentation(List<SongItemQueryDTO> dtos) {
        return dtos.stream()
                .map(SongMapper::convertSongItemQueryToPresentation)
                .toList();
    }

    public static SongQueryDTO convertSongModelToQuery(SongModel model) {
        return new SongQueryDTO(model.getId(), model.getTitle(),
                model.getGenres().stream().map(GenreModel::getName).toList(), "album",
                model.getDuration(), true, true, model.getPlays());
    }

    public static SongModel convertCreateCommandToModel(CreateSongCommandDTO dto, List<GenreModel> genres) {
        return new SongModel(dto.title, genres, 0L, dto.duration);
    }

    public static CreateSongCommandDTO convertPresentationToCreateCommand(CreateSong createSong) {
        List<Integer> genres = createSong.getGenresList().stream().map(GenreID::getId).toList();
        return new CreateSongCommandDTO(createSong.getTitle(), UUID.fromString(createSong.getAlbumId()),
                UUID.fromString(createSong.getAuthorId()), genres, createSong.getDuration());
    }

    public static SongItemQueryDTO convertSongModelToSongItem(SongModel model) {
        return new SongItemQueryDTO(model.getId(), model.getTitle(), model.getDuration());
    }

    public static List<SongItemQueryDTO> convertSongModelListToSongItems(List<SongModel> models) {
        return models.stream()
                .map(SongMapper::convertSongModelToSongItem)
                .toList();
    }

}
