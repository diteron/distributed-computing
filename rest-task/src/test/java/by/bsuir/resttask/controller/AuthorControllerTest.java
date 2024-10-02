package by.bsuir.resttask.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import by.bsuir.resttask.dto.request.AuthorRequestTo;
import by.bsuir.resttask.dto.response.AuthorResponseTo;

public class AuthorControllerTest extends RestControllerTest<AuthorRequestTo, AuthorResponseTo> {

    @Override
    protected AuthorRequestTo getRandomRequestTo() {
        return new AuthorRequestTo(null,
                                   "login"     + RANDOM_NUMBER_GENERATOR.nextInt(),
                                   "password"  + RANDOM_NUMBER_GENERATOR.nextInt(),
                                   "firstname" + RANDOM_NUMBER_GENERATOR.nextInt(),
                                   "lastname"  + RANDOM_NUMBER_GENERATOR.nextInt());
    }

    @Override
    protected AuthorRequestTo getUpdateRequestTo(AuthorRequestTo originalRequest, Long updateEntityId) {
        return new AuthorRequestTo(
                    updateEntityId,
                    originalRequest.login()     + RANDOM_NUMBER_GENERATOR.nextInt(),
                    originalRequest.password()  + RANDOM_NUMBER_GENERATOR.nextInt(),
                    originalRequest.firstname() + RANDOM_NUMBER_GENERATOR.nextInt(),
                    originalRequest.lastname()  + RANDOM_NUMBER_GENERATOR.nextInt());
    }

    @Override
    protected String getMappingPath() {
        return "/authors";
    }

    @Override
    protected void assertRequestAndResponceEquals(AuthorRequestTo request, AuthorResponseTo response) {
        assertEquals(request.login(), response.login());
        assertEquals(request.firstname(), response.firstname());
        assertEquals(request.lastname(), response.lastname());
    }
}
