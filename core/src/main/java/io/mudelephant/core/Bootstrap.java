package io.mudelephant.core;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Bootstrap class for storing necessary information about the application.
 * @param <T>
 */
public class Bootstrap<T extends Configuration> {

    private final String[] args;
    private final List<Module> modules = new LinkedList<Module>();
    private final Application<T> application;
    private final InputStream appIn;
    private final PrintStream appOut;
    private final T configuration;

    /**
     * Creates a new {@link Bootstrap} for the given application.
     * @param args
     * @param application a MudElephant {@link io.mudelephant.core.Application}
     * @param appIn default inputstream for the application
     * @param appOut default output stream for the application
     * @param configuration configuration object generated from the configuration file
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

    /**
     * Returns list of modules on added to the system to run.
     * @return
     */
    public List<Module> getModules() {
        return modules;
    }

    /**
     * Returns the parent application.
     * @return
     */
    public Application<T> getApplication() {
        return application;
    }

    /**
     * Returns the default input stream of the application.
     * It is required in case of you want to read something after you start your application.
     * @return
     */
    public InputStream getAppIn() {
        return appIn;
    }

    /**
     * Returns the default output stream of the application.
     * @return
     */
    public PrintStream getAppOut() {
        return appOut;
    }

    /**
     * Returns the array of arguments which are given at the start of application.
     * @return
     */
    public String[] getArgs() {
        return args;
    }

    /**
     * Returns the configuration object created from the configuration file.
     * @return
     */
    public T getConfiguration() {
        return configuration;
    }
}
