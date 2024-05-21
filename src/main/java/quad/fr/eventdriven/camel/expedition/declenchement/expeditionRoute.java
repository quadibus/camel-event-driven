package quad.fr.eventdriven.camel.expedition.declenchement;

import lombok.AllArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class expeditionRoute extends RouteBuilder {


    private CalculerPoidsCommandeProcessor calculerPoidsCommandeProcessor ;

    @Override
    public void configure() throws Exception {
        from("seda:calculerPoidsCommandeUseCase")
                .routeId("calculerpoids")
                .log(LoggingLevel.INFO, "input calculerpoids : ${body} -- ${headers}")
                .filter(simple("${header.class} contains 'Commande'"))
                .log("Commande recu")
                .log(LoggingLevel.INFO, "end of calculerPoids")
                .unmarshal().json(JsonLibrary.Jackson, CommandeEvent.class)
                .process(calculerPoidsCommandeProcessor)
                .marshal().json(JsonLibrary.Jackson)
                .to("seda:Inputhub");
    }



}

