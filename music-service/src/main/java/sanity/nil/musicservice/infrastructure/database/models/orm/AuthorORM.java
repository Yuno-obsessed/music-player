package sanity.nil.musicservice.infrastructure.database.models.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.musicservice.infrastructure.database.models.AuthorModel;
import sanity.nil.musicservice.infrastructure.database.models.GenreModel;

import java.util.List;
import java.util.UUID;

public interface AuthorORM extends JpaRepository<AuthorModel, UUID> {

    List<AuthorModel> getAllByIdIn(List<UUID> ids);
}
