package core;

public class Cell {
    private int x;
    private int y;
    private int val;
    private int f = Integer.MAX_VALUE;
    private int g = Integer.MAX_VALUE;

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

    @Override
    public String toString() {
        return "Cell(" + x + ", " + y + ")";
    }
}
