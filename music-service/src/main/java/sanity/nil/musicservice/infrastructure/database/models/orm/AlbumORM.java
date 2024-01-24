package sanity.nil.musicservice.infrastructure.database.models.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.musicservice.infrastructure.database.models.AlbumModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AlbumORM extends JpaRepository<AlbumModel, UUID> {

}
