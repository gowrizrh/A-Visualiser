package core;

public class Cell implements Comparable<Cell> {
    private int x;
    private int y;
    private int val;
    private int f = Integer.MAX_VALUE;
    private int g = Integer.MAX_VALUE;
    private Cell parent = null;

    public Cell(int _x, int _y) {
        x = _x;
        y = _y;
        val = 0;
    }

    public int val() {
        return val;
    }

    public void val(int _val) {
        val = _val;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int f() {
        return f;
    }

    public void f(int _f) {
        f = _f;
    }

    public int g() {
        return g;
    }

    public void g(int _g) {
        g = _g;
    }

    public Cell parent() {
        return parent;
    }

    public void parent(Cell _parent) {
        parent = _parent;
    }

    @Override
    public String toString() {
        return "Cell(" + x + ", " + y + ")";
    }

    @Override
    public int compareTo(Cell o) {
        if (f > o.f)
            return 1;
        else if (f < o.f)
            return -1;

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        Cell other = (Cell) o;
        return this == o || other.y == y && other.x == x;
    }

    @Override
    public int hashCode() {
        final int prime = 7;
        int result = 1;
        result = result + prime * y;
        result = result + prime * x;
        return result;
    }
}
