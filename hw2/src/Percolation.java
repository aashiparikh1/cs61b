import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int openSites;
    private int dimension;
    private WeightedQuickUnionUF disjointSet;

    private WeightedQuickUnionUF disjointSetAvoidABackwash;
    private int topRow;

    private int topRowDSAB;
    private int bottomRow;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("Grid size must be greater than 0.");
        }
        dimension = N;
        openSites = 0;
        grid = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                grid[r][c] = false;
            }
        }
        disjointSet = new WeightedQuickUnionUF(N * N + 5);
        disjointSetAvoidABackwash = new WeightedQuickUnionUF(N * N + 5);
        topRow = 0;
        topRowDSAB = 0;
        bottomRow = N * N + 2;
    }

    public void open(int row, int col) {
        if (!validateRowAndColumn(row, col)) {
            throw new java.lang.IndexOutOfBoundsException("Invalid row or column index.");
        }
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = true;
        openSites++;
        int oneDPos = xyTo1D(row, col);
        // connects topRow items together
        if (row == topRow) {
            disjointSet.union(topRow, oneDPos);
        }
        // connects topRow items together
        if (row == topRowDSAB) {
            disjointSetAvoidABackwash.union(topRow, oneDPos);
        }
        // connects bottomRow items together
        if (row == dimension - 1) {
            disjointSet.union(bottomRow, oneDPos);
        }
        // connects to right item, if open
        if (col + 1 < dimension && isOpen(row, col + 1)) {
            disjointSet.union(oneDPos, oneDPos + 1);
        }
        // connects to right item, if open
        if (col + 1 < dimension && isOpen(row, col + 1)) {
            disjointSetAvoidABackwash.union(oneDPos, oneDPos + 1);
        }
        // connects to left item, if open
        if (col - 1 >= 0 && isOpen(row, col - 1)) {
            disjointSet.union(oneDPos, oneDPos - 1);
        }
        // connects to left item, if open
        if (col - 1 >= 0 && isOpen(row, col - 1)) {
            disjointSetAvoidABackwash.union(oneDPos, oneDPos - 1);
        }
        // connects to bottom item, if open
        if (row + 1 < dimension && isOpen(row + 1, col)) {
            disjointSet.union(oneDPos, oneDPos + dimension);
        }
        // connects to bottom item, if open
        if (row + 1 < dimension && isOpen(row + 1, col)) {
            disjointSetAvoidABackwash.union(oneDPos, oneDPos + dimension);
        }
        // connects to top item, if open
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
            disjointSet.union(oneDPos, oneDPos - dimension);
        }
        // connects to top item, if open
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
            disjointSetAvoidABackwash.union(oneDPos, oneDPos - dimension);
        }
    }

    public boolean isOpen(int row, int col) {
        if (!validateRowAndColumn(row, col)) {
            throw new java.lang.IndexOutOfBoundsException("Invalid row or column index.");
        }
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        if (!validateRowAndColumn(row, col)) {
            throw new java.lang.IndexOutOfBoundsException("Invalid row or column index.");
        }
        if (disjointSetAvoidABackwash.connected(topRow, xyTo1D(row, col)) && isOpen(row, col)) {
            return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        if (dimension == 1 && isOpen(0, 0)) {
            return true;
        } else if (dimension == 1) {
            return false;
        } else if (disjointSet.connected(topRow, bottomRow)) {
            return true;
        }
        return false;
    }

    public int xyTo1D(int r, int c) {
        if (!validateRowAndColumn(r, c)) {
            throw new java.lang.IllegalArgumentException("Invalid row or column index.");
        }
        return c + (r * dimension);
    }

    public boolean validateRowAndColumn(int r, int c) {
        if ((r >= 0 && r < dimension) && (c >= 0 && c < dimension)) {
            return true;
        }
        return false;
    }
}
