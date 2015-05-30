package hellodb.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;

@Component
public class MyRoute7 extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:test?period=3s")
                .to("sql:SELECT * FROM sites")
                .split(body())
                    // マルチスレッドで処理が全部完了したら結果をリストの順番に集計する
                    // 今回はurlの文字を足し込むだけ
                    .aggregationStrategy((oldExchange, newExchange) -> {
                        if (oldExchange == null) return newExchange;
                        String oldData = oldExchange.getIn().getBody(String.class);
                        String newData = newExchange.getIn().getBody(String.class);
                        String s = oldData + newData;
                        oldExchange.getIn().setBody(s);
                        return oldExchange;
                    })
                    .parallelProcessing()
                    .timeout(2000)
                    // これ以下はマルチスレッド処理
                    .process(exchange -> {
                        // urlだけを取り出してbodyに入れる
                        Map<String, Object> map = exchange.getIn().getBody(Map.class);
                        exchange.getIn().setBody(map.get("url"));
                        Thread.sleep(new Random().nextInt(5) * 1000);
                    })
                .end()
                // マルチスレッドな処理が全て終わった後の処理はこれ以降
                // bodyにはurlの文字列が全て足し込まれた文字列データが入っているはず
                .process(exchange -> {
                    String body = exchange.getIn().getBody(String.class);
                    System.out.println(body);
                })
        ;
    }
}

