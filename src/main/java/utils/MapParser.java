package utils;

import core.Map;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class MapParser {

    /**
     * Parses a txt file map which has the following format
     *
     * x x x x x
     * x x x x x
     * x x x x x
     *
     * The length of the map is determined by the text file.
     * We probably need some limit on this later
     */
    public Map parse(String path) {
        Path filePath = FileSystems.getDefault().getPath(path);
        List<String> lines = null;
        try {
            lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while( lines.size() > 0 && lines.get(0).trim().startsWith("#") ) {
            lines.remove(0);
        }
        int rows = lines.size();
        int cols = lines.get(0).trim().split("\\p{javaWhitespace}+").length;
        Map map = new Map(rows, cols);
        for (int r = 0; r < rows; r++) {
            Scanner scanner = new Scanner(lines.get(r));
            for (int c = 0; c < cols; c++) {
                map.setValueAt(r, c, scanner.nextInt());
            }
            scanner.close();
        }
        return map;
    }
}
