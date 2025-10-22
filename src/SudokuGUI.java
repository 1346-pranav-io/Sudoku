import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuGUI extends JFrame {
    private JTextField[][] cells = new JTextField[9][9];
    private int[][] solvedBoard;
    private int[][] puzzleBoard;

    public SudokuGUI() {
        setTitle("Sudoku Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        solvedBoard = SudokuGenerator.generateSolvedBoard();
        puzzleBoard = SudokuGenerator.createPuzzle(solvedBoard, "MEDIUM");

        JPanel gridPanel = new JPanel(new GridLayout(9, 9));
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(new Font("Arial", Font.BOLD, 20));
                if (puzzleBoard[row][col] != 0) {
                    cell.setText(String.valueOf(puzzleBoard[row][col]));
                    cell.setEditable(false);
                    cell.setBackground(Color.LIGHT_GRAY);
                }
                cells[row][col] = cell;
                gridPanel.add(cell);
            }
        }

        JButton checkBtn = new JButton("Check");
        checkBtn.addActionListener(e -> checkSolution());

        JButton solveBtn = new JButton("Solve");
        solveBtn.addActionListener(e -> solvePuzzle());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(checkBtn);
        bottomPanel.add(solveBtn);

        add(gridPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Check the current input against solved board
    private void checkSolution() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String text = cells[row][col].getText();
                int val = text.isEmpty() ? 0 : Integer.parseInt(text);
                if (val != solvedBoard[row][col]) {
                    JOptionPane.showMessageDialog(this, "Incorrect solution! Keep trying.");
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(this, "🎉 Congratulations! You solved it!");
    }

    // Fill all cells with solution
    private void solvePuzzle() {
        for (int row = 0; row < 9; row++)
            for (int col = 0; col < 9; col++)
                cells[row][col].setText(String.valueOf(solvedBoard[row][col]));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuGUI::new);
    }
}
