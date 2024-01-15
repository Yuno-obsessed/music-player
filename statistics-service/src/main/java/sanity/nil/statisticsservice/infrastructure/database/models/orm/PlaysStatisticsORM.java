package sanity.nil.statisticsservice.infrastructure.database.models.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.statisticsservice.infrastructure.database.models.PlaysStatisticsModel;

import java.util.UUID;

public interface PlaysStatisticsORM extends JpaRepository<PlaysStatisticsModel, UUID> {
}
