import core.Map;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class MapTest {

    @Test
    public void testGrids() {
        Map myMap = new Map(10, 10);
        Path filePath = FileSystems.getDefault().getPath("src/main/resources/grid1.txt");
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
    }
}
