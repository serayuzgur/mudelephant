package io.mudelephant.core;

import io.mudelephant.common.file.DirectoryUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

/**
 * The base class for MudElephant applications.
 *
 * @param <T> the type of configuration class for this application
 */
public abstract class Application<T extends Configuration> {

    private String usage = "Usage : app configurationFilePath command(optional)";

    /**
     * Starter method of application.
     * * Checks all arguments.
     * * Parses Configuration.
     * * Runs all modules with configuration.
     *
     * @param args
     * @param configClass
     */
    public final void run(final String[] args, Class<T> configClass) {
        Objects.requireNonNull(args, usage);
        if (args.length < 1) {
            System.err.println(usage);
            System.exit(1);
        }
        String configPath = Objects.requireNonNull(args[0], usage);

        T configuration = readConfiguration(configPath, configClass);

        Bootstrap<T> bootstrap = createBootstrap(args, configuration);

        try {
            run(bootstrap, configuration);
            for (Module module : bootstrap.getModules())
                module.run(bootstrap, configuration);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }


    }

    /**
     * Creates the bootstrap instance.
     * @param args
     * @param configuration
     * @return
     */
    private Bootstrap<T> createBootstrap(String[] args, T configuration) {
        return new Bootstrap<T>(args, this, System.in, System.out, configuration);
    }

    /**
     * Reads configuration file. Deserializes it with boon
     *
     * @param configPath
     * @param configClass
     * @return
     */
    private T readConfiguration(String configPath, Class<T> configClass) {
        try {
            File path = DirectoryUtils.getApplicationDirectory(getClass());
            Objects.requireNonNull(path);
            //Append configuration name to path
            path = new File(path, configPath);
            T config = ObjectMapper.getInstance().readValue(new FileInputStream(path), configClass); // 'src' can be File, InputStream, Reader, String
            return config;
        } catch (Exception e) {
            RuntimeException re = new RuntimeException("Can't find configuration file : " + configPath, e);
            throw re;
        }
    }

    /**
     * An Abstract method for applications. All necessary operations must be done here. It will be called before module run methods.
     *
     * @param bootstrap
     * @param configuration
     * @throws Exception
     */
    public abstract void run(Bootstrap bootstrap, T configuration) throws Exception;

}