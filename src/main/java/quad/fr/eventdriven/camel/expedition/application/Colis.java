package quad.fr.eventdriven.camel.expedition.application;

import lombok.Builder;

import java.util.List;

@Builder
public record Colis(Long idCommande,List<Livrable> livrables) {
}

