package core;

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

    public int getRows() {
        return grid.length;
    }

	public int getColumns() {
        return grid[0].length;
    }

    public int getValueAt(int row, int col) {
        return grid[row][col].val();
    }

    public String toString() {
        StringBuilder repr = new StringBuilder();
        for(int r = 0; r < getRows(); r++) {
            for(int c = 0; c < getColumns(); c++) {
                repr.append(String.format("%d ", getValueAt(r, c)));
            }
            repr.append("\n");
        }
        return repr.toString();
    }

    public void setValueAt(int row, int col, int value) {
        grid[row][col].val(value);
    }
}
