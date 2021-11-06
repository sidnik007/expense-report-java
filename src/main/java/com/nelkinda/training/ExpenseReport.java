package com.nelkinda.training;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

enum ExpenseType {
    DINNER("Dinner", true, 5000),
    BREAKFAST("Breakfast", true, 1000),
    CAR_RENTAL("Car Rental", false, Integer.MAX_VALUE)
    ;
    final String name;
    final boolean isMeal;
    final int limit;

    ExpenseType(final String name, final boolean isMeal, final int limit) {
        this.name = name;
        this.isMeal = isMeal;
        this.limit = limit;
    }
}

class Expense {
    ExpenseType type;
    int amount;

    public Expense(ExpenseType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    String getName() {
        return type.name;
    }

    boolean isMeal() {
        return type.isMeal;
    }

    boolean isOverMarker() {
        return amount > type.limit;
    }
}

public class ExpenseReport {
    public void printReport(List<Expense> expenses) {
        printReport(expenses, new Date());
    }

    void printReport(final List<Expense> expenses, final Date date) {

        String report = generateReport(expenses, date);
        System.out.print(report);
    }

    private String generateReport(List<Expense> expenses, Date date) {
        return header(date) + body(expenses) + summary(expenses);
    }

    private String header(Date date) {
        return "Expenses " + date + "\n";
    }

    private String body(List<Expense> expenses) {
        return expenses.stream().map(this::detail).collect(Collectors.joining());
    }

    private String detail(Expense expense) {
        String mealOverExpensesMarker = expense.isOverMarker() ? "X" : " ";
        return expense.getName() + "\t" + expense.amount + "\t" + mealOverExpensesMarker + "\n";
    }

    private String summary(List<Expense> expenses) {
        return "Meal expenses: " + sumMeal(expenses) + "\n"
               + "Total expenses: " + sumTotal(expenses) + "\n";
    }

    private int sumMeal(final List<Expense> expenses) {
        return sum(expenses, Expense::isMeal);
    }

    private int sumTotal(final List<Expense> expenses) {
        return sum(expenses, e -> true);
    }

    private int sum(final List<Expense> expenses, final Predicate<Expense> predicate) {
        return expenses
                .stream()
                .filter(predicate)
                .mapToInt(e -> e.amount)
                .sum();
    }

}
