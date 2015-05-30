package hellodb.route;

import org.apache.camel.builder.RouteBuilder;

import java.util.Map;

//@Component
public class MyRoute4 extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:test?period=3s")
                .to("sql:SELECT * FROM sites")
                .split(body())
                    .process(exchange -> {
                        Map row = exchange.getIn().getBody(Map.class);
                        System.out.println("url:" + row.get("url"));
                    })
                .end();
    }
}

