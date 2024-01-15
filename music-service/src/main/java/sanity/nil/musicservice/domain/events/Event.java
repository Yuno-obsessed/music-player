package sanity.nil.musicservice.domain.events;

import java.util.UUID;

public interface Event {

    String getEventType();

    UUID getEventAggregateID();
}
