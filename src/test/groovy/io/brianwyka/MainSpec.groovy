package io.brianwyka

import spock.lang.Specification
import spock.lang.Subject

import java.nio.charset.StandardCharsets

class MainSpec extends Specification {

    @Subject
    Main main = new Main()

    def "default"() {
        expect:
        main
    }

    def "main - no args"() {
        given:
        PrintStream systemOut = System.out
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
        System.out = new PrintStream(byteArrayOutputStream)

        when:
        Main.main()

        then:
        String console = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8).trim()

        and:
        console == "Hello World!"

        cleanup:
        System.out == systemOut
    }

    def "main - args"() {
        given:
        PrintStream systemOut = System.out
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
        System.out = new PrintStream(byteArrayOutputStream)

        when:
        Main.main("Brian")

        then:
        String console = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8).trim()

        and:
        console == "Hello Brian!"

        cleanup:
        System.out == systemOut
    }

}
