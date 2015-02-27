package io.mudelephant.sample;

import io.mudelephant.undertow.configuration.HasUndertowConfiguration;
import io.mudelephant.undertow.configuration.UndertowConfiguration;


public class SampleConfiguration implements HasUndertowConfiguration {
    private String deneme;
    private UndertowConfiguration undertow;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SampleConfiguration{");
        sb.append("deneme='").append(deneme).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public UndertowConfiguration getUndertow() {
        return undertow;
    }
}
