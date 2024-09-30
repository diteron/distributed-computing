package by.bsuir.resttask.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import by.bsuir.resttask.dto.request.NewsRequestTo;
import by.bsuir.resttask.dto.response.NewsResponseTo;
import by.bsuir.resttask.entity.News;
import by.bsuir.resttask.exception.EntityNotFoundException;
import by.bsuir.resttask.exception.EntityNotSavedException;
import by.bsuir.resttask.mapper.NewsMapper;
import by.bsuir.resttask.repository.Repository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsMapper NEWS_MAPPER;
    private final Repository<News, Long> NEWS_REPOSITORY;

    private final AuthorService AUTHOR_SERVICE;

    public List<NewsResponseTo> getAll() {
        return NEWS_REPOSITORY.findAll()
                                .stream()
                                .map(NEWS_MAPPER::toResponseTo)
                                .toList();
    };

    public NewsResponseTo getById(Long id) {
        return NEWS_REPOSITORY.findById(id)
                                .map(NEWS_MAPPER::toResponseTo)
                                .orElseThrow(() -> 
                                    new EntityNotFoundException("News", id));
    };

    public NewsResponseTo save(NewsRequestTo news) {
        checkAuthorExistence(news.authorId());
        return Optional.of(news)
                        .map(NEWS_MAPPER::toEntity)
                        .map(NEWS_REPOSITORY::save)
                        .map(NEWS_MAPPER::toResponseTo)
                        .orElseThrow(() -> 
                            new EntityNotSavedException("News", news.id()));
    };

    public NewsResponseTo update(NewsRequestTo news) {
        checkAuthorExistence(news.authorId());
        return NEWS_REPOSITORY.findById(news.id())
                                .map(entity -> NEWS_MAPPER.updateEntity(entity, news))
                                .map(NEWS_REPOSITORY::save)
                                .map(NEWS_MAPPER::toResponseTo)
                                .orElseThrow(() -> 
                                    new EntityNotFoundException("News", news.id()));
    };

    public void delete(Long id) {
        NEWS_REPOSITORY.findById(id)
                       .ifPresentOrElse(NEWS_REPOSITORY::delete,
                                        () -> { 
                                            throw new EntityNotFoundException("Message", id); 
                                        });  
    };

    private void checkAuthorExistence(Long authorId) {
        AUTHOR_SERVICE.getById(authorId);
    }
}
