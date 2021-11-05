package com.nelkinda.training;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
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
        PrintStream originalStdOut = System.out;
        ByteArrayOutputStream interceptedStdOutput = new ByteArrayOutputStream();
        Date now = new Date();
        System.setOut(new PrintStream(interceptedStdOutput));
        new ExpenseReport().printReport(expenses, now);
        final String actual = interceptedStdOutput.toString();
        final String expected = "Expenses " + now + "\n" +
                                "Dinner\t1\t \n" +
                                "Dinner\t5000\t \n" +
                                "Dinner\t5001\tX\n" +
                                "Breakfast\t2\t \n" +
                                "Breakfast\t1000\t \n" +
                                "Breakfast\t1001\tX\n" +
                                "Car Rental\t4\t \n" +
                                "Car Rental\t800\t \n" +
                                "Meal expenses: 12005\n" +
                                "Total expenses: 12809\n";
        Assertions.assertEquals(expected, actual);
        System.setOut(originalStdOut);
    }
}
