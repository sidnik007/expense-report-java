package com.nelkinda.training;

import java.util.Date;
import java.util.List;

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
            String expenseName = expense.getName();
            String mealOverExpensesMarker = expense.isOverMarker() ? "X" : " ";
            System.out.println(expenseName + "\t" + expense.amount + "\t" + mealOverExpensesMarker);
        }

        System.out.println("Meal expenses: " + sumMeal(expenses));
        System.out.println("Total expenses: " + sumTotal(expenses));
    }

    private int sumMeal(final List<Expense> expenses) {
        int mealExpenses = 0;
        for (Expense expense : expenses) {
            if (expense.isMeal()) {
                mealExpenses += expense.amount;
            }
        }
        return mealExpenses;
    }

    private int sumTotal(final List<Expense> expenses) {
        int total = 0;
        for (Expense expense : expenses) {
            total += expense.amount;
        }
        return total;
    }

}
