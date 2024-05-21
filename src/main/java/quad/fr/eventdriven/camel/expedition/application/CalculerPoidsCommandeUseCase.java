package quad.fr.eventdriven.camel.expedition.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class CalculerPoidsCommandeUseCase {

    private LivrableDataPort livrableDataPort;

    public Float executer(Colis colis) {
        log.info("calcul poids commande"+ colis);
        return colis.livrables().stream()
                .map(produit -> livrableDataPort.getByProductId(produit.id()))
                .map(Optional::orElseThrow)
                .map(Livrable::poids).reduce(0.0F,Float::sum);
    }



}
