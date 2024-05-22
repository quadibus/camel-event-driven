package quad.fr.eventdriven.camel.catalogue.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ArticleRepository extends JpaRepository<ArticleEntity, UUID> {

    public Optional<ArticleEntity> findByIdArticle(Long id);

}
