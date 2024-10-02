package by.bsuir.resttask.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;

import by.bsuir.resttask.dto.request.AuthorRequestTo;
import by.bsuir.resttask.dto.request.MessageRequestTo;
import by.bsuir.resttask.dto.request.NewsRequestTo;
import by.bsuir.resttask.dto.response.MessageResponseTo;
import io.restassured.response.Response;

public class MessageControllerTest extends RestControllerTest<MessageRequestTo, MessageResponseTo> {
    private static Long authorId;
    private static Long newsId;
    private static boolean isForeignKeyEntitiesCreated = false;

    @AfterAll
    public static void deleteForeighnKeyEntities() {
        if (isForeignKeyEntitiesCreated) {
            deleteForeignKeyEntity(authorId, "/authors");
            deleteForeignKeyEntity(newsId, "/news");
        }
    }

    @Override
    protected MessageRequestTo getRandomRequestTo() {
        createForeignKeyEntitiesIfNeeded();
        return new MessageRequestTo(null, newsId,
                                    "content" + RANDOM_NUMBER_GENERATOR.nextInt());
    }

    @Override
    protected MessageRequestTo getUpdateRequestTo(MessageRequestTo originalRequest, Long updateEntityId) {
        return new MessageRequestTo(updateEntityId,
                                    originalRequest.newsId(),
                                    "content" + RANDOM_NUMBER_GENERATOR.nextInt());
    }

    @Override
    protected String getMappingPath() {
        return "/messages";
    }

    @Override
    protected void assertRequestAndResponceEquals(MessageRequestTo request, MessageResponseTo response) {
        assertEquals(request.newsId(), response.newsId());
        assertEquals(request.content(), response.content());
    }

    private void createForeignKeyEntitiesIfNeeded() {
        if (!isForeignKeyEntitiesCreated) {
            AuthorRequestTo author = new AuthorRequestTo(null, "login", "password",
                                                         "firstame", "lastname");
            Response authResponse = createForeignKeyEntity(author, "/authors");                                                     
            authorId = getResponseId(authResponse);
            
            NewsRequestTo news = new NewsRequestTo(null, authorId, "Title", "Content");
            Response newsResponse = createForeignKeyEntity(news, "/news");
            newsId = getResponseId(newsResponse);

            isForeignKeyEntitiesCreated = true;
        }
    }

}
