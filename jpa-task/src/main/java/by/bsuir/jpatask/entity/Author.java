package by.bsuir.jpatask.entity;

import jakarta.persistence.Column;
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
@Table(name = "tbl_author")
public class Author extends Entity {
    
    @Column(length = 64, nullable = false, unique = true)
    private String login;

    @Column(length = 128, nullable = false)
    private String password;

    @Column(length = 64, nullable = false)
    private String firstname;

    @Column(length = 64, nullable = false)
    private String lastname;

}
