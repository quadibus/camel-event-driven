package quad.fr.eventdriven.camel.expedition.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LivrableDataBaseRepository extends JpaRepository<LivrableEntity, UUID> {

    public Optional<LivrableEntity> findByIdArticle(Long idArticle);

}
