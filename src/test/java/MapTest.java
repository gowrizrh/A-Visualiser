import core.Cell;
import core.Map;
import org.junit.Assert;
import org.junit.Test;
import utils.MapParser;

import java.util.List;

public class MapTest {

    @Test
    public void parserTest() {
        MapParser parser = new MapParser();
        Map map1 = parser.parse("src/main/resources/grid1.txt");

        Assert.assertEquals(10, map1.x());
        Assert.assertEquals(10, map1.y());
        Assert.assertEquals(0, map1.value(0, 0));
        Assert.assertEquals(1, map1.value(0, 2));
    }

    @Test
    public void adjacentNodes() {
        MapParser parser = new MapParser();
        Map map1 = parser.parse("src/main/resources/grid1.txt");
        Cell c1 = map1.cell(5, 6);
        Cell c2 = map1.cell(5, 8);
        Cell c3 = map1.cell(4, 7);
        Cell c4 = map1.cell(6, 7);

        List<Cell> neighbours = map1.nodes(new Cell(5, 7));

        Assert.assertSame("Cells should be same for performance reasons", c1, neighbours.get(0));
        Assert.assertSame("Cells should be same for performance reasons", c2, neighbours.get(1));
        Assert.assertSame("Cells should be same for performance reasons", c3, neighbours.get(2));
        Assert.assertSame("Cells should be same for performance reasons", c4, neighbours.get(3));
    }
}
