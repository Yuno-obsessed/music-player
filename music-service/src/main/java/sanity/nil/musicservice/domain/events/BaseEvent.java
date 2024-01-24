package sanity.nil.musicservice.domain.events;

import sanity.nil.musicservice.domain.consts.EventType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class BaseEvent implements Serializable {

    private UUID eventID;

    private EventType eventType;

    private LocalDateTime eventTimestamp;

    public BaseEvent(EventType eventType) {
        eventID = UUID.randomUUID();
        this.eventType = eventType;
        eventTimestamp = LocalDateTime.now();
    }

    public UUID getEventID() {
        return eventID;
    }

    public EventType getEventType() {
        return eventType;
    }

    public LocalDateTime getEventTimestamp() {
        return eventTimestamp;
    }

}
