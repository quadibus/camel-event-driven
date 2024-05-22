package quad.fr.eventdriven.camel.catalogue.application;

import lombok.Builder;

import java.util.List;

@Builder
public record Panier (Long id, List<Article> articles  ) {
}
