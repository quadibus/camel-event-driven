package quad.fr.eventdriven.camel.panier.notification;

import lombok.Builder;
import quad.fr.eventdriven.camel.panier.application.Article;

import java.util.List;

@Builder
public record PanierModifiedEvent (Long id, List<Article> articles){
}
