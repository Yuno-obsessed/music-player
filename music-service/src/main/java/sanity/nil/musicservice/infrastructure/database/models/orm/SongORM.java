package sanity.nil.musicservice.infrastructure.database.models.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sanity.nil.musicservice.infrastructure.database.models.SongModel;

import java.util.List;
import java.util.UUID;

public interface SongORM extends JpaRepository<SongModel, UUID> {

    @Query(
            "SELECT s FROM SongModel s " +
                    "WHERE :#{#title} IS NULL OR " +
                    "LOWER(s.title) LIKE LOWER(CONCAT('%', :#{#title}, '%'))" +
                    " ORDER BY s.title"
    )
    List<SongModel> findAllByTitle(@Param("title") String title);
}
