package quad.fr.eventdriven.camel;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TechnicalLogger extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("seda:log")
                .routeId("logger")
                .log(LoggingLevel.INFO,"Received logging : ${headers} ::::::::: ${body}");
    }
}
