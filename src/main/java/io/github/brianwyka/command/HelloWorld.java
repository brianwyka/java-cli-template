package io.github.brianwyka.command;

import java.util.concurrent.Callable;

import lombok.val;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

/**
 * A command which is used for monitoring HTTP access log files
 *
 * @author brianwyka
 */
@Slf4j(topic = "OUT")
@CommandLine.Command(
        name = "hello-world",
        description = "Print out Hello World",
        mixinStandardHelpOptions = true
)
public class HelloWorld implements Callable<Integer> {

    /**
     * The HTTP access log file path parameter
     */
    @CommandLine.Parameters(
            index = "0",
            arity = "0..1",
            description = "The name of the person to say hello to",
            showDefaultValue = CommandLine.Help.Visibility.ALWAYS
    )
    private String name;

    /**
     * Bootstrap the command
     *
     * @param args the command line args
     */
    public static void main(final String... args) {
        val status = new CommandLine(new HelloWorld()).setTrimQuotes(true).execute(args);
        Runtime.getRuntime().halt(status);
    }

    /**
     * Entrypoint to the command
     *
     * @return the program exit code
     */
    @Override
    public Integer call() {
        if (name != null && !name.isBlank()) {
            log.info("Hello {}!", name);
        } else {
            log.info("Hello World!");
        }
        return CommandLine.ExitCode.OK;
    }

}