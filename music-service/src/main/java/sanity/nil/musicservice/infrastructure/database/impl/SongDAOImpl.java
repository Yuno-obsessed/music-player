package sanity.nil.musicservice.infrastructure.database.impl;

import lombok.RequiredArgsConstructor;
import sanity.nil.musicservice.application.dto.command.CreateSongCommandDTO;
import sanity.nil.musicservice.application.dto.query.SongItemQueryDTO;
import sanity.nil.musicservice.application.dto.query.SongQueryDTO;
import sanity.nil.musicservice.application.exceptions.AlbumNotFound;
import sanity.nil.musicservice.application.exceptions.SongNotFound;
import sanity.nil.musicservice.application.interfaces.SongDAO;
import sanity.nil.musicservice.application.interfaces.SongReader;
import sanity.nil.musicservice.infrastructure.database.mapper.SongMapper;
import sanity.nil.musicservice.infrastructure.database.models.AlbumModel;
import sanity.nil.musicservice.infrastructure.database.models.AuthorModel;
import sanity.nil.musicservice.infrastructure.database.models.GenreModel;
import sanity.nil.musicservice.infrastructure.database.models.SongModel;
import sanity.nil.musicservice.infrastructure.database.models.orm.AlbumORM;
import sanity.nil.musicservice.infrastructure.database.models.orm.AuthorORM;
import sanity.nil.musicservice.infrastructure.database.models.orm.GenreORM;
import sanity.nil.musicservice.infrastructure.database.models.orm.SongORM;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class SongDAOImpl implements SongReader, SongDAO {

    private final SongORM songORM;
    private final GenreORM genreORM;
    private final AuthorORM authorORM;
    private final AlbumORM albumORM;

    @Override
    public SongQueryDTO getByID(UUID id) {
        SongModel maybeModel = songORM.findById(id).orElseThrow(
                () -> new SongNotFound(id)
        );
        return SongMapper.convertSongModelToQuery(maybeModel);
    }

    @Override
    public List<SongItemQueryDTO> getAllByTitle(String title) {
        List<SongModel> songModels = songORM.findAllByTitle(title);
        return SongMapper.convertSongModelListToSongItems(songModels);
    }

    @Override
    public UUID create(CreateSongCommandDTO dto) {
        List<GenreModel> genres = genreORM.getAllByIdIn(dto.genres);
        List<AuthorModel> authors = authorORM.getAllByIdIn(dto.authors);
        AlbumModel album = albumORM.findById(dto.albumID).orElseThrow(
                () -> new AlbumNotFound(dto.albumID)
        );
        SongModel model = SongMapper.convertCreateCommandToModel(dto, genres, album, authors);
        model.setId(UUID.randomUUID());
        SongModel createdModel = songORM.save(model);
        return createdModel.getId();
    }
}
