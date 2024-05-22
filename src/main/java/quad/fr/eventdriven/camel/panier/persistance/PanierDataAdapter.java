package quad.fr.eventdriven.camel.panier.persistance;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import quad.fr.eventdriven.camel.panier.application.Article;
import quad.fr.eventdriven.camel.panier.application.Panier;
import quad.fr.eventdriven.camel.panier.application.PanierDataPort;

import java.util.Optional;

@Component
@AllArgsConstructor
public class PanierDataAdapter implements PanierDataPort {

    PanierDatabaseRepository panierDatabaseRepository;

    @Override
    public Panier savePanier(Panier panier) {
        PanierEntity panierToSave = mapPanierToPanierEntity(panier);
        PanierEntity panierSaved = panierDatabaseRepository.save(panierToSave);
        return mapPanierEntityToPanier(panierSaved);

    }

    @Override
    public Optional<Panier> findPanierById(Long id) {
        return panierDatabaseRepository.findByIdCommun(id).map(
                this::mapPanierEntityToPanier);
    }

    public Panier mapPanierEntityToPanier(PanierEntity panierEntity) {
        return Panier.builder()
                .articles(panierEntity.articles.stream().map(article -> Article.builder().id(article).build()).toList())
                .id(panierEntity.getIdCommun())
                .build();
    }

    public PanierEntity mapPanierToPanierEntity(Panier panier) {
        return PanierEntity.builder()
                .articles(panier.articles().stream().map(Article::id).toList())
                .idCommun(panier.id())
                .build();
    }


}
