import core.Map;
import org.junit.Assert;
import org.junit.Test;
import utils.MapParser;

public class MapTest {

    @Test
    public void parserTest() {
        MapParser parser = new MapParser();
        Map map1 = parser.parse("src/main/resources/grid1.txt");

        Assert.assertEquals(10, map1.getRows());
        Assert.assertEquals(10, map1.getColumns());
        Assert.assertEquals(1, map1.getValueAt(0, 2));
    }
}
