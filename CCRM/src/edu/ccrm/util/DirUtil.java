package edu.ccrm.util;

import java.io.IOException;
import java.nio.file.*;

public class DirUtil {
    public static long getTotalSize(Path dir) throws IOException {
        long totalSize = 0;
        try (var paths = Files.walk(dir)) {
            for (Path path : (Iterable<Path>) paths::iterator) {
                if (Files.isRegularFile(path)) {
                    totalSize += Files.size(path);
                }
            }
        }
        return totalSize;
    }
}
