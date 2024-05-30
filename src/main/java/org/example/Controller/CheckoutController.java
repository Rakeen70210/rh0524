package org.example.Controller;

import org.example.Model.RentalAgreement;
import org.example.Service.CheckoutService;
import org.example.Service.InputValidationService;
import org.example.View.CheckoutView;

import java.time.LocalDate;

/**
 * Controller class responsible for communicating between all other components
 *
 * @author rakeen huq
 */
public class CheckoutController {

    private final CheckoutView checkoutView;
    private final InputValidationService inputValidationService;

    /**
     * Constructor for the checkout controller
     *
     * @param checkoutView           the checkout view
     * @param inputValidationService the input validation service
     */
    public CheckoutController(CheckoutView checkoutView, InputValidationService inputValidationService) {
        this.checkoutView = checkoutView;
        this.inputValidationService = inputValidationService;
    }

    /**
     * Processes the checkout process
     */
    public void processCheckout() {
        String toolCode = getValidatedToolCode();
        int rentalDayCount = getValidatedRentalDayCount();
        int discountPercent = getValidatedDiscountPercent();
        LocalDate checkoutDate = checkoutView.getCheckoutDate();

        checkoutView.closeScanner();

        CheckoutService checkoutService = new CheckoutService(checkoutDate, rentalDayCount, toolCode, discountPercent);
        RentalAgreement rentalAgreement = checkoutService.getRentalAgreement();
        checkoutView.displayRentalAgreement(rentalAgreement);
    }

    private String getValidatedToolCode() {
        String toolCode;
        toolCode = checkoutView.getToolCode();
        if (inputValidationService.validateToolCode(toolCode)) {
            return toolCode;
        } else {
            throw new IllegalArgumentException("Invalid tool code");
        }

    }

    private int getValidatedRentalDayCount() {
        int rentalDayCount;
        rentalDayCount = checkoutView.getRentalDayCount();
        if (inputValidationService.validateRentalDayCount(rentalDayCount)) {
            return rentalDayCount;
        } else {
            throw new IllegalArgumentException("Invalid rental day count, must be greater than 0");
        }
    }

    private int getValidatedDiscountPercent() {
        int discountPercent;
        discountPercent = checkoutView.getDiscountPercent();

        if (inputValidationService.validateDiscountPercent(discountPercent)) {
            return discountPercent;
        } else {
            throw new IllegalArgumentException("Invalid discount percent, must be greater than 0 and less than 100");
        }


    }
}