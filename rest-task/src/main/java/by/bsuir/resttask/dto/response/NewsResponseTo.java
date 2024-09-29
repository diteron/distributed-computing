package by.bsuir.resttask.dto.response;

import java.time.LocalDateTime;

public record NewsResponseTo(
    Long id,
    Long authorId,
    String title,
    String content,
    LocalDateTime timeCreated,
    LocalDateTime timeModified
) {}
