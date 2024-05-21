package quad.fr.eventdriven.camel.panier.declenchement;


import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PanierRoute extends RouteBuilder {

    static final String idRoute = "panierRoute";

    @Override
    public void configure() throws Exception {
        from("quartz://paniersimu?cron=0+*+*+*+*+?")
                .routeId("PanierSimu")
                .setHeader("class",constant(Commande.class.toString()))
                .setHeader("correlationID",constant(UUID.randomUUID()))
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Commande commande = Commande.builder()
                                .id(123L)
                                .articles(
                                        List.of(
                                                Article.builder()
                                                        .id(2345432L)
                                                        .name("frigo")
                                                        .build(),
                                                Article.builder()
                                                        .id(2345433L)
                                                        .name("PC portable")
                                                        .build()
                                        )
                                ).build();
                        exchange.getIn().setBody(commande);
                    }
                })
                .marshal().json(JsonLibrary.Jackson)
                .log(LoggingLevel.INFO,"panierSimu to hub ${body}")
                .to("seda:Inputhub");
    }
}
