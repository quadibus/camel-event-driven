package quad.fr.eventdriven.camel.panier.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface PanierDatabaseRepository extends JpaRepository<PanierEntity, UUID> {

    Optional<PanierEntity> findByIdCommun(Long id);
}
