package com.spring.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;

class IntegrationTest {

    private static final String INPUT_DIR = "";
    private static final String OUTPUT_DIR = "target/output";

    @ParameterizedTest(name = "CLI test with: {0}")
    @MethodSource("inputFiles")
    void testAppWithInputFile(String fileName) throws Exception {
        Entity.reset();
        // 📥 input betöltése
        String input = readResource(fileName);

        // stream-ek előkészítése
        ByteArrayInputStream inStream =
                new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        // 🔁 Tracer stream csere
        Tracer.changeStream(new PrintStream(outStream), inStream);

        // 🚀 APP FUTTATÁS
        App.main(new String[]{});

        // 📤 output kinyerése
        String output = outStream.toString(StandardCharsets.UTF_8);

        // fájlba írás
        writeOutput(fileName, output);

        // opcionális expected check
        Path expectedPath = getResourcePath("expected/" + fileName);
        if (Files.exists(expectedPath)) {
            String expected = Files.readString(expectedPath);
            assertEquals(normalize(expected), normalize(output));
        }
    }

    // 📂 paraméterek
    static Stream<String> inputFiles() throws IOException {
        Path dir = getResourcePath(INPUT_DIR);

        return Files.list(dir)
                .filter(p -> p.toString().endsWith(".txt"))
                .map(p -> p.getFileName().toString());
    }

    // 📥 resource olvasás
    private static String readResource(String path) throws IOException {
        return Files.readString(getResourcePath(path), StandardCharsets.UTF_8);
    }

    private static Path getResourcePath(String resource) {
        try {
            return Paths.get(
                    IntegrationTest.class
                            .getClassLoader()
                            .getResource(resource)
                            .toURI()
            );
        } catch (Exception e) {
            throw new RuntimeException("Missing resource: " + resource, e);
        }
    }

    // 📤 output írás
    private static void writeOutput(String fileName, String content) throws IOException {
        Path dir = Paths.get(OUTPUT_DIR);
        Files.createDirectories(dir);

        Files.writeString(dir.resolve(fileName), content, StandardCharsets.UTF_8);
    }

    private static String normalize(String s) {
        return s.trim().replace("\r\n", "\n");
    }
}