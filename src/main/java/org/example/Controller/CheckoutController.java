package org.example.Controller;

import java.time.LocalDate;

import org.example.Model.RentalAgreement;
import org.example.Service.CheckoutService;
import org.example.Service.InputValidationService;
import org.example.View.CheckoutView;

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

        processCheckout();
    }

    private void processCheckout() {
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
            if (inputValidationService.validateRentalDayCount(rentalDayCount)) {
                break;
            } else {
                checkoutView.displayRentalDayCountError();
            }
        }
        return rentalDayCount;
    }

    private int getValidatedDiscountPercent() {
        int discountPercent;
        while (true) {
            discountPercent = checkoutView.getDiscountPercent();
            if (inputValidationService.validateDiscountPercent(discountPercent)) {
                break;
            } else {
                checkoutView.displayDiscountPercentError();
            }
        }
        return discountPercent;
    }
}