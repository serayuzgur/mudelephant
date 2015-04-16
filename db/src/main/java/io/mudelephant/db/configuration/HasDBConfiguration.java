package io.mudelephant.db.configuration;

import io.mudelephant.core.Configuration;

public interface HasDBConfiguration extends Configuration {
    DBConfiguration getDatabase();
}
