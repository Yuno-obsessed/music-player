package sanity.nil.musicservice.infrastructure.database.impl;

import lombok.RequiredArgsConstructor;
import sanity.nil.musicservice.application.relay.dto.OutboxMessage;
import sanity.nil.musicservice.application.relay.interfaces.OutboxDAO;
import sanity.nil.musicservice.domain.events.Event;
import sanity.nil.musicservice.infrastructure.database.models.orm.OutboxORM;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class OutboxDAOImpl implements OutboxDAO {

    private final OutboxORM outboxORM;

    @Override
    public List<OutboxMessage> getAllNonProcessedMessages() {
        return null;
    }

    @Override
    public void updateMessage(List<UUID> ids) {

    }

    @Override
    public void addEvents(List<Event> events) {

    }
}
