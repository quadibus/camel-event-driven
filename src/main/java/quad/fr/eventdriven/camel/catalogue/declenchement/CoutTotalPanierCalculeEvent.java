package quad.fr.eventdriven.camel.catalogue.declenchement;

import lombok.Builder;

@Builder
public record CoutTotalPanierCalculeEvent(Long id_panier, Float coutTotal) {
}
