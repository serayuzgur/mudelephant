package io.mudelephant.sample;

import com.google.common.collect.Sets;
import io.mudelephant.athlete.AthleteModule;
import io.mudelephant.athlete.DefaultJSR311Application;
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
        DefaultJSR311Application app = new DefaultJSR311Application(singletons, classes);
        UndertowModule undertow = new UndertowModule();
        AthleteModule athlete = new AthleteModule(app, undertow);
        bootstrap.addModule(undertow);
        bootstrap.addModule(athlete);
    }
}
