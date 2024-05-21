package quad.fr.eventdriven.camel.catalogue.persistance;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Entity(name = "Article" )
@Table(name = "Article")
@Data
@Builder
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    UUID id;
    Long idCommun;
    String name;
    Float poids;
}
