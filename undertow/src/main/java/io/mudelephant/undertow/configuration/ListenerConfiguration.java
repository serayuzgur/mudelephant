package io.mudelephant.undertow.configuration;

/**
 * Holds configuration for listener.
 */
public class ListenerConfiguration {

    public enum Type {
        HTTP,
        HTTPS,
        AJP
    }
    private String host;
    private short port;
    private Type type;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public short getPort() {
        return port;
    }

    public void setPort(short port) {
        this.port = port;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
