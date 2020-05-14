package core;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private Cell[][] grid;

    public Map(int rows, int cols) {
        if (rows <= 0 || cols <= 0) throw new IllegalArgumentException("Rows and columns must be > 0.");
        grid = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }
    }

    public int rows() {
        return grid.length;
    }

    public int cols() {
        return grid[0].length;
    }

    public int value(int row, int col) {
        return grid[row][col].val();
    }

    public String toString() {
        StringBuilder repr = new StringBuilder();
        for(int r = 0; r < rows(); r++) {
            for(int c = 0; c < cols(); c++) {
                repr.append(String.format("%d ", value(r, c)));
            }
            repr.append("\n");
        }
        return repr.toString();
    }

    public void value(int row, int col, int value) {
        grid[row][col].val(value);
    }

    public int value(Cell cell) {
        return grid[cell.x()][cell.y()].val();
    }

    public List<Cell> nodes(Cell c) {
        List<Cell> adjacentCells = new ArrayList<>();
        if (c.y() > 0) adjacentCells.add(grid[c.x()][c.y() - 1]);
        if (c.y() < cols()-1) adjacentCells.add(grid[c.x()][c.y() + 1]);
        if (c.x() > 0) adjacentCells.add(grid[c.x() - 1][c.y()]);
        if (c.x() < rows()-1) adjacentCells.add(grid[c.x() + 1][c.y()]);
        return adjacentCells;
    }

    /**
     * Testing only!
     */
    public Cell cell(int row, int col) {
        return grid[row][col];
    }
}
