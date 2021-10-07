package io.github.brianwyka.command;

import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

/**
 * A command which is used for creating classes usig Reflection
 *
 * @author brianwyka
 */
@Slf4j(topic = "OUT")
@CommandLine.Command(
        name = "reflect",
        description = "Instantiate a class with reflection",
        mixinStandardHelpOptions = true
)
public class Reflect implements Callable<Integer> {

    private static final Logger ERR = LoggerFactory.getLogger(""); // Root Logger

    /**
     * The class name
     */
    @CommandLine.Parameters(
            index = "0",
            arity = "0..1",
            description = "The name of the class to instantiate (must have no-args constructor)",
            defaultValue = "java.lang.String",
            showDefaultValue = CommandLine.Help.Visibility.ALWAYS
    )
    private String className;

    /**
     * Bootstrap the command
     *
     * @param args the command line args
     */
    public static void main(final String... args) {
        val status = new CommandLine(new Reflect()).setTrimQuotes(true).execute(args);
        Runtime.getRuntime().halt(status);
    }

    /**
     * Entrypoint to the command
     *
     * @return the program exit code
     */
    @Override
    public Integer call() {
        try {
            Class.forName(className).getConstructor().newInstance();
            log.error("Successfully instantiated class {} with reflection", className);
        } catch (final Exception e) {
            log.error("Error instantiating class {} with reflection", className);
            ERR.error("Try adding the class to reflect-config.json");
            return CommandLine.ExitCode.SOFTWARE;
        }
        return CommandLine.ExitCode.OK;
    }

}
