package by.bsuir.jpatask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@jakarta.persistence.Entity
@Table(name = "tbl_news")
public class News extends Entity {

    @ManyToOne()
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Column(length = 64, nullable = false, unique = true)
    private String title;
    
    @Column(length = 2048, nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime timeCreated;
    
    @Column(nullable = false)
    private LocalDateTime timeModified;

}
