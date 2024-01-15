package sanity.nil.musicservice.presentation.config.di;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import sanity.nil.musicservice.application.command.CreateSongCommand;
import sanity.nil.musicservice.application.interfaces.MessageBroker;
import sanity.nil.musicservice.application.interfaces.SongDAO;
import sanity.nil.musicservice.application.interfaces.SongReader;
import sanity.nil.musicservice.application.query.GetSongByIDQuery;
import sanity.nil.musicservice.application.query.GetSongsByTitleQuery;
import sanity.nil.musicservice.application.relay.interfaces.OutboxDAO;
import sanity.nil.musicservice.application.service.SongCommandService;
import sanity.nil.musicservice.application.service.SongQueryService;
import sanity.nil.musicservice.infrastructure.database.impl.OutboxDAOImpl;
import sanity.nil.musicservice.infrastructure.database.impl.SongDAOImpl;
import sanity.nil.musicservice.infrastructure.database.models.orm.GenreORM;
import sanity.nil.musicservice.infrastructure.database.models.orm.OutboxORM;
import sanity.nil.musicservice.infrastructure.database.models.orm.SongORM;
import sanity.nil.musicservice.infrastructure.messageBroker.adapter.MessageBrokerImpl;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories("sanity.nil.musicservice.infrastructure.database.models.orm")
public class BeanCreator {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean("myObjectMapper")
    public ObjectMapper myObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }

    @Bean
    public SongReader songReader(SongORM songORM, GenreORM genreORM) {
        return new SongDAOImpl(songORM, genreORM);
    }

    @Bean
    public SongDAO songDAO(SongORM songORM, GenreORM genreORM) {
        return new SongDAOImpl(songORM, genreORM);
    }

    @Bean
    public OutboxDAO outboxDAO(OutboxORM outboxORM) {
        return new OutboxDAOImpl(outboxORM);
    }

    @Bean
    public SongQueryService songQueryService(SongReader songReader, OutboxDAO outboxDAO) {
        return new SongQueryService(
                new GetSongByIDQuery(songReader, outboxDAO),
                new GetSongsByTitleQuery(songReader)
        );
    }

    @Bean
    public SongCommandService songCommandService(SongDAO songDAO) {
        return new SongCommandService(
                new CreateSongCommand(songDAO)
        );
    }

    @Bean
    public MessageBroker messageBroker(KafkaTemplate<String, Object> kafkaTemplate) {
        return new MessageBrokerImpl(kafkaTemplate);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("topic1")
                .partitions(10)
                .replicas(1)
                .build();
    }

//    @Bean
//    public ApplicationRunner runner(KafkaTemplate<String, String> template) {
//        return args -> {
//            template.send("topic1", )
//        }
//    }

}
