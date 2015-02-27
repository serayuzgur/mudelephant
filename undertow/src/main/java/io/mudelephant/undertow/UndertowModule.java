package io.mudelephant.undertow;

import io.mudelephant.core.Bootstrap;
import io.mudelephant.core.Module;
import io.mudelephant.undertow.configuration.HasUndertowConfiguration;
import io.mudelephant.undertow.configuration.ListenerConfiguration;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.BlockingHandler;
import io.undertow.server.handlers.PathHandler;

import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;


public class UndertowModule<T extends HasUndertowConfiguration> extends Module<T> {
    PathHandler pathHandler = new PathHandler();

    @Override
    public void run(Bootstrap bootstrap, T configuration) {
        Undertow.Builder serverBuilder = Undertow.builder();
        for (ListenerConfiguration conf : configuration.getUndertow().getListeners()) {
            switch (conf.getType()) {
                case HTTP:
                    serverBuilder.addHttpListener(conf.getPort(), conf.getHost());
                    break;
                case HTTPS:
                    try {
                        serverBuilder.addHttpsListener(conf.getPort(), conf.getHost(), SSLContext.getDefault());
                    } catch (NoSuchAlgorithmException e) {
                        bootstrap.getAppOut().print(e);
                    }
                    break;
                case AJP:
                    serverBuilder.addAjpListener(conf.getPort(), conf.getHost());
                    break;
            }

        }

        serverBuilder.setHandler(new BlockingHandler(pathHandler));
        serverBuilder.build().start();
    }

    /**
     * Registers handlers to the given path.
     * @param path
     * @param handler
     */
    public void registerHandler(String path, HttpHandler handler) {
        pathHandler.addPrefixPath(path, handler);
    }
}
