package hellodb.route;

import hellodb.TestCommon;
import org.junit.Test;

/**
 * お試し起動要
 */
public class MyRoute1Test extends TestCommon {

    @Test
    public void test() throws Exception {
        // @Componentが付いているルートを起動後に単に１０秒待つだけの試験
        Thread.sleep(10000);
    }

}