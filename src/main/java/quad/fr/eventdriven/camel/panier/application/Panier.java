package quad.fr.eventdriven.camel.panier.application;

import lombok.Builder;

import java.util.List;

@Builder
public record Panier(Long id, List<Article> articles) {
}
