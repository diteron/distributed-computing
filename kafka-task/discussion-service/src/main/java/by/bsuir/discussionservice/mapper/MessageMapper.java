package by.bsuir.discussionservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import by.bsuir.discussionservice.dto.request.MessageRequestTo;
import by.bsuir.discussionservice.dto.response.MessageResponseTo;
import by.bsuir.discussionservice.entity.Message;
import by.bsuir.discussionservice.entity.MessageState;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    
    @Mapping(target = "key", expression = "java(new Message.Key(request.country(), request.id(),"
                                          + "request.newsId(), request.state()))")
    Message toEntity(MessageRequestTo request);

    @Mapping(target = "key", expression = "java(new Message.Key(request.country(), request.id(),"
                                          + "request.newsId(), state))")
    Message toEntity(MessageRequestTo request, MessageState state);

    @Mapping(target = "id", source = "entity.key.id")
    @Mapping(target = "newsId", source = "entity.key.newsId")
    @Mapping(target = "state", source = "entity.key.state")
    MessageResponseTo toResponseTo(Message entity);
    
    @Mapping(target = "key.id", ignore = true)
    @Mapping(target = "key.newsId", source = "newsId")
    @Mapping(target = "key.country", source = "country")
    @Mapping(target = "key.state", source = "state")
    Message updateEntity(@MappingTarget Message entityToUpdate, MessageRequestTo updateRequest);
    
}
