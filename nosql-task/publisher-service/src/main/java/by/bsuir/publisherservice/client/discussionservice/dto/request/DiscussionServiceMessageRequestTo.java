package by.bsuir.publisherservice.client.discussionservice.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DiscussionServiceMessageRequestTo(
    Long id,
    Long newsId,
    
    @NotNull
    @Size(min = 2, max = 2048, message = "Content must be between 8 and 128 characters")
    String content,

    @NotNull
    String country
) {

}
