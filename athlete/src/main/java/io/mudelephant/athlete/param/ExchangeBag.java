package io.mudelephant.athlete.param;

import io.undertow.server.HttpServerExchange;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ExchangeBag is a holder class for sharing data between Setters.
 */
public class ExchangeBag {

    private final HttpServerExchange exchange;

    private Map<String, String> formParameters = null;

    private byte[] requestPayload = null;

    public ExchangeBag(HttpServerExchange exchange) {
        this.exchange = exchange;
    }

    public HttpServerExchange getExchange() {
        return exchange;
    }

    public byte[] getRequestPayload() {
        if (requestPayload == null) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try {
                while (exchange.getInputStream().available() > 0)
                    outputStream.write(exchange.getInputStream().read());
                requestPayload = outputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return requestPayload;
    }

    public Map<String, String> getFormParameters() {
        return formParameters;
    }

    public void setFormParameters(Map<String, String> formParameters) {
        this.formParameters = new HashMap<String, String>(formParameters);
    }

}
