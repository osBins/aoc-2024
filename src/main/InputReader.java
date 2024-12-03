package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InputReader {
    public static List<String> readInputFile(String file) throws IOException {
        return Files.readAllLines(Paths.get("src/resources/" + file));
    }
}
