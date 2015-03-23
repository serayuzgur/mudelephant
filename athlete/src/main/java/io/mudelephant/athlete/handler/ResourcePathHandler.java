package io.mudelephant.athlete.handler;

import io.mudelephant.athlete.param.ExchangeBag;
import io.mudelephant.athlete.param.setter.Setter;
import io.mudelephant.athlete.resource.MethodEntry;
import io.mudelephant.common.utils.StringUtils;
import io.mudelephant.core.ObjectMapper;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Handles routing of the request. Invokes the mapped resource method
 */
public class ResourcePathHandler implements HttpHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcePathHandler.class);

    private final String path;
    private final Map<String, MethodEntry> router;


    public ResourcePathHandler(String path, Map<String, MethodEntry> router) {
        this.path = path;
        this.router = router;
    }

    public void handleRequest(HttpServerExchange exchange) throws Exception {
        if (exchange.isInIoThread()) {
            exchange.dispatch(this);
            return;
        }
        String path = concatPath(exchange);

        LOGGER.info("Request: {}", path);

        MethodEntry pair = getResourceMethodBy(path);

        if (pair == null) {
            LOGGER.warn("Path Not Found: {}", path);
            exchange.setResponseCode(404);
            return;
        }


        executeMethod(pair.getKey(), pair.getValue(), exchange);

    }

    private void executeMethod(Method method, Setter[] setters, HttpServerExchange exchange) throws Exception {
        Object[] objects = new Object[setters.length];
        ExchangeBag bag = new ExchangeBag(exchange);

        for (int j = 0; j < setters.length; j++) {
            Object o = setters[j].get(bag);
            objects[j] = o;
        }
        //TODO: find a way to handle streams
        String result = ObjectMapper.getInstance().toJson(method.invoke(createInstance(method), objects));
        //TODO: find a way to write as byte[]
        exchange.getResponseSender().send(result);

    }

    protected Object createInstance(Method method) throws IllegalAccessException, InstantiationException {
        return method.getDeclaringClass().newInstance();
    }

    private MethodEntry getResourceMethodBy(String path) {
        return router.get(path);
    }


    private String concatPath(HttpServerExchange exchange) {
        return StringUtils.replaceNSlashWith1Slash('/' + exchange.getRequestPath() + '/' + exchange.getRequestMethod());
    }
}
