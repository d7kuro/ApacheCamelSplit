package hellodb.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.sql.SqlConstants;

//@Component
public class MyRoute2 extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:test?period=3s")
                .to("sql:SELECT * FROM sites")
                .process(exchange -> {
                    int count = exchange.getIn().getHeader(SqlConstants.SQL_ROW_COUNT, int.class);
                    System.out.println("cound:" + count);
                });
    }
}

