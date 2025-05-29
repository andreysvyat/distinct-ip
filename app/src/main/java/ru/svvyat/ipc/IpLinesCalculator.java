package ru.svvyat.ipc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class IpLinesCalculator implements DistinctFileLinesCalculator<Long> {
    private static final Pattern IP_REGEX = compile("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$");

    @Override
    public Long calculateDistinct(String fileName) {

        try (var reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(fileName))))) {
            return reader.lines().filter(l -> IP_REGEX.matcher(l).matches()).distinct().count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
