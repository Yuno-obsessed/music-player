package sanity.nil.statisticsservice.infrastructure.database.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "plays_statistics")
@Entity
public class PlaysStatisticsModel {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "song_id")
    private UUID songID;

    @Column(name = "user_id")
    private UUID userID;

    @Column(name = "played_at")
    private LocalDateTime playedAt;

}
