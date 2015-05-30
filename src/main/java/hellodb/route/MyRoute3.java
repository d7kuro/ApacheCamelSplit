package hellodb.route;

import org.apache.camel.builder.RouteBuilder;

import java.util.List;
import java.util.Map;

//@Component
public class MyRoute3 extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:test?period=3s")
                .to("sql:SELECT * FROM sites")
                .process(exchange -> {
                    List<Map> rows = exchange.getIn().getBody(List.class);
                    for (Map row : rows ) {
                        System.out.println("url:" + row.get("url"));
                    }
                });
    }
}

