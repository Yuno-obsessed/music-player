package sanity.nil.musicservice.domain.events;

import sanity.nil.musicservice.domain.consts.EventType;

import java.util.UUID;

public interface Event {

    EventType getEventType();

    UUID getEventAggregateID();

    void process();
}
