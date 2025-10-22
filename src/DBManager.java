import java.sql.*;

public class DBManager {
    private static final String URL = "jdbc:mysql://localhost:3306/sudoku_db";
    private static final String USER = "root";
    private static final String PASS = "Pranav@1346";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void saveHighScore(int userId, int timeSeconds, String difficulty) {
        String sql = "INSERT INTO high_scores (user_id, time_seconds, difficulty) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, timeSeconds);
            ps.setString(3, difficulty);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
