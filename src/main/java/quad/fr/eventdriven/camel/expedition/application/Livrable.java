package quad.fr.eventdriven.camel.expedition.application;


import lombok.Builder;

import java.util.UUID;

@Builder
public record Livrable(Long id, String name, Float poids) {
}
