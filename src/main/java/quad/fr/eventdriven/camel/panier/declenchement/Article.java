package quad.fr.eventdriven.camel.panier.declenchement;

import lombok.Builder;

@Builder
public record Article(Long id, String name) {
}
