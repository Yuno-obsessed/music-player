package sanity.nil.musicservice.application.relay.interfaces;

import sanity.nil.musicservice.application.relay.dto.OutboxMessage;
import sanity.nil.musicservice.domain.events.Event;

import java.util.List;
import java.util.UUID;

public interface OutboxDAO {

    List<OutboxMessage> getAllNonProcessedMessages();

    void updateMessage(List<UUID> ids);

    void addEvents(List<Event> events);
}
