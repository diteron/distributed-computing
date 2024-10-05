package by.bsuir.jpatask.entity;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@jakarta.persistence.Entity
@Table(name = "tbl_message")
public class Message extends Entity {
    
    @ManyToOne()
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

    @Column(length = 2048, nullable = false)
    private String content;

}
