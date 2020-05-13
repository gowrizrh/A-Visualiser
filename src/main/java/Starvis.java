import core.Cell;
import core.Map;

public class Starvis {
    private Cell start;
    private Cell goal;
    private Map map;

    public void find(Cell _start, Cell _goal, Map _map) {
        start = _start;
        goal = _goal;
        map = _map;
    }

    public Map map() {
        return map;
    }

    public void map(Map _map) {
        map = _map;
    }
}
