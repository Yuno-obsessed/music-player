package sanity.nil.musicservice.application.interfaces;

public interface MessageBroker {

    void sendMessage(String topic, Object message);
}
