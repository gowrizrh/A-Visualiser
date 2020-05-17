import core.Cell;
import core.Map;

import java.util.*;

public class Starvis {
    private Cell start;
    private Cell goal;
    private Map map;

    public int heuristic(Cell c) {
        int dx = Math.abs(c.x() - goal.x());
        int dy = Math.abs(c.y() - goal.y());

        return dx + dy;
    }

    public ArrayList<Cell> find(Cell _start, Cell _goal, Map _map) {
        start = _start;
        goal = _goal;
        map = _map;

        if (map.value(start) == 1) return null;
        Queue<Cell> open = new PriorityQueue<>();
        HashSet<Cell> closed = new HashSet<Cell>();
        start.g(0); start.f(heuristic(start)); open.add(start);

        Cell current = null;
        while (!open.isEmpty()) {
            current = open.poll();
            closed.add(current);

            if (current == goal) {
                System.out.println("Path found!");
                ArrayList<Cell> paths = new ArrayList<>();
                while (current.parent() != null) {
                    paths.add(current);
                    current = current.parent();
                }
                return paths;
            }

            for (Cell n : map.nodes(current)) {
                if (map.value(n) == 1 || closed.contains(n)) continue;

                int G = current.g() + 1;

                if (G < n.g() || !open.contains(n)) {
                    int F = G + heuristic(n);
                    n.g(G);
                    n.f(F);
                    n.parent(current);

                    if (!open.contains(n))
                        open.add(n);
                }
            }
        }

        return null;
    }

    public Map map() {
        return map;
    }

    public void map(Map _map) {
        map = _map;
    }
}
