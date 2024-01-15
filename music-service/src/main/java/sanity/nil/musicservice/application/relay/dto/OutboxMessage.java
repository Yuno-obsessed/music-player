package sanity.nil.musicservice.application.relay.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class OutboxMessage {

    public UUID id;

    public String eventType;

    public UUID eventAggregateID;

    public String topic;

    public String payload;

}
