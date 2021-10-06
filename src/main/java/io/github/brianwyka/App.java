package io.github.brianwyka;

import io.github.brianwyka.command.HelloWorld;
import io.github.brianwyka.command.Reflect;
import lombok.val;
import picocli.CommandLine;

/**
 * Command line entrypoint
 *
 * @author brianwyka
 */
@CommandLine.Command(
        name = "app",
        description = "CLI Application",
        aliases = {"app"},
        header = "APP",
        footer = "(c) Brian Wyka",
        mixinStandardHelpOptions = true,
        subcommands = {
                CommandLine.HelpCommand.class,
                HelloWorld.class,
                Reflect.class
        }
)
public class App {

    /**
     * Bootstrap the command
     *
     * @param args the command line args
     */
    public static void main(final String[] args) {
        val defaultColorScheme = CommandLine.Help.defaultColorScheme(CommandLine.Help.Ansi.AUTO);
        val colorScheme = new CommandLine.Help.ColorScheme.Builder(defaultColorScheme)
                .commands(CommandLine.Help.Ansi.Style.bold, CommandLine.Help.Ansi.Style.fg_cyan)
                .build();
        val status = new CommandLine(new App())
                .setColorScheme(colorScheme)
                .execute(args);
        Runtime.getRuntime().halt(status);
    }

}
