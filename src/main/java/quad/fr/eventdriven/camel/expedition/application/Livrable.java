package quad.fr.eventdriven.camel.expedition.application;


import lombok.Builder;

@Builder
public record Livrable(Long id, Float poids) {
}
