package quad.fr.eventdriven.camel.catalogue.declenchement;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import quad.fr.eventdriven.camel.catalogue.application.CalculerCoutTotalPanierUseCase;
import quad.fr.eventdriven.camel.catalogue.application.Panier;

@Component
@AllArgsConstructor
@Slf4j
public class CalculerCoutTotalPanierProcessor implements Processor {

    CalculerCoutTotalPanierUseCase calculerCoutTotalPanierUseCase;
    PanierModifierToPanierEventMapper panierMapper;

    @Override
    public void process(Exchange exchange) throws Exception {
        PanierModifieEvent panierModifieEvent = exchange.getIn().getBody(PanierModifieEvent.class);
        //Mapper
        Panier panier = panierMapper.panierModifierEventToPanier(panierModifieEvent);
        //Appel usecase
        Float coutTotalPanier = calculerCoutTotalPanierUseCase.executer(panier);
        //retour mapping event
        CoutTotalPanierCalculeEvent coutTotalPanierEvent = CoutTotalPanierCalculeEvent.builder().id_panier(panier.id()).coutTotal(coutTotalPanier).build();
        // retour route.
        exchange.getIn().setBody(coutTotalPanierEvent);
        exchange.getIn().setHeader("class", CoutTotalPanierCalculeEvent.class.toString());

    }
}
