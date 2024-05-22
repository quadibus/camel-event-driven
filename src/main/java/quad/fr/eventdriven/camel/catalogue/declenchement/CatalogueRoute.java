package quad.fr.eventdriven.camel.catalogue.declenchement;

import lombok.AllArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CatalogueRoute extends RouteBuilder {

     CalculerCoutTotalPanierProcessor calculerCoutTotalPanierProcessor;

    @Override
    public void configure() throws Exception {
        from("seda:catalogue").id("catalogueRoute")
                .choice()
                    .when(simple("${header.class} contains 'PanierModifiedEvent'"))
                        .log("Commande recue")
                        .unmarshal().json(JsonLibrary.Jackson, PanierModifieEvent.class)
                        .log(LoggingLevel.INFO, "input calculerCoutPanier : ${body} -- ${headers}")
                        .process(calculerCoutTotalPanierProcessor)
                        .marshal().json(JsonLibrary.Jackson)
                        .to("seda:Inputhub")
                .end();
    }
}
