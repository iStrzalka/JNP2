package jnp2.projekt1.projekt;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.camel.Exchange;

import org.apache.camel.Processor;

import org.springframework.stereotype.Component;
import java.lang.*;
import java.util.Currency;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class AlertProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getIn().getBody(String.class);
        String result = "";
        result = result + "Aktualnie jest " + get_result(body, "\"description\":\"([a-zA-Z0-9 ]+)\"") + "\n";
        result = result + "Temperatura aktualna jest rowna " + get_result(body, "\"temp\":([0-9.]+),") + 
                 "°C, gdzie odczuwalna jest rowna " + get_result(body, "\"feels_like\":([0-9.]+),") + "°C\n";
        result = result + "Mozemy tez poczuc wiatr o predkosci " + get_result(body, "\"wind\":\\{\"speed\":([0-9.]+),") + "m/s\n";
        exchange.getIn().setBody(result);
    }

    String get_result(String body, String pattern) {
        Pattern r =  Pattern.compile(pattern, Pattern.MULTILINE);
        Matcher matcher = r.matcher(body);
        String result="";
        if(matcher.find())
            result = matcher.group(1);
        // System.out.println("Result for " + pattern + ": " + result);
        return result;
    }
}
