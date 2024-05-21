package quad.fr.eventdriven.camel.catalogue.application;

import lombok.Builder;

@Builder
public record Article (Long id, String name, Float poids){}
