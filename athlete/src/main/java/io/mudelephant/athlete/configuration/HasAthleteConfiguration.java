package io.mudelephant.athlete.configuration;

import io.mudelephant.core.Configuration;

public interface HasAthleteConfiguration extends Configuration {
    AthleteConfiguration getAthlete();

}
