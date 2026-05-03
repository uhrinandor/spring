package com.spring.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;

class IntegrationTest {

    private static final String INPUT_DIR = ".";
    private static final String EXPECTED_DIR = "expected";
    private static final String OUTPUT_DIR = "target/output";

    @ParameterizedTest(name = "CLI test with: {0}")
    @MethodSource("inputFiles")
    void testAppWithInputFile(String fileName) throws Exception {
        Tracer.changeDeterministicMode(false);
        Entity.reset();

        // Read input
        String input = readResource(INPUT_DIR + "/" + fileName);

        ByteArrayInputStream inStream =
                new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        Tracer.changeStream(new PrintStream(outStream), inStream);

        // Run app
        App.main(new String[]{});

        String output = outStream.toString(StandardCharsets.UTF_8);

        // Save output for debugging
        writeOutput(fileName, output);

        // Load expected
        Path expectedPath = getResourcePath(EXPECTED_DIR + "/" + fileName);

        if (!Files.exists(expectedPath)) {
            fail("Missing expected output file: " + EXPECTED_DIR + "/" + fileName);
        }

        String expected = Files.readString(expectedPath, StandardCharsets.UTF_8);

        // Compare
        assertEquals(
                normalize(expected),
                normalize(output),
                "Output did not match expected for input file: " + fileName
        );
    }

    static Stream<String> inputFiles() throws IOException {
        Path dir = getResourcePath(INPUT_DIR);

        return Files.list(dir)
                .filter(p -> p.toString().endsWith(".txt"))
                .map(p -> p.getFileName().toString());
    }

    private static String readResource(String resource) throws IOException {
        Path path = getResourcePath(resource);
        return Files.readString(path, StandardCharsets.UTF_8);
    }

    private static Path getResourcePath(String resource) {
        try {
            URL url = IntegrationTest.class
                    .getClassLoader()
                    .getResource(resource);

            if (url == null) {
                throw new RuntimeException("Missing resource: " + resource);
            }

            return Paths.get(url.toURI());
        } catch (Exception e) {
            throw new RuntimeException("Failed to load resource: " + resource, e);
        }
    }

    private static void writeOutput(String fileName, String content) throws IOException {
        Path dir = Paths.get(OUTPUT_DIR);
        Files.createDirectories(dir);

        Files.writeString(dir.resolve(fileName), content, StandardCharsets.UTF_8);
    }

    private static String normalize(String s) {
        return s.trim().replace("\r\n", "\n");
    }
}