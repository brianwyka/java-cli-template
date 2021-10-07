package io.github.brianwyka.command;

import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import picocli.CommandLine;

/**
 * A command which is used for printing Hello World messages
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
        if ("-".equals(name)) {
            try (val scanner = new Scanner(System.in)) {
                while (scanner.hasNext()) {
                    log.info("Hello {}!", scanner.nextLine());
                }
            }
        } else if (name != null && !name.isBlank()) {
            log.info("Hello {}!", name);
        } else {
            log.info("Hello World!");
        }
        return CommandLine.ExitCode.OK;
    }

}
