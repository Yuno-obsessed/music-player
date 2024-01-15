package sanity.nil.musicservice.infrastructure.messageBroker.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import sanity.nil.musicservice.application.interfaces.MessageBroker;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
public class MessageBrokerImpl implements MessageBroker {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void sendMessage(String topic, Object message) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Success");
            } else {
                log.error(ex.getMessage());
            }
        });
    }
}
