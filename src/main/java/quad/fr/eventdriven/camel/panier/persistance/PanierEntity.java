package quad.fr.eventdriven.camel.panier.persistance;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity(name = "Panier")
@Table(name = "Panier")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PanierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    Long idCommun;

    List<Long> articles;
}
