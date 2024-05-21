package quad.fr.eventdriven.camel.expedition.declenchement;

import quad.fr.eventdriven.camel.expedition.application.Colis;
import quad.fr.eventdriven.camel.expedition.application.Livrable;
import org.springframework.stereotype.Component;

@Component
public class CommandeEventMapper {

    public Colis mapCommentEventToCommande(CommandeEvent commandeEvent) {
        return Colis.builder()
                .idCommande(commandeEvent.id())
                .livrables(
                        commandeEvent.articles().stream().map(
                                article -> Livrable.builder()
                                        .id(article.id())
                                        .build()
                        ).toList()
                )
                .build();
    }
}
