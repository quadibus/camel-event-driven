package quad.fr.eventdriven.camel.panier.notification;


import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import quad.fr.eventdriven.camel.panier.application.Article;
import quad.fr.eventdriven.camel.panier.application.Panier;

import java.util.List;
import java.util.UUID;


public class PanierQuartzRoute extends RouteBuilder {

    static final String idRoute = "panierQuartzRoute";

    @Override
    public void configure() throws Exception {
        from("quartz://paniersimu?cron=0+*+*+*+*+?")
                .routeId("PanierSimu")
                .setHeader("class",constant(Panier.class.toString()))
                .setHeader("correlationID",constant(UUID.randomUUID()))
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Panier commande = Panier.builder()
                                .id(123L)
                                .articles(
                                        List.of(
                                                Article.builder()
                                                        .id(169L)
                                                        .build(),
                                                Article.builder()
                                                        .id(901L)
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
