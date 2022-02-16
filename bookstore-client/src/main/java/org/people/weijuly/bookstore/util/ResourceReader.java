package org.people.weijuly.bookstore.util;


import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ResourceReader {

    public String read(String filename) throws IOException {
        return Optional
                .ofNullable(filename)
                .map(name -> ClassLoader.getSystemClassLoader().getResourceAsStream(name))
                .map(stream -> new InputStreamReader(stream, StandardCharsets.UTF_8))
                .map(BufferedReader::new)
                .map(reader -> reader.lines().collect(Collectors.joining(System.lineSeparator())))
                .orElseThrow(() -> new IOException(String.format("Cannot read file: %s", filename)));
    }
}
