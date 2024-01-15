package sanity.nil.musicservice.infrastructure.database.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sanity.nil.musicservice.application.relay.dto.OutboxMessage;
import sanity.nil.musicservice.domain.events.Event;
import sanity.nil.musicservice.infrastructure.database.models.OutboxModel;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OutboxMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public List<OutboxMessage> convertModelsToDTOs(List<OutboxModel> models) {
        return models.stream()
                .map(this::convertModelToDTO)
                .collect(Collectors.toList());
    }

    private OutboxMessage convertModelToDTO(OutboxModel model) {
        OutboxMessage outboxMessage = new OutboxMessage();
        outboxMessage.id = model.getId();
        outboxMessage.topic = model.getTopic();
        outboxMessage.payload = model.getPayload();
        outboxMessage.eventAggregateID = model.getAggregateID();
        return outboxMessage;
    }

    public OutboxModel convertEventToModel(Event event) {
        OutboxModel outboxModel = new OutboxModel();
        String payload = null;
        try {
            payload = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
//        outboxModel.setPayload(payload);
//        outboxModel.setId(event.getBaseEvent().getEventID());
//        outboxModel.setEventStatus(EventStatus.STATUS_AWAITING.getCode());
//        outboxModel.setAggregateID(event.uniqueAggregateID());
//        if (event instanceof OrderCreatedEvent) {
//            outboxModel.setExchange(rabbitConfig.getOrderFanoutExchange());
//            outboxModel.setRoute(rabbitConfig.getOrderCreatedRK());
//        }
//        if (event instanceof OrderProductReservedEvent) {
//            outboxModel.setExchange(rabbitConfig.getOrderTopicExchange());
//            outboxModel.setRoute(rabbitConfig.getOrderAddedProductRK());
//        }

        return outboxModel;
    }

    public List<OutboxModel> convertEventsToModels(List<Event> events) {
        return events.stream()
                .map(this::convertEventToModel)
                .collect(Collectors.toList());
    }
}
