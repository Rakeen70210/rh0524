package org.example;

import org.example.Controller.CheckoutController;
import org.example.Service.InputValidationService;
import org.example.View.CheckoutView;


/**
 * Command line application
 */
public class Main {
    /**
     * Main Class serving as entry point for the application
     * @param args no arguments
     */
    public static void main(String[] args) {
        CheckoutView checkoutView = new CheckoutView();
        InputValidationService inputValidationService = new InputValidationService();
        CheckoutController checkoutController = new CheckoutController(checkoutView, inputValidationService);
        checkoutController.processCheckout();
    }
}