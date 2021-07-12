package jnp2.projekt1.projekt;


import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class AlertRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("timer:scheduler?period={{check.interval}}")
                .setHeader(Exchange.HTTP_METHOD).constant("GET")
                .to("https://api.openweathermap.org/data/2.5/weather/?q={{api.city}}&appid={{api.weather}}&lang=pl&units=metric")
                .to("direct://sendWeatherAlert");

        from("direct://sendWeatherAlert")
                .sample(10, TimeUnit.SECONDS)
                .process(new AlertProcessor())
                .filter(body())
                .setHeader("to",simple("{{mail.user}}@gmail.com"))
                .to("smtps://smtp.gmail.com?username={{mail.user}}&password={{mail.password}}")
                .log("Send weather alert: ${body}");

        from("timer:scheduler?period={{check.interval}}")
            .setHeader(Exchange.HTTP_METHOD).constant("GET")
            .to("https://api.nbp.pl/api/exchangerates/tables/A/today/?format=json")
            .to("direct://sendExchangeRatesAlert");
        
        from("direct://sendExchangeRatesAlert")
            .sample(10, TimeUnit.SECONDS)
            .process(new ExchangeProcessor())
            .filter(body())
            .setHeader("to",simple("{{mail.user}}@gmail.com"))
            .to("smtps://smtp.gmail.com?username={{mail.user}}&password={{mail.password}}")
            .log("Send exchange alert: ${body}");
    
    }
}