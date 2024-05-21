package quad.fr.eventdriven.camel.expedition.declenchement;

import lombok.Builder;

@Builder
public record PoidsColisCalculeEvent(Long idCommande, Float poidsColis ) {
}
