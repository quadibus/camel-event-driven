package quad.fr.eventdriven.camel.catalogue.application;

import lombok.Builder;

@Builder
public record ArticleSaved (Long id,String name, Float poids)  {
}
