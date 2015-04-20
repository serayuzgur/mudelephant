package io.mudelephant.athlete.handler;

import io.mudelephant.athlete.handler.listener.ExecuteListener;
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
    private final ExecuteListener[] listeners;


    public ResourcePathHandler(final String path, final Map<String, MethodEntry> router, final ExecuteListener[] listeners) {
        this.path = path;
        this.router = router;
        this.listeners = listeners;
    }

    public void handleRequest(HttpServerExchange exchange) throws Exception {
        if (exchange.isInIoThread()) {
            exchange.dispatch(this);
            return;
        }
        String path = concatPath(exchange);
        LOGGER.info("Request: {}", path);
        MethodEntry entry = getResourceMethodBy(path);
        if (entry == null) {
            LOGGER.warn("Path Not Found: {}", path);
            exchange.setResponseCode(404);
            return;
        }
        try {
            callBefores(entry);
            executeMethod(entry, exchange);
            callSuccesses(entry);
        } catch (Exception e) {
            LOGGER.error("Error at handling Path:" + path, e);
            callErrors(entry, e);
        }

    }

    private void executeMethod(MethodEntry entry, HttpServerExchange exchange) throws Exception {
        Method method = entry.getKey();
        Setter[] setters = entry.getValue();
        Object[] objects = new Object[setters.length];
        ExchangeBag bag = new ExchangeBag(exchange);

        for (int j = 0; j < setters.length; j++) {
            Object o = setters[j].get(bag);
            objects[j] = o;
        }
        //TODO: find a way to handle streams
        ObjectMapper.getInstance().writeValue(exchange.getOutputStream(),method.invoke(createInstance(method), objects));
        exchange.getOutputStream().flush();
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


    private void callBefores(MethodEntry entry) {
        for (ExecuteListener listener : listeners)
            listener.before(entry);
    }

    private void callSuccesses(MethodEntry entry) {
        for (ExecuteListener listener : listeners)
            listener.success(entry);
    }

    private void callErrors(MethodEntry entry, Exception e) {
        for (ExecuteListener listener : listeners)
            listener.error(entry, e);
    }
}
