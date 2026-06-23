package pl.kinga.funkcyjnajava.lekcja5_7.publicsector;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class BudgetAnalyzer {
    public static void main(String[] args) {
        List<BudgetItem> budgetItems = List.of(
                new BudgetItem("BUDG/001", "SALARY", "public", new BigDecimal("345.99"), "Q1", true),
                new BudgetItem("BUDG/002", "EQUIPMENT", "governmental", new BigDecimal("1345.99"), "Q2", true),
                new BudgetItem("BUDG/003", "TRAINING", "social", new BigDecimal("22345.99"), "Q3", true),
                new BudgetItem("BUDG/004", "TRAVEL", "public", new BigDecimal("45.99"), "Q4", true),
                new BudgetItem("BUDG/005", "SALARY", "public", new BigDecimal("345.99"), "Q1", false),
                new BudgetItem("BUDG/006", "EQUIPMENT", "governmental", new BigDecimal("1345.99"), "Q2", false),
                new BudgetItem("BUDG/007", "TRAINING", "social", new BigDecimal("12345.99"), "Q3", false),
                new BudgetItem("BUDG/008", "TRAVEL", "public", new BigDecimal("245.99"), "Q4", false),
                new BudgetItem("BUDG/009", "SALARY", "public", new BigDecimal("545.99"), "Q1", true),
                new BudgetItem("BUDG/010", "EQUIPMENT", "governmental", new BigDecimal("43345.99"), "Q2", true),
                new BudgetItem("BUDG/011", "TRAINING", "social", new BigDecimal("2345.99"), "Q3", true),
                new BudgetItem("BUDG/012", "TRAVEL", "public", new BigDecimal("5.99"), "Q4", true),
                new BudgetItem("BUDG/013", "TRAVEL", "public", new BigDecimal("245.99"), "Q4", false),
                new BudgetItem("BUDG/014", "TRAINING", "social", new BigDecimal("3445.99"), "Q2", true)
        );

        System.out.println("----Total approved budget----");
        BigDecimal totalApprovedBudget = budgetItems.stream()
                .filter(BudgetItem::approved)
                .map(BudgetItem::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(totalApprovedBudget);

        System.out.println("----Total budget for training----");
        BigDecimal totalTrainingBudget = budgetItems.stream()
                .filter(i -> i.category().equals("TRAINING"))
                .map(BudgetItem::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(totalTrainingBudget);

        System.out.println("----Unapproved Count----");
        long unapprovedCount = budgetItems.stream()
                .filter(i -> !i.approved())
                .count();
        System.out.println(unapprovedCount);

        System.out.println("----Unique department names joined----");
        String joinedDepartments = budgetItems.stream()
                .map(BudgetItem::department)
                .distinct()
                .reduce((a, b) -> a + ", " + b)
                .orElse("brak");
        System.out.println(joinedDepartments);

        System.out.println("----Max Budget Item----");
        Optional<BudgetItem> maxBudgetItem = budgetItems.stream()
                .max(Comparator.comparing(BudgetItem::amount));
        maxBudgetItem.ifPresent(System.out::println);

        System.out.println("----Min budget Item----");
        Optional<BudgetItem> minApprovedBudgetItem = budgetItems.stream()
                .filter(BudgetItem::approved)
                .min(Comparator.comparing(BudgetItem::amount));
        minApprovedBudgetItem.ifPresent(System.out::println);

        System.out.println("----Total sum of budgets per quarter----");
        BigDecimal quarterQ1 = budgetItems.stream()
                .filter(i -> i.quarter().equals("Q1"))
                .map(BudgetItem::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal quarterQ2 = budgetItems.stream()
                .filter(i -> i.quarter().equals("Q2"))
                .map(BudgetItem::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal quarterQ3 = budgetItems.stream()
                .filter(i -> i.quarter().equals("Q3"))
                .map(BudgetItem::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal quarterQ4 = budgetItems.stream()
                .filter(i -> i.quarter().equals("Q4"))
                .map(BudgetItem::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Q1: " + quarterQ1 + " PLN");
        System.out.println("Q2: " + quarterQ2 + " PLN");
        System.out.println("Q3: " + quarterQ3 + " PLN");
        System.out.println("Q4: " + quarterQ4 + " PLN");
    }
}
