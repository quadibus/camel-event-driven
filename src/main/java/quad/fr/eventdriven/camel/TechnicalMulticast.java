package quad.fr.eventdriven.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TechnicalMulticast extends RouteBuilder {

    public static final String routeAddr = "seda:Inputhub";
    @Override
    public void configure() throws Exception {
        from(routeAddr)
                .routeId("multicast-hub")
                .multicast()
                .parallelProcessing()
                    .to("seda:expedition")
                    .to("seda:catalogue")
                    .to("seda:log")
                .end()
        ;

    }
}
