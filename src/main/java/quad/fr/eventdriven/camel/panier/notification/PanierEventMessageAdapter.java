package quad.fr.eventdriven.camel.panier.notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;
import quad.fr.eventdriven.camel.panier.application.Panier;
import quad.fr.eventdriven.camel.panier.application.PanierEventPort;

import java.util.UUID;

@Component
public class PanierEventMessageAdapter implements PanierEventPort {

    @Produce("kafka:event-topic")
    ProducerTemplate producer;


    @Override
    public void notifierModificationPanier(Panier panier) {
        //map to event
        PanierModifiedEvent panierModifiedEvent = PanierModifiedEvent.builder().articles(panier.articles()).id(panier.id()).build();
        producer.send(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {

                exchange.getIn().setHeader("class", panierModifiedEvent.getClass().toString());
                exchange.getIn().setHeader("correlationID", UUID.randomUUID());
                ObjectMapper mapper = new ObjectMapper();

                exchange.getIn().setBody(mapper.writeValueAsBytes(panierModifiedEvent), String.class);
            }
        });

    }

}
