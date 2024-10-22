package by.bsuir.publisherservice.client.discussionservice.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

import by.bsuir.publisherservice.client.discussionservice.kafka.message.RequestTopicMessage;
import by.bsuir.publisherservice.client.discussionservice.kafka.message.ResponseTopicMessage;

@Configuration
public class KafkaConfig {

    @Value("${topic.news-message.request}")
    private String REQUEST_TOPIC;
  
    @Value("${topic.news-message.response}")
    private String RESPONSE_TOPIC;

    @Bean
    public ReplyingKafkaTemplate<String, RequestTopicMessage, ResponseTopicMessage> replyingKafkaTemplate(
            ProducerFactory<String, RequestTopicMessage> producerFactory,
            ConcurrentMessageListenerContainer<String, ResponseTopicMessage> container
    ) {
        return new ReplyingKafkaTemplate<>(producerFactory, container);
    }

    @Bean
    public ConcurrentMessageListenerContainer<String, ResponseTopicMessage> replyContainer(
            ConsumerFactory<String, ResponseTopicMessage> consumerFactory
    ) {
        ContainerProperties containerProperties = new ContainerProperties(RESPONSE_TOPIC);
        return new ConcurrentMessageListenerContainer<>(consumerFactory, containerProperties);
    }

    @Bean
    public NewTopic responseTopic() {
        return TopicBuilder.name(REQUEST_TOPIC)
                           .partitions(5)
                           .compact()
                           .build();
    }

}
