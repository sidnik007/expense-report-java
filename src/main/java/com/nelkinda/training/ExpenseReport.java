package com.nelkinda.training;

import java.util.Date;
import java.util.List;

enum ExpenseType {
    DINNER, BREAKFAST, CAR_RENTAL
}

class Expense {
    ExpenseType type;
    int amount;

    public Expense(ExpenseType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    String getExpenseName() {
        String expenseName = "";
        switch (type) {
        case DINNER:
            expenseName = "Dinner";
            break;
        case BREAKFAST:
            expenseName = "Breakfast";
            break;
        case CAR_RENTAL:
            expenseName = "Car Rental";
            break;
        }
        return expenseName;
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
            if (expense.type == ExpenseType.DINNER || expense.type == ExpenseType.BREAKFAST) {
                mealExpenses += expense.amount;
            }

            String expenseName = expense.getExpenseName();

            String mealOverExpensesMarker = expense.type == ExpenseType.DINNER && expense.amount > 5000 || expense.type == ExpenseType.BREAKFAST && expense.amount > 1000 ? "X" : " ";

            System.out.println(expenseName + "\t" + expense.amount + "\t" + mealOverExpensesMarker);

            total += expense.amount;
        }

        System.out.println("Meal expenses: " + mealExpenses);
        System.out.println("Total expenses: " + total);
    }

}
