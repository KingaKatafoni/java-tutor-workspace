package pl.kinga.bazydanych_sql.lekcja9_8.ex;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InsuranceRepository {
    private static final String URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private Connection connection;


    public InsuranceRepository() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            try (Statement stmt = connection.createStatement()) {

                stmt.executeUpdate(
                        """
                                
                                     CREATE TABLE policy (
                                    id BIGINT PRIMARY KEY,
                                    customer_name VARCHAR(200) NOT NULL,
                                    pesel VARCHAR(11) NOT NULL,
                                    policy_type VARCHAR(30) NOT NULL,
                                    premium DECIMAL(10,2) NOT NULL,
                              
                                   start_date DATE NOT NULL,
                                    is_active BOOLEAN NOT NULL DEFAULT TRUE
                                )
                                
                                """
                );

                stmt.
                        executeUpdate(
                                """
                                        
                                             INSERT INTO policy VALUES
                                        (1, 'Anna Nowak',
                                             '90010112345', 'CAR', 150.00, '2024-01-15'
                                                     (2, 'Jan Kowalski', '85050567890', 'HOME', 89.99, '2024-03-01', TRUE)
                                              (3, 'Maria Wisniewska', '95121298765', 'TRAVEL', 45.00, '2024-06-10', FAL
                                                 (4, 'Piotr Zielinski', '78030334567', 'LIFE', 200.00, '2023-11-20', T
                                                  (5,
                                          'Katarzyna Wojcik', '01082256789', 'CAR', 180.00, '2025-01-05', TRUE)
                                        """
                        );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    }

    public List<String> findAll() {
        List<String> allValues = new ArrayList<>();
        String sql = "SELECT id, customer_name, policy_type, premium FROM policy";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                long id = rs.getLong("id");
                String customerName = rs.getString("customer_name");
                String policyType = rs.getString("policy_type");
                BigDecimal premium = rs.getBigDecimal("premium");
                allValues.add(id + " | " + customerName + " | " + policyType + " | " + premium);

            }

        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }

        return allValues;
    }

    public List<String> findByPolicyType(String policyType) {
        List<String> foundByPolicyType = new ArrayList<>();
        String sql = "SELECT id, customer_name, policy_type, premium FROM policy WHERE policy_type = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, policyType);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String customerName = rs.getString("customer_name");
                    BigDecimal premium = rs.getBigDecimal("premium");
                    foundByPolicyType.add(id + " | " + customerName + " | " + policyType + " | " + premium);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }

        return foundByPolicyType;
    }

    public int addPolicy(long policyId, String customerName, String pesel, String policyType, BigDecimal premium) {
        int numbersOfAddedRows = 0;

        String sql = "INSERT INTO policy (id, customer_name, pesel, policy_type, premium, start_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, policyId);
            pstmt.setString(2, customerName);
            pstmt.setString(3, pesel);
            pstmt.setString(4, policyType);
            pstmt.setBigDecimal(5, premium);
            pstmt.setDate(6, Date.valueOf(LocalDate.now()));

            numbersOfAddedRows = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }

        return numbersOfAddedRows;
    }


    public int updatePremium(long policyId, BigDecimal newPremium) {
        int updatedRows = 0;
        String sql = "UPDATE policy SET premium = ? WHERE id = ?";


        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setBigDecimal(1, newPremium);
            pstmt.setLong(2, policyId);
            updatedRows = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }

        return updatedRows;
    }

    public int deactivatePolicy(long policyId) {
        String sql = "UPDATE policy SET is_active = ? WHERE id = ?";
        int deactivatedRows = 0;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setBoolean(1, false);
            pstmt.setLong(2, policyId);
            deactivatedRows = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }

        return deactivatedRows;
    }

    public static void main(String[] args) {
        InsuranceRepository insuranceRepository = new InsuranceRepository();

        System.out.println("---All values---");
        List<String> all = insuranceRepository.findAll();
        all.forEach(System.out::println);

        System.out.println();
        System.out.println("---Found by policy type CAR---");
        List<String> allPolicyType = insuranceRepository.findByPolicyType("CAR");
        allPolicyType.forEach(System.out::println);

        System.out.println("---Added policy---");
        int i = insuranceRepository.addPolicy(6, "Kinga Binga", "93021234567", "LIVE", new BigDecimal("230.00"));
        System.out.println(i);

        System.out.println("---Updated policy---");
        System.out.println(insuranceRepository.updatePremium(2, new BigDecimal("234.99")));

        System.out.println("---Deactivated policy---");
        System.out.println(insuranceRepository.deactivatePolicy(2));

    }
}
