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
public class ExchangeProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getIn().getBody(String.class);
        String result = "";
        result = result + "Aktualnie 1 EUR kosztuje " + get_result(body, "\\{\"currency\":\"euro\",\"code\":\"EUR\",\"mid\":([0-9.]+)\\}") + "zl, "
                 + "1USD kosztuje " + get_result(body, "\\{\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"mid\":([0-9.]+)\\}") + "zl, "
                 + "1CZK kosztuje " + get_result(body, "\\{\"currency\":\"korona czeska\",\"code\":\"CZK\",\"mid\":([0-9.]+)\\}") + "zl.";
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
