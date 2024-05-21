package quad.fr.eventdriven.camel;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TechnicalMulticast extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("seda:Inputhub")
                .routeId("multicast")
                .multicast()
                .parallelProcessing()
                    .to("seda:calculerPoidsCommandeUseCase")
                    .to("seda:log")
                .end()
                //.log(LoggingLevel.INFO,"input hub :: Received multicasted message : ${headers} :: ${body} ")

        ;

    }
}
