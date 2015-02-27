package io.mudelephant.athlete;

import io.mudelephant.athlete.handler.ResourcePathHandler;
import io.mudelephant.athlete.resource.ResourceMapper;
import io.mudelephant.core.Bootstrap;
import io.mudelephant.core.Configuration;
import io.mudelephant.core.Module;
import io.mudelephant.undertow.UndertowModule;

import javax.ws.rs.core.Application;

/**
 * Initialized Athlete module, creates a {@link ResourcePathHandler} and registers it to {@link UndertowModule}
 */
public class AthleteModule<T extends Configuration> extends Module<T> {
    public Application application;

    public AthleteModule(final Application application, final UndertowModule undertowModule) {
        this.application = application;
        ResourceMapper mapper = new ResourceMapper("/", application.getClasses());
        undertowModule.registerHandler("/", new ResourcePathHandler("*", mapper.getRouteMap()));
    }

    @Override
    public void run(Bootstrap bootstrap, T configuration) {


    }

}
