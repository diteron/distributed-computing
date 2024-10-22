package by.bsuir.publisherservice.client.discussionservice.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.stereotype.Service;

import by.bsuir.publisherservice.client.discussionservice.kafka.exception.DiscussionServiceResponseException;
import by.bsuir.publisherservice.client.discussionservice.kafka.exception.DiscussionServiceTimeoutException;
import by.bsuir.publisherservice.client.discussionservice.kafka.message.RequestTopicMessage;
import by.bsuir.publisherservice.client.discussionservice.kafka.message.ResponseTopicMessage;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class KafkaService {

    @Value("${topic.news-message.request}")
    private String REQUEST_TOPIC;

    private final ReplyingKafkaTemplate<String, RequestTopicMessage, ResponseTopicMessage> REPLYING_KAFKA_TEMPLATE;

    public ResponseTopicMessage sendAndRecieve(RequestTopicMessage topicMessage) {
        String recordKey = topicMessage.requestMessage().newsId().toString();    // Key to determine a partition by Kafka
        ProducerRecord<String, RequestTopicMessage> record = 
                new ProducerRecord<>(REQUEST_TOPIC, recordKey, topicMessage);
        RequestReplyFuture<String, RequestTopicMessage, ResponseTopicMessage> replyFuture = 
                REPLYING_KAFKA_TEMPLATE.sendAndReceive(record);
                
        try {
            ConsumerRecord<String, ResponseTopicMessage> consumerRecord = replyFuture.get(1, TimeUnit.SECONDS);
            return consumerRecord.value();
        }
        catch (TimeoutException e) {
            throw new DiscussionServiceTimeoutException("Timeout while trying to get response");
        }
        catch (ExecutionException | InterruptedException e) {
            throw new DiscussionServiceResponseException("Unexpected error occured while trying to get response");
        }
    }

}
