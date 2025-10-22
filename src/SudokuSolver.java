public class SudokuSolver {

    // Check if placing num at board[row][col] is valid
    public static boolean isSafe(int[][] board, int row, int col, int num) {
        // Row and column check
        for (int d = 0; d < 9; d++) {
            if (board[row][d] == num || board[d][col] == num)
                return false;
        }

        // 3x3 box check
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;
        for (int r = boxRowStart; r < boxRowStart + 3; r++) {
            for (int c = boxColStart; c < boxColStart + 3; c++) {
                if (board[r][c] == num)
                    return false;
            }
        }

        return true;
    }

    // Full backtracking Sudoku solver
    public static boolean solve(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) return true;
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // Utility: Copy a Sudoku board
    public static int[][] copyBoard(int[][] b) {
        int[][] c = new int[9][9];
        for (int i = 0; i < 9; i++)
            System.arraycopy(b[i], 0, c[i], 0, 9);
        return c;
    }

    // Utility: Print Sudoku board nicely
    public static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0)
                System.out.println("------+-------+------");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0)
                    System.out.print("| ");
                System.out.print(board[i][j] == 0 ? ". " : board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
    