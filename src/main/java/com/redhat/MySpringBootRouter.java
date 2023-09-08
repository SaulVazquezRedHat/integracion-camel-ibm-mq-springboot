package com.redhat;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class MySpringBootRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:mytimer?period=5000")
            .routeId("generate-route")
            .transform(constant("HELLO from Camel!"))
            .wireTap("direct:tap");

        from("direct:tap")   
            .log("${body}")
            .to("mq:queue:DEV.QUEUE.1");
      }

}
