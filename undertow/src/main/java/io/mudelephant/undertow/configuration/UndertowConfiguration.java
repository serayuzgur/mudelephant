package io.mudelephant.undertow.configuration;


public class UndertowConfiguration {

    private ListenerConfiguration[] listeners;
    private ContextConfiguration[] contexts;


    public ListenerConfiguration[] getListeners() {
        return listeners;
    }

    public void setListeners(ListenerConfiguration[] listeners) {
        this.listeners = listeners;
    }

    public ContextConfiguration[] getContexts() {
        return contexts;
    }

    public void setContexts(ContextConfiguration[] contexts) {
        this.contexts = contexts;
    }
}
