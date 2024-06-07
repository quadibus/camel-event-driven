package quad.fr.eventdriven.camel.expedition.declenchement;

import lombok.AllArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ExpeditionRoute extends RouteBuilder {


    private CalculerPoidsCommandeProcessor calculerPoidsCommandeProcessor;

    @Override
    public void configure() throws Exception {
        from("kafka:event-topic")
                .routeId("expeditionRoute")
                .choice().
                when(simple("${header.class} contains 'PanierModifiedEvent'"))
                    .log("Commande recue")
                    .unmarshal().json(JsonLibrary.Jackson, PanierModifieEvent.class)
                    .log(LoggingLevel.INFO, "input calculerpoids : ${body} -- ${headers}")
                    .process(calculerPoidsCommandeProcessor)
                    .marshal().json(JsonLibrary.Jackson)
                    .to("seda:Inputhub")
                .end();


        // .filter(simple("${header.class} contains 'Commande'"))


    }


}

