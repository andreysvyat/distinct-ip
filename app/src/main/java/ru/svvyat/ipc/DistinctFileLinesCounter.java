package ru.svvyat.ipc;

public interface DistinctFileLinesCounter<R> {
    R calculateDistinct(String fileName);
}
