package io.mudelephant.core;

/**
 * An abstract class for all modules to extend in the application.
 * @param <T>
 */
public abstract class Module<T extends Configuration> {

    public abstract void run(final Bootstrap bootstrap, final T configuration) throws ClassNotFoundException;
}
