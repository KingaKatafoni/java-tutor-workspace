package pl.kinga.bazydanych_sql.mini_project;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaxRepository {
    private static final String URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private Connection connection;

    public TaxRepository() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(
                        """
                                CREATE TABLE taxpayer (
                                    id BIGINT PRIMARY KEY,
                                    full_name VARCHAR(200) NOT NULL,
                                    nip VARCHAR(10) NOT NULL UNIQUE,
                                    city VARCHAR(100) NOT NULL,
                                    taxpayer_type VARCHAR(20) NOT NULL,
                                    is_active BOOLEAN NOT NULL DEFAULT TRUE
                                );
                                """
                );
                stmt.executeUpdate(
                        """
                                CREATE TABLE tax_declaration (
                                    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                    taxpayer_id BIGINT NOT NULL,
                                    tax_year INT NOT NULL,
                                    declaration_type VARCHAR(10) NOT NULL,
                                    income DECIMAL(12,2) NOT NULL,
                                    tax_amount DECIMAL(12,2) NOT NULL,
                                    status VARCHAR(20) NOT NULL DEFAULT 'DRAFT',
                                    submitted_at DATE
                                );
                                """
                );

                stmt.executeUpdate(
                        """
                                INSERT INTO taxpayer (id, full_name, nip, city, taxpayer_type, is_active) VALUES
                                (1, 'Anna Nowak', '1234567890', 'Kraków', 'INDIVIDUAL', TRUE),
                                (2, 'Jan Kowalski', '2345678901', 'Warszawa', 'INDIVIDUAL', TRUE),
                                (3, 'Tech Solutions Sp. z o.o.', '3456789012', 'Gdańsk', 'COMPANY', TRUE),
                                (4, 'Maria Wiśniewska', '4567890123', 'Kraków', 'INDIVIDUAL', FALSE),
                                (5, 'BuildCorp S.A.', '5678901234', 'Warszawa', 'COMPANY', TRUE),
                                (6, 'Piotr Zieliński', '6789012345', 'Poznań', 'INDIVIDUAL', TRUE),
                                (7, 'DataFlow Sp. z o.o.', '7890123456', 'Kraków', 'COMPANY', TRUE),
                                (8, 'Katarzyna Wójcik', '8901234567', 'Łódź', 'INDIVIDUAL', TRUE);
                                """
                );

                stmt.executeUpdate(
                        """
                                INSERT INTO tax_declaration (taxpayer_id, tax_year, declaration_type, income, tax_amount, status, submitted_at) VALUES
                                (1, 2023, 'PIT', 85000.00, 14450.00, 'ACCEPTED', '2024-04-15'),
                                (1, 2024, 'PIT', 92000.00, 15640.00, 'SUBMITTED', '2025-03-20'),
                                (2, 2023, 'PIT', 120000.00, 20400.00, 'ACCEPTED', '2024-04-10'),
                                (2, 2024, 'PIT', 135000.00, 22950.00, 'DRAFT', NULL),
                                (3, 2023, 'CIT', 450000.00, 85500.00, 'ACCEPTED', '2024-06-28'),
                                (3, 2024, 'CIT', 520000.00, 98800.00, 'SUBMITTED', '2025-06-15'),
                                (3, 2023, 'VAT', 450000.00, 103500.00, 'ACCEPTED', '2024-01-25'),
                                (3, 2024, 'VAT', 520000.00, 119600.00, 'SUBMITTED', '2025-01-20'),
                                (4, 2023, 'PIT', 65000.00, 11050.00, 'ACCEPTED', '2024-04-28'),
                                (5, 2023, 'CIT', 890000.00, 169100.00, 'ACCEPTED', '2024-06-30'),
                                (5, 2024, 'CIT', 950000.00, 180500.00, 'SUBMITTED', '2025-06-20'),
                                (5, 2023, 'VAT', 890000.00, 204700.00, 'ACCEPTED', '2024-01-30'),
                                (6, 2023, 'PIT', 55000.00, 9350.00, 'ACCEPTED', '2024-04-05'),
                                (6, 2024, 'PIT', 58000.00, 9860.00, 'SUBMITTED', '2025-04-01'),
                                (7, 2024, 'CIT', 280000.00, 53200.00, 'DRAFT', NULL),
                                (8, 2024, 'PIT', 72000.00, 12240.00, 'SUBMITTED', '2025-03-28');
                                """
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    }

    public List<String> findAllActiveTaxpayers() {
        List<String> allActiveTaxpayers = new ArrayList<>();
        String sql = "SELECT id, full_name, nip, city FROM taxpayer WHERE is_active = TRUE";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                long id = rs.getLong("id");
                String taxpayerName = rs.getString("full_name");
                String nip = rs.getString("nip");
                String city = rs.getString("city");
                allActiveTaxpayers.add(id + " | " + taxpayerName + " | " + nip + " | " + city);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }

        return allActiveTaxpayers;
    }

    public List<String> findDeclarationsByTaxpayerId(long taxpayerId) {
        List<String> declarationsById = new ArrayList<>();
        String sql = "SELECT td.id, t.full_name, td.declaration_type, td.tax_year, td.tax_amount, td.status\n" +
                "FROM TAX_DECLARATION td\n" +
                "  INNER JOIN TAXPAYER t ON t.id = td.taxpayer_id\n" +
                "WHERE td.taxpayer_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, taxpayerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    long declarationId = rs.getLong("id");
                    String fullName = rs.getString("full_name");
                    String type = rs.getString("declaration_type");
                    String year = rs.getString("tax_year");
                    BigDecimal taxAmount = rs.getBigDecimal("tax_amount");
                    String status = rs.getString("status");

                    declarationsById.add(
                            declarationId + " | "
                                    + fullName + " | "
                                    + type + " | "
                                    + year + " | "
                                    + taxAmount + " | "
                                    + status);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }

        return declarationsById;
    }

    public List<String> findTotalTaxByCity() {
        List<String> totalTaxByCity = new ArrayList<>();
        String sql = " SELECT t.CITY, sum(TAX_AMOUNT) as total_tax\n" +
                "      FROM TAXPAYER t\n" +
                "      INNER JOIN TAX_DECLARATION td ON t.ID = td.TAXPAYER_ID\n" +
                "      GROUP BY CITY\n" +
                "      ORDER BY total_tax DESC ;";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
                String city = rs.getString("city");
                BigDecimal totalTax = rs.getBigDecimal("total_tax");
                totalTaxByCity.add(city + " | " + totalTax);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
        return totalTaxByCity;
    }

    public int addDeclaration(long taxpayerId, int taxYear, String declarationType, BigDecimal income, BigDecimal taxAmount) {
        int addedDeclarationCount = 0;
        String sql = "INSERT INTO TAX_DECLARATION( taxpayer_id, tax_year, declaration_type, income, tax_amount, status, submitted_at)\n" +
                "     VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, taxpayerId);
            pstmt.setInt(2, taxYear);
            pstmt.setString(3, declarationType);
            pstmt.setBigDecimal(4, income);
            pstmt.setBigDecimal(5, taxAmount);
            pstmt.setString(6, "DRAFT");
            pstmt.setDate(7, null);
            addedDeclarationCount = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }

        return addedDeclarationCount;
    }

    public int submitDeclaration(long declarationId) {
        int submittedCount = 0;
        String sql = "UPDATE  TAX_DECLARATION\n" +
                "      SET STATUS = ?, SUBMITTED_AT = ?\n" +
                "      WHERE ID = ?;";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "SUBMITTED");
            pstmt.setDate(2, Date.valueOf(LocalDate.now()));
            pstmt.setLong(3, declarationId);
            submittedCount = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }

        return submittedCount;
    }

    public static void main(String[] args) {
        TaxRepository taxRepository = new TaxRepository();
        List<String> allActiveTaxpayers = taxRepository.findAllActiveTaxpayers();
        allActiveTaxpayers.forEach(System.out::println);

        taxRepository.findDeclarationsByTaxpayerId(1).forEach(System.out::println);

        taxRepository.findTotalTaxByCity().forEach(System.out::println);

        System.out.println("Added declaration: " + taxRepository.addDeclaration(3, 2026, "PIT", new BigDecimal("345678.99"), new BigDecimal("54677.9")));
        System.out.println("Submitted declaration: " + taxRepository.submitDeclaration(3));
    }
}
