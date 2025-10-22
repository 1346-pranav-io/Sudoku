import java.util.*;

public class SudokuGenerator {
    private static final Random rand = new Random();

    // Generate a complete solved Sudoku board
    public static int[][] generateSolvedBoard() {
        int[][] board = new int[9][9];
        fillDiagonalBoxes(board);
        fillRemaining(board, 0, 3);
        return board;
    }

    // Fill diagonal 3x3 boxes (independent)
    private static void fillDiagonalBoxes(int[][] board) {
        for (int i = 0; i < 9; i += 3) fillBox(board, i, i);
    }

    private static void fillBox(int[][] board, int row, int col) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= 9; i++) nums.add(i);
        Collections.shuffle(nums);
        int idx = 0;
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                board[row + r][col + c] = nums.get(idx++);
    }

    // Fill remaining cells using backtracking
    private static boolean fillRemaining(int[][] board, int i, int j) {
        if (j >= 9) {
            j = 0;
            i++;
        }
        if (i >= 9) return true;

        if (board[i][j] != 0)
            return fillRemaining(board, i, j + 1);

        List<Integer> nums = new ArrayList<>();
        for (int n = 1; n <= 9; n++) nums.add(n);
        Collections.shuffle(nums);

        for (int num : nums) {
            if (SudokuSolver.isSafe(board, i, j, num)) {
                board[i][j] = num;
                if (fillRemaining(board, i, j + 1)) return true;
                board[i][j] = 0;
            }
        }
        return false;
    }

    // Create a Sudoku puzzle with difficulty
    public static int[][] createPuzzle(int[][] solved, String difficulty) {
        int[][] puzzle = SudokuSolver.copyBoard(solved);
        int removals = switch (difficulty.toUpperCase()) {
            case "EASY" -> 30;
            case "MEDIUM" -> 40;
            case "HARD" -> 50;
            default -> 40;
        };

        int removed = 0;
        while (removed < removals) {
            int r = rand.nextInt(9), c = rand.nextInt(9);
            if (puzzle[r][c] != 0) {
                puzzle[r][c] = 0;
                removed++;
            }
        }
        return puzzle;
    }

    // Test generator
    public static void main(String[] args) {
        int[][] solved = generateSolvedBoard();
        System.out.println("✅ Solved Sudoku:");
        SudokuSolver.printBoard(solved);

        System.out.println("\n🎯 Puzzle (Medium Difficulty):");
        int[][] puzzle = createPuzzle(solved, "MEDIUM");
        SudokuSolver.printBoard(puzzle);
    }
}
