package quad.fr.eventdriven.camel.expedition.persistance;

import quad.fr.eventdriven.camel.expedition.application.Livrable;
import quad.fr.eventdriven.camel.expedition.application.LivrableDataPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class LivrableDataAdapter implements LivrableDataPort {

    LivrableDataBaseRepository livrableDataBaseRepository;

    @Override
    public Optional<Livrable> getByProductId(Long id) {
        return livrableDataBaseRepository.findByIdArticle(id).map(
                livrableEntity ->
                        Livrable.builder()
                                .poids(livrableEntity.getPoids())
                                .name(livrableEntity.getName())
                                .id(livrableEntity.getIdArticle())
                                .build()
        );
    }
}
