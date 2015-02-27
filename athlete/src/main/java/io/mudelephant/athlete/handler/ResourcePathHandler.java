package io.mudelephant.athlete.handler;

import io.mudelephant.athlete.param.ExchangeBag;
import io.mudelephant.athlete.param.setter.Setter;
import io.mudelephant.athlete.resource.MethodEntry;
import io.mudelephant.common.utils.StringUtils;
import io.mudelephant.core.ObjectMapper;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Handles routing of the request. Invokes the mapped resource method
 */
public class ResourcePathHandler implements HttpHandler {
    private final String path;
    private final Map<String, MethodEntry> router;


    public ResourcePathHandler(String path, Map<String, MethodEntry> router) {
        this.path = path;
        this.router = router;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        if (exchange.isInIoThread()) {
            exchange.dispatch(this);
            return;
        }
        String path = concatPath(exchange);

        MethodEntry pair = getResourceMethodBy(path);

        executeMethod(pair.getKey(), pair.getValue(), exchange);

        //TODO: log call properly.
        System.out.println(path);
    }

    private void executeMethod(Method method, Setter[] setters, HttpServerExchange exchange) throws Exception {
        Object[] objects = new Object[setters.length];
        ExchangeBag bag = new ExchangeBag(exchange);

        for (int j = 0; j < setters.length; j++) {
            Object o = setters[j].get(bag);
            objects[j] = o;
        }
        //TODO: find a way to handle streams
        String result = ObjectMapper.getInstance().toJson(method.invoke(method.getDeclaringClass().newInstance(), objects));
        exchange.getResponseSender().send(result);

    }

    private MethodEntry getResourceMethodBy(String path) {
        return router.get(path);
    }


    private String concatPath(HttpServerExchange exchange) {
        return StringUtils.replaceNSlashWith1Slash(exchange.getRequestPath() + '/' + exchange.getRequestMethod());
    }
}
