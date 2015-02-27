package io.mudelephant.undertow.handler;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

/**
 * This is the handler that will be matched if a URL does not match any of the paths that are registered with Undertow.
 * In this case we do not have any other handlers registered, so this handler is always invoked.
 */
public class DefaultHttpHandler implements HttpHandler {
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        exchange.getResponseSender().send("Path not found");

    }
}
