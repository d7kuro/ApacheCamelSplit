package hellodb.route;

import org.apache.camel.builder.RouteBuilder;

public class MyRoute1 extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:test?period=3s")
        .process(exchange -> {
            System.out.println(1111);
        });
    }
}

