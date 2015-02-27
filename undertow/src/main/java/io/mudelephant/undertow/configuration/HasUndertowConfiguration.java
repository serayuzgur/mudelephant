package io.mudelephant.undertow.configuration;

import io.mudelephant.core.Configuration;

public interface HasUndertowConfiguration extends Configuration {

    public UndertowConfiguration getUndertow();
}
