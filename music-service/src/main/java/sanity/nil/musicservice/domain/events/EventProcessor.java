package sanity.nil.musicservice.domain.events;

import java.util.ArrayList;
import java.util.List;

public class EventProcessor {

    private static EventProcessor INSTANCE;
    private List<Event> log;
    private boolean isActive;

    public EventProcessor getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EventProcessor();
        }

        return INSTANCE;
    }

    private EventProcessor() {
        this.log = new ArrayList<>();
        this.isActive = false;
    }

    public void process(Event event) {
        isActive = true;
        event.process();
        isActive = false;
        log.add(event);
    }
}
