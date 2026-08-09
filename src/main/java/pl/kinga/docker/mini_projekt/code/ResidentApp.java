import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResidentApp {
    private final String URL = "jdbc:postgresql://db:5432/residents";
    private final String USER = "resuser";
    private final String PASSWORD = "respass123";
    private Connection conn;

    public ResidentApp() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    }

    public List<String> findAll() {
        List<String> allResidents = new ArrayList<>();
        String sql = "SELECT * FROM resident";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                long id = rs.getLong("id");
                String fullName = rs.getString("full_name");
                String city = rs.getString("city");
                int birthYear = rs.getInt("birth_year");
                allResidents.add(id + " | " + fullName + " | " + city + " | " + birthYear);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }

        return allResidents;
    }

    public List<String> findByCity(String city) {
        List<String> foundByCity = new ArrayList<>();
        String sql = "SELECT id, full_name, birth_year FROM resident WHERE city = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, city);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    String fullName = rs.getString("full_name");
                    int birthYear = rs.getInt("birth_year");
                    foundByCity.add(id + " | " + fullName + " | " + birthYear);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
        return foundByCity;
    }

    public List<String> countByCity() {
        List<String> countedByCity = new ArrayList<>();
        String sql = "SELECT city, COUNT(*) as count FROM resident GROUP BY city";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String city = rs.getString("city");
                long count = rs.getLong("count");
                countedByCity.add(city + " | " + count);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }

        return countedByCity;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(3000);  // czekaj az baza wystartuje

        ResidentApp app = new ResidentApp();

        System.out.println("=== Wszyscy mieszkancy ===");
        app.findAll().forEach(System.out::println);

        System.out.println("\n=== Mieszkancy Krakowa ===");
        app.findByCity("Kraków").forEach(System.out::println);

        System.out.println("\n=== Liczba mieszkancow per miasto ===");
        app.countByCity().forEach(System.out::println);
    }

}