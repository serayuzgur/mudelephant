package io.mudelephant.sample;

import io.mudelephant.athlete.configuration.AthleteConfiguration;
import io.mudelephant.athlete.configuration.HasAthleteConfiguration;
import io.mudelephant.db.configuration.DBConfiguration;
import io.mudelephant.db.configuration.HasDBConfiguration;
import io.mudelephant.undertow.configuration.HasUndertowConfiguration;
import io.mudelephant.undertow.configuration.UndertowConfiguration;


public class SampleConfiguration implements
        HasUndertowConfiguration,
        HasAthleteConfiguration,
        HasDBConfiguration {
    private String deneme;
    private UndertowConfiguration undertow;
    private AthleteConfiguration athlete;
    private DBConfiguration batoo;


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
    public DBConfiguration getDatabase() {
        return batoo;
    }
}
