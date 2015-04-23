package io.mudelephant.athlete.handler;

import io.mudelephant.athlete.handler.listener.ExecuteListener;
import io.mudelephant.athlete.param.ExchangeBag;
import io.mudelephant.athlete.param.setter.Setter;
import io.mudelephant.athlete.resource.ServiceInfo;
import io.mudelephant.common.utils.StringUtils;
import io.mudelephant.core.ObjectMapper;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import org.abstractmeta.reflectify.MethodInvoker;
import org.abstractmeta.reflectify.Reflectify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Handles routing of the request. Invokes the mapped resource method
 */
public class ResourcePathHandler implements HttpHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcePathHandler.class);

    private final String path;
    private final Map<String, ServiceInfo> router;
    private final ExecuteListener[] listeners;


    public ResourcePathHandler(final String path, final Map<String, ServiceInfo> router, final ExecuteListener[] listeners) {
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
        ServiceInfo serviceInfo = getResourceMethodBy(path);
        if (serviceInfo == null) {
            LOGGER.warn("Path Not Found: {}", path);
            exchange.setResponseCode(404);
            return;
        }
        try {
            callBefores(serviceInfo);
            executeMethod(serviceInfo, exchange);
            callSuccesses(serviceInfo);
        } catch (Exception e) {
            LOGGER.error("Error at handling Path:" + path, e);
            callErrors(serviceInfo, e);
        }

    }

    private void executeMethod(ServiceInfo serviceInfo, HttpServerExchange exchange) throws Exception {
        MethodInvoker invoker = serviceInfo.getInvoker();
        Setter[] setters = serviceInfo.getSetters();
        ExchangeBag bag = new ExchangeBag(exchange);

        for (int j = 0; j < setters.length; j++) {
            Object o = setters[j].get(bag);
            invoker.getParameterSetter(j).set(o);
        }
        //TODO: find a way to handle streams
        ObjectMapper.getInstance().writeValue(exchange.getOutputStream(),invoker.invoke(createInstance(serviceInfo.getProvider())));
        exchange.getOutputStream().flush();
    }

    protected Object createInstance(Reflectify.Provider provider) throws IllegalAccessException, InstantiationException {
        return provider.get();
    }

    private ServiceInfo getResourceMethodBy(String path) {
        return router.get(path);
    }


    private String concatPath(HttpServerExchange exchange) {
        return StringUtils.replaceNSlashWith1Slash('/' + exchange.getRequestPath() + '/' + exchange.getRequestMethod());
    }


    private void callBefores(ServiceInfo entry) {
        for (ExecuteListener listener : listeners)
            listener.before(entry);
    }

    private void callSuccesses(ServiceInfo entry) {
        for (ExecuteListener listener : listeners)
            listener.success(entry);
    }

    private void callErrors(ServiceInfo entry, Exception e) {
        for (ExecuteListener listener : listeners)
            listener.error(entry, e);
    }
}
