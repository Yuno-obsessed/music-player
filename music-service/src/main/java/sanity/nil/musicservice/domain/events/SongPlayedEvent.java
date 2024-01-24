package sanity.nil.musicservice.domain.events;

import sanity.nil.musicservice.domain.consts.EventType;

import java.util.UUID;

public class SongPlayedEvent implements Event {

    private BaseEvent baseEvent;
    private UUID songID;

    public SongPlayedEvent(UUID songID) {
        this.baseEvent = new BaseEvent(EventType.SONG_PLAYED);
        this.songID = songID;
    }

    @Override
    public void process() {
    }

    @Override
    public EventType getEventType() {
        return baseEvent.getEventType();
    }

    @Override
    public UUID getEventAggregateID() {
        return songID;
    }
}
