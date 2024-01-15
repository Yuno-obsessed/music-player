package sanity.nil.statisticsservice.infrastructure.database.models.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.statisticsservice.infrastructure.database.models.SearchesStatisticsModel;

import java.util.UUID;

public interface SearchesStatisticsORM extends JpaRepository<SearchesStatisticsModel, UUID> {
}
