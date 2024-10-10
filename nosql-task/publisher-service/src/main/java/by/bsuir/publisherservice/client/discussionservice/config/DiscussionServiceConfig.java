package by.bsuir.publisherservice.client.discussionservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.bsuir.publisherservice.client.discussionservice.DiscussionServiceClient;

@Configuration
public class DiscussionServiceConfig {

    @Bean
    RestClient restClient(ObjectMapper objectMapper) {
        return RestClient.builder()
                         .baseUrl("http://localhost:24130/api/v1.0")
                         .build();
    }

    @Bean
    DiscussionServiceClient discussionServiceClient(RestClient restClient) {
    HttpServiceProxyFactory httpServiceProxyFactory =
        HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
                               .build();

        return httpServiceProxyFactory.createClient(DiscussionServiceClient.class);
    }

}
