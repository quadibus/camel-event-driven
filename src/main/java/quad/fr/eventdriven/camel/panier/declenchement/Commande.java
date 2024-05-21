package quad.fr.eventdriven.camel.panier.declenchement;

import lombok.Builder;

import java.util.List;

@Builder
public record Commande (Long id,List<Article> articles){

}
