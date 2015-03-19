package io.mudelephant.sample;

import com.google.common.collect.Sets;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.mudelephant.athlete.AthleteModule;
import io.mudelephant.core.Application;
import io.mudelephant.core.Bootstrap;
import io.mudelephant.undertow.UndertowModule;

import java.util.Set;

/**
 * Created by serayuzgur on 12/01/15.
 */
public class SampleApplication extends Application<SampleConfiguration> {

    public static void main(String[] args) {
        new SampleApplication().run(args, SampleConfiguration.class);
    }

    @Override
    public void run(Bootstrap bootstrap, SampleConfiguration configuration) throws Exception {
        Set<Class<?>> classes = Sets.newHashSet();
        Set<Object> singletons = Sets.newHashSet();
        classes.add(SampleHtml.class);
        classes.add(SampleResource.class);
        singletons.add(new SampleResource());
        UndertowModule undertow = new UndertowModule();
        Injector injector = Guice.createInjector(new SampleGuiceModule());
        AthleteModule athlete = new AthleteModule(undertow,singletons,classes,injector);
        bootstrap.addModule(undertow);
        bootstrap.addModule(athlete);
    }
}
