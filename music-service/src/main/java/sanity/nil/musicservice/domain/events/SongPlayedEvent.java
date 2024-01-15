package sanity.nil.musicservice.domain.events;

import java.util.UUID;

public class SongPlayedEvent implements Event {

    private BaseEvent baseEvent;
    private UUID songID;

    public SongPlayedEvent(UUID songID) {
        this.baseEvent = new BaseEvent("SongPlayed");
        this.songID = songID;
    }

    @Override
    public String getEventType() {
        return baseEvent.getEventType();
    }

    @Override
    public UUID getEventAggregateID() {
        return songID;
    }
}
