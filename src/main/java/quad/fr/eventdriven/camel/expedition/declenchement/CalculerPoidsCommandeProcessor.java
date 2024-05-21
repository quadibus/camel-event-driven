package quad.fr.eventdriven.camel.expedition.declenchement;

import quad.fr.eventdriven.camel.expedition.application.CalculerPoidsCommandeUseCase;
import quad.fr.eventdriven.camel.expedition.application.Colis;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class CalculerPoidsCommandeProcessor implements Processor {

    CalculerPoidsCommandeUseCase calculerPoidsCommandeUseCase;
    CommandeEventMapper mapper;

    @Override
    public void process(Exchange exchange) throws Exception {
        log.info(exchange.getIn().getBody(String.class));
        CommandeEvent commandeEvent = exchange.getIn().getBody(CommandeEvent.class);
        Colis colis = mapper.mapCommentEventToCommande(commandeEvent);
        Float poidsColis = calculerPoidsCommandeUseCase.executer(colis);

        PoidsColisCalculeEvent poidsColisCalculeEvent = PoidsColisCalculeEvent.builder()
                .idCommande(colis.idCommande())
                .poidsColis(poidsColis).build();

        exchange.getIn().setBody(poidsColisCalculeEvent);
        exchange.getIn().setHeader("class", PoidsColisCalculeEvent.class.toString());
    }
}
