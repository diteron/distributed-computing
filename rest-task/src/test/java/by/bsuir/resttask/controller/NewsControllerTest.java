package by.bsuir.resttask.controller;

import org.junit.jupiter.api.AfterAll;

import by.bsuir.resttask.dto.request.AuthorRequestTo;
import by.bsuir.resttask.dto.request.NewsRequestTo;
import by.bsuir.resttask.dto.response.NewsResponseTo;
import io.restassured.response.Response;

public class NewsControllerTest extends RestControllerTest<NewsRequestTo, NewsResponseTo> {
    private static Long authorId;
    private static boolean isForeignKeyEntitiesCreated = false;

    @AfterAll
    public static void deleteForeighnKeyEntities() {
        if (isForeignKeyEntitiesCreated) {
            deleteForeignKeyEntity(authorId, "/authors");
        }
    }

    @Override
    protected NewsRequestTo getRequestTo() {
        createForeignKeyEntitiesIfNeeded();
        return new NewsRequestTo(null, authorId,
                                 "title"   + RANDOM_NUMBER_GENERATOR.nextInt(),
                                 "content" + RANDOM_NUMBER_GENERATOR.nextInt());
    }

    @Override
    protected NewsRequestTo getUpdateRequestTo(NewsRequestTo originalRequest, Long updateEntityId) {
        return new NewsRequestTo(updateEntityId,
                                 originalRequest.authorId(),
                                 "title"   + RANDOM_NUMBER_GENERATOR.nextInt(),
                                 "content" + RANDOM_NUMBER_GENERATOR.nextInt());
    }

    @Override
    protected String getRequestsMappingPath() {
        return "/news";
    }

    private void createForeignKeyEntitiesIfNeeded() {
        if (!isForeignKeyEntitiesCreated) {
            AuthorRequestTo author = new AuthorRequestTo(null, "login", "password",
                                                         "firstame", "lastname");
            Response authResponse = createForeignKeyEntity(author, "/authors");                                                     
            authorId = getResponseId(authResponse);

            isForeignKeyEntitiesCreated = true;
        }
    }
}
