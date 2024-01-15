package sanity.nil.musicservice.infrastructure.database.models.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.musicservice.infrastructure.database.models.GenreModel;

import java.util.List;
import java.util.UUID;

public interface GenreORM extends JpaRepository<GenreModel, Integer> {

    List<GenreModel> getAllByIdIn(List<Integer> ids);
}
