package com.nelkinda.training;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.nelkinda.training.ExpenseType.*;

public class ExpenseReportTest {

    @Test
    void printReportTest() {

        List<Expense> expenses = List.of(
                new Expense(DINNER, 1),
                new Expense(DINNER, 5000),
                new Expense(DINNER, 5001),
                new Expense(BREAKFAST, 2),
                new Expense(BREAKFAST, 1000),
                new Expense(BREAKFAST, 1001),
                new Expense(CAR_RENTAL, 4),
                new Expense(CAR_RENTAL, 800)
        );
        new ExpenseReport().printReport(expenses);
    }
}
