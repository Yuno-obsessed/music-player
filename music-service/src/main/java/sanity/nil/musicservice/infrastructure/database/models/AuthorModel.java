package sanity.nil.musicservice.infrastructure.database.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "genre")
@Entity
public class AuthorModel extends BaseModel {

    @Column(name = "name", length = 80)
    private String name;
}
