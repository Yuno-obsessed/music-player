package sanity.nil.musicservice.application.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.musicservice.application.dto.query.SongQueryDTO;
import sanity.nil.musicservice.application.interfaces.SongReader;
import sanity.nil.musicservice.application.relay.interfaces.OutboxDAO;
import sanity.nil.musicservice.domain.events.Event;
import sanity.nil.musicservice.domain.events.SongPlayedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class GetSongByIDQuery {

    private final SongReader songReader;
    private final OutboxDAO outboxDAO;

    public SongQueryDTO handle(UUID id) {
        SongQueryDTO songQueryDTO = songReader.getByID(id);
        List<Event> events = new ArrayList<>();
        events.add(new SongPlayedEvent(songQueryDTO.id));
        outboxDAO.addEvents(events);
        return songQueryDTO;
    }
}
