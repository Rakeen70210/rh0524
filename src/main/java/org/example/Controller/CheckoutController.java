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
     * @param checkoutView            the checkout view
     * @param inputValidationService   the input validation service
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
        while (true) {
            toolCode = checkoutView.getToolCode();
            if (inputValidationService.validateToolCode(toolCode)) {
                break;
            } else {
                checkoutView.displayToolCodeError();
            }
        }
        return toolCode;
    }

    private int getValidatedRentalDayCount() {
        int rentalDayCount;
        while (true) {
            rentalDayCount = checkoutView.getRentalDayCount();
            try {
                if (inputValidationService.validateRentalDayCount(rentalDayCount)) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                checkoutView.displayRentalDayCountError();
            }
        }
        return rentalDayCount;
    }

    private int getValidatedDiscountPercent() {
        int discountPercent;
        while (true) {
            discountPercent = checkoutView.getDiscountPercent();
            try {
                if (inputValidationService.validateDiscountPercent(discountPercent)) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                checkoutView.displayDiscountPercentError();
            }
        }
        return discountPercent;
    }
}