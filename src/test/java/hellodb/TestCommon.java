package hellodb;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Before;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by daikuro on 15/05/29.
 */
public class TestCommon extends CamelSpringTestSupport {

    public static final String CLASSPATH_SPRING_XML = "spring.xml";


    public Exchange exchange;

    @Before
    public void init() {
        exchange = new DefaultExchange(context());
    }

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext(CLASSPATH_SPRING_XML);
    }
}
