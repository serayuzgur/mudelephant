package io.mudelephant.sample;

import io.mudelephant.athlete.configuration.AthleteConfiguration;
import io.mudelephant.athlete.configuration.HasAthleteConfiguration;
import io.mudelephant.batoo.configuration.BatooConfiguration;
import io.mudelephant.batoo.configuration.HasBatooConfiguration;
import io.mudelephant.undertow.configuration.HasUndertowConfiguration;
import io.mudelephant.undertow.configuration.UndertowConfiguration;


public class SampleConfiguration implements
        HasUndertowConfiguration,
        HasAthleteConfiguration,
        HasBatooConfiguration {
    private String deneme;
    private UndertowConfiguration undertow;
    private AthleteConfiguration athlete;
    private BatooConfiguration batoo;


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

    @Override
    public BatooConfiguration getBatoo() {
        return batoo;
    }
}
