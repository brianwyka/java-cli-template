package io.github.brianwyka.command;

import java.util.Scanner;
import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

/**
 * A command which is used for printing Hello World messages
 *
 * @author brianwyka
 */
@CommandLine.Command(
        name = "hello-world",
        description = "Print out Hello World",
        mixinStandardHelpOptions = true
)
public class HelloWorld implements Callable<Integer> {

    private static final Logger OUT = LoggerFactory.getLogger("OUT");

    /**
     * The person's name
     */
    @CommandLine.Parameters(
            index = "0",
            arity = "0..1",
            description = "The name of the person to say hello to, specify '-' to use stdin",
            showDefaultValue = CommandLine.Help.Visibility.ALWAYS
    )
    private String name;

    /**
     * Bootstrap the command
     *
     * @param args the command line args
     */
    public static void main(final String... args) {
        final var status = new CommandLine(new HelloWorld()).setTrimQuotes(true).execute(args);
        Runtime.getRuntime().halt(status);
    }

    /**
     * Entrypoint to the command
     *
     * @return the program exit code
     */
    @Override
    public Integer call() {
        if ("-".equals(name)) {
            try (final var scanner = new Scanner(System.in)) {
                while (scanner.hasNext()) {
                    OUT.info("Hello {}!", scanner.nextLine());
                }
            }
        } else if (name != null && !name.isBlank()) {
            OUT.info("Hello {}!", name);
        } else {
            OUT.info("Hello World!");
        }
        return CommandLine.ExitCode.OK;
    }

}
