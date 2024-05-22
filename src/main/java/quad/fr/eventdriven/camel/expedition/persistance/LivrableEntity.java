package quad.fr.eventdriven.camel.expedition.persistance;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "Livrable")
@Table(name="Livrable")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivrableEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    UUID id;

    Long idArticle;
    Float poids;
}
