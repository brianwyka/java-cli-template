package io.brianwyka;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "OUT")
public class Main {

    public static void main(final String[] args) {
        if (args.length == 0) {
            log.info("Hello World!");
            return;
        }
        log.info("Hello {}!", String.join(", ", args));
    }

}
