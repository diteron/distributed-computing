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
@Table(name = "tbl_tag")
public class Tag extends Entity {

    @Column(length = 32, nullable = false)
    private String name;
    
}
