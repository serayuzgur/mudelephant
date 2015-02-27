package io.mudelephant.core;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class Bootstrap<T extends Configuration> {

    private final String[] args;
    private final List<Module> modules = new LinkedList<Module>();
    private final Application<T> application;
    private final InputStream appIn;
    private final PrintStream appOut;
    private final T configuration;

    /**
     * Creates a new {@link Bootstrap} for the given application.
     *  @param args
     * @param application a MudElephant {@link io.mudelephant.core.Application}
     * @param appIn
     * @param appOut
     * @param configuration
     */
    public Bootstrap(String[] args, Application<T> application, InputStream appIn, PrintStream appOut, T configuration) {
        this.args = args;
        this.application = application;
        this.appIn = appIn;
        this.appOut = appOut;
        this.configuration = configuration;
    }


    /**
     * Adds module to the bootstrap.
     * It is ordered. So be pay attention on calling order.
     *
     * @param module module to add
     */
    public void addModule(Module module) {
        modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }

    public Application<T> getApplication() {
        return application;
    }

    public InputStream getAppIn() {
        return appIn;
    }

    public PrintStream getAppOut() {
        return appOut;
    }

    public String[] getArgs() {
        return args;
    }

    public T getConfiguration() {
        return configuration;
    }
}
