package sanity.nil.musicservice.infrastructure.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sanity.nil.musicservice.domain.consts.EventStatus;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "outbox")
@Entity
public class OutboxModel extends BaseModel {

    @Column(name = "topic")
    private String topic;

    @Column(name = "payload", columnDefinition = "text")
    private String payload;

    @Column(name = "aggregate_id")
    private UUID aggregateID;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "event_status")
    private EventStatus eventStatus;
}
