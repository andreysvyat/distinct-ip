package ru.svvyat.ipc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class IpLinesCounter implements DistinctFileLinesCounter<Long> {
    private static final Pattern IP_REGEX = compile("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$");

    /**
     * Выбран тип лонг, так как нет смысла брать болешь.
     * Технически было бы достаточно и unsigned int, но в java такого нет
     * @param fileName
     * @return количество уникальных строк формата ip
     */
    @Override
    public Long calculateDistinct(String fileName) {

        try (var reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(fileName))))) {
            return reader.lines().filter(l -> IP_REGEX.matcher(l).matches()).distinct().count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
