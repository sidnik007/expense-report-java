package com.nelkinda.training;

import java.util.Date;
import java.util.List;

enum ExpenseType {
    DINNER("Dinner", true),
    BREAKFAST("Breakfast", true),
    CAR_RENTAL("Car Rental", false)
    ;
    final String name;
    final Boolean isMeal;

    ExpenseType(final String name, final Boolean isMeal) {
        this.name = name;
        this.isMeal = isMeal;
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
        return type == ExpenseType.DINNER && amount > 5000 || type == ExpenseType.BREAKFAST && amount > 1000;
    }
}

public class ExpenseReport {
    public void printReport(List<Expense> expenses) {
        printReport(expenses, new Date());
    }

    void printReport(final List<Expense> expenses, final Date date) {
        int total = 0;
        int mealExpenses = 0;

        System.out.println("Expenses " + date);

        for (Expense expense : expenses) {
            if (expense.isMeal()) {
                mealExpenses += expense.amount;
            }

            String expenseName = expense.getName();

            String mealOverExpensesMarker = expense.isOverMarker() ? "X" : " ";

            System.out.println(expenseName + "\t" + expense.amount + "\t" + mealOverExpensesMarker);

            total += expense.amount;
        }

        System.out.println("Meal expenses: " + mealExpenses);
        System.out.println("Total expenses: " + total);
    }

}
