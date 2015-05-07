package io.mudelephant.core;

public abstract class Module<T extends Configuration> {

    public abstract void run(final Bootstrap bootstrap, final T configuration) throws ClassNotFoundException;
}
