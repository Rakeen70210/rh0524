package org.example.View;

import org.example.Model.RentalAgreement;
import org.example.Model.ToolCode;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Collects input from the user to determine which tool will be checked out
 * 
 * @author rakeen huq
 */
public class CheckoutView {

    private final Scanner scanner;

    /**
     * Constructs a new instance of the CheckoutView class with a new scanner
     *
     */
    public CheckoutView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Closes the scanner
     *
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Collects input from the user to determine which tool will be checked out
     *
     * @return the tool code
     */
    public String getToolCode() {
        System.out.println("Enter one of the following Tool Codes: ");
        for (ToolCode toolCode : ToolCode.values()) {
            System.out.println(toolCode);
        }
        // input validation
        while (true) {
            try {
                String toolCode = scanner.nextLine();
                if (toolCode.isEmpty()) {
                    throw new IllegalArgumentException("Please enter a non-empty string");
                }
                return toolCode;
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter a valid String.");
            }
        }
    }

    /**
     * Displays an error message when the user enters an invalid tool code
     *
     */
    public void displayToolCodeError() {
        System.out.println("Invalid Tool Code.");

    }

    /**
     * Collects input from the user to determine how many days the tool will be
     * checked out
     *
     * @return the rental day count
     */
    public int getRentalDayCount() {
        System.out.println("Rental Day Count: ");
        // input validation
        while (true) {
            try {
                int rentalDayCount = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                return rentalDayCount;
            } catch (InputMismatchException e) {
                System.out.println("Input is not an integer, please try again.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Displays an error message when the user enters an invalid rental day count
     *
     */
    public void displayRentalDayCountError() {
        throw new IllegalArgumentException("Invalid Rental Day Count. Please enter a number greater than 0.");
    }

    /**
     * Collects input from the user to determine the discount percentage
     *
     * @return the discount percentage
     */
    public int getDiscountPercent() {
        System.out.println("Discount Percent: ");
        // input validation
        while (true) {
            try {
                int discountPercent = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                return discountPercent;
            } catch (InputMismatchException e) {
                System.out.println("Input is not an integer, please try again.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Displays an error message when the user enters an invalid discount percentage
     *
     */
    public void displayDiscountPercentError() {
        throw new IllegalArgumentException("Invalid discount percent. Please enter a value between 0 and 100.");
    }

    /**
     * Collects input from the user to determine the check out date
     *
     * @return the check-out date
     */
    public LocalDate getCheckoutDate() {
        System.out.println("Check Out Date(MM/dd/yy): ");
        String checkOutDate = scanner.nextLine();
        // Parse the date string into a Date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        // Check Out Date validation
        while (true) {
            try {
                Date date = dateFormat.parse(checkOutDate);
                return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            } catch (ParseException | DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter a date in the format dd/mm/yyyy.");
                checkOutDate = scanner.nextLine();
            }
        }

    }

    /**
     * Displays the rental agreement
     *
     * @param rentalAgreement the rental agreement
     */
    public void displayRentalAgreement(RentalAgreement rentalAgreement) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");

        System.out.println("Tool Type: " + rentalAgreement.getToolType());
        System.out.println("Tool Brand: " + rentalAgreement.getToolBrand());
        System.out.println("Rental Days: " + rentalAgreement.getRentalDays());
        System.out.println("Checkout Date: " + rentalAgreement.getCheckoutDate().format(dateFormatter));
        System.out.println("Due Date: " + rentalAgreement.getDueDate().format(dateFormatter));
        System.out.println("Daily Rental Charge: " + currencyFormatter.format(rentalAgreement.getDailyRentalCharge()));
        System.out.println("Charge Days: " + rentalAgreement.getChargeDays());
        System.out.println("Pre-discount Charge: " + currencyFormatter.format(rentalAgreement.getPreDiscountCharge()));
        System.out.println("Discount Percent: " + rentalAgreement.getDiscountPercent() + "%");
        System.out.println("Discount Amount: " + currencyFormatter.format(rentalAgreement.getDiscountAmount()));
        System.out.println("Final Charge: " + currencyFormatter.format(rentalAgreement.getFinalCharge()));
    }

}
