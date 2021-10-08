package io.github.brianwyka.command;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import picocli.CommandLine;

/**
 * A command which is used for adding integers
 *
 * @author brianwyka
 */
@Slf4j(topic = "OUT")
@CommandLine.Command(
        name = "add",
        description = "Print out Hello World",
        mixinStandardHelpOptions = true
)
public class Add implements Callable<Integer> {

    /**
     * The numbers to add
     */
    @CommandLine.Parameters(
            index = "0",
            arity = "0..10",
            description = "The numbers to add together",
            showDefaultValue = CommandLine.Help.Visibility.ALWAYS
    )
    private int[] numbers;

    /**
     * Bootstrap the command
     *
     * @param args the command line args
     */
    public static void main(final String... args) {
        val status = new CommandLine(new Add()).setTrimQuotes(true).execute(args);
        Runtime.getRuntime().halt(status);
    }

    /**
     * Entrypoint to the command
     *
     * @return the program exit code
     */
    @Override
    public Integer call() {
        val sum = IntStream.of(numbers)
            .sum();
        log.info("Sum: {}", sum);
        return CommandLine.ExitCode.OK;
    }

}
