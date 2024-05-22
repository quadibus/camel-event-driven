package quad.fr.eventdriven.camel.expedition.declenchement;

import quad.fr.eventdriven.camel.expedition.application.Colis;
import quad.fr.eventdriven.camel.expedition.application.Livrable;
import org.springframework.stereotype.Component;

@Component
public class PanierModifieEventMapper {

    public Colis mapCommentEventToCommande(PanierModifieEvent panierModifieEvent) {
        return Colis.builder()
                .idCommande(panierModifieEvent.id())
                .livrables(
                        panierModifieEvent.articles().stream().map(
                                article -> Livrable.builder()
                                        .id(article.id())
                                        .build()
                        ).toList()
                )
                .build();
    }
}
