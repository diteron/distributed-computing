package by.bsuir.resttask.dto.response;

public record AuthorResponseTo(
    Long id,
    String login,
    String firstName,
    String lastName 
) {}
