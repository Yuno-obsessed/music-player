package sanity.nil.musicservice.infrastructure.database.models.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.musicservice.infrastructure.database.models.OutboxModel;

import java.util.UUID;

public interface OutboxORM extends JpaRepository<OutboxModel, UUID> {
}
