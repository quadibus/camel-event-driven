package quad.fr.eventdriven.camel.catalogue.persistance;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "Article" )
@Table(name = "Article")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    UUID id;

    Long idArticle;
    Float prix;
}
