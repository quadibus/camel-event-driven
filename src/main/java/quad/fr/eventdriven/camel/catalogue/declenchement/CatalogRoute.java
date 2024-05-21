package quad.fr.eventdriven.camel.catalogue.declenchement;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CatalogRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("seda:catalogRoute").choice()
                .when(simple("${header.class} contains 'Commande'"))
                .endChoice();
    }
}
