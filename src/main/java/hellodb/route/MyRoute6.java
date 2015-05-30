package hellodb.route;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

//@Component
public class MyRoute6 extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:test?period=3s")
                .to("sql:SELECT * FROM sites")
                .split(body()).parallelProcessing()
                    .marshal().json(JsonLibrary.Jackson)
                    .to("http://localhost/myservice")
                .end();
    }
}