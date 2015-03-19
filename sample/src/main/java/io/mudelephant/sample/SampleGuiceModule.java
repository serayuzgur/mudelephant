package io.mudelephant.sample;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;

/**
 * Created by serayuzgur on 19/03/15.
 */
public class SampleGuiceModule extends AbstractModule {

    @Override
    protected void configure() {


        bind(SamplePojo.class).toProvider(new Provider<SamplePojo>() {

            @Override
            public SamplePojo get() {
                SamplePojo samplePojo = new SamplePojo();
                samplePojo.setId(123);
                samplePojo.setName("hello");
                return samplePojo;
            }
        });

    }


}
