package io.mudelephant.sample;

import io.mudelephant.athlete.configuration.AthleteConfiguration;
import io.mudelephant.athlete.configuration.HasAthleteConfiguration;
import io.mudelephant.undertow.configuration.HasUndertowConfiguration;
import io.mudelephant.undertow.configuration.UndertowConfiguration;


public class SampleConfiguration implements HasUndertowConfiguration,HasAthleteConfiguration {
    private String deneme;
    private UndertowConfiguration undertow;
    private AthleteConfiguration athlete;


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

    @Override
    public AthleteConfiguration getAthlete() {
        return athlete;
    }
}
