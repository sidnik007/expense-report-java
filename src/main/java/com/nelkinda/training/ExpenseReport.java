package com.nelkinda.training;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

enum ExpenseType {
    DINNER("Dinner", true, 5000),
    BREAKFAST("Breakfast", true, 1000),
    CAR_RENTAL("Car Rental", false, Integer.MAX_VALUE)
    ;
    final String name;
    final Boolean isMeal;
    final int limit;

    ExpenseType(final String name, final Boolean isMeal, final int limit) {
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

        System.out.println("Expenses " + date);

        for (Expense expense : expenses) {
            String mealOverExpensesMarker = expense.isOverMarker() ? "X" : " ";
            System.out.println(expense.getName() + "\t" + expense.amount + "\t" + mealOverExpensesMarker);
        }

        System.out.println("Meal expenses: " + sumMeal(expenses));
        System.out.println("Total expenses: " + sumTotal(expenses));
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
