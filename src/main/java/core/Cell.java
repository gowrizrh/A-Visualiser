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
}
