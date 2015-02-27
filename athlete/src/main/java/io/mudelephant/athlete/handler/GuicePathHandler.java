package io.mudelephant.athlete.handler;

import io.mudelephant.common.utils.StringUtils;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Handles guice dependency injection operations for handlers.
 */
public class GuicePathHandler implements HttpHandler {
    private final String path;
    private final Map<String, Method> router;


    public GuicePathHandler(String path, Map<String, Method> router) {
        this.path = path;
        this.router = router;
    }

    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        String path = StringUtils.replaceNSlashWith1Slash(httpServerExchange.getRequestPath() + '/' + httpServerExchange.getRequestMethod());

        Method method = router.get(path);
        String result = (String) method.invoke(method.getDeclaringClass().newInstance(), path);

        httpServerExchange.getResponseSender().send(result);

        System.out.println(path);
    }
}
