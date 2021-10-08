package io.github.brianwyka.command;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.Callable;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import picocli.CommandLine;
import picocli.CommandLine.ExitCode;

/**
 * A command which is used for adding integers
 *
 * @author brianwyka
 */
@Slf4j(topic = "OUT")
@CommandLine.Command(
        name = "http",
        description = "Make an HTTP GET Request",
        mixinStandardHelpOptions = true
)
public class Http implements Callable<Integer> {

    /**
     * The numbers to add
     */
    @CommandLine.Parameters(
            index = "0",
            arity = "0..1",
            description = "The URl to make an HTTP GET reques to",
            showDefaultValue = CommandLine.Help.Visibility.ALWAYS
    )
    private URL url;

    /**
     * Bootstrap the command
     *
     * @param args the command line args
     */
    public static void main(final String... args) {
        val status = new CommandLine(new Http()).setTrimQuotes(true).execute(args);
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
            HttpRequest httpRequest = HttpRequest.newBuilder(url.toURI())
                .GET()
                .build();
            HttpResponse<String> httpResponse = HttpClient.newHttpClient()
                .send(httpRequest, BodyHandlers.ofString());
            log.info("Http Response Code: {}", httpResponse.statusCode());
            log.info("-------------------------------------");
            log.info(httpResponse.body());
            return CommandLine.ExitCode.OK;
        } catch (final IOException | InterruptedException | URISyntaxException e) {
            log.error("Invalid URL or HTTP request: {}", url);
            return ExitCode.SOFTWARE;
        }
    }

}
