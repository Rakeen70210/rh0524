package org.example.Controller;

import org.example.Controller.CheckoutController;
import org.example.Model.RentalAgreement;
import org.example.Service.CheckoutService;
import org.example.Service.InputValidationService;
import org.example.View.CheckoutView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CheckoutControllerTest {

    private CheckoutView checkoutView;
    private InputValidationService inputValidationService;
    private CheckoutController checkoutController;

    @BeforeEach
    public void setUp() {
        checkoutView = mock(CheckoutView.class);
        inputValidationService = mock(InputValidationService.class);
        checkoutController = new CheckoutController(checkoutView, inputValidationService);
    }

    @Test
    public void testProcessCheckout() {
        // Arrange
        String toolCode = "LADW";
        int rentalDayCount = 3;
        int discountPercent = 10;
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        RentalAgreement rentalAgreement = mock(RentalAgreement.class);

        when(checkoutView.getToolCode()).thenReturn(toolCode);
        when(inputValidationService.validateToolCode(toolCode)).thenReturn(true);

        when(checkoutView.getRentalDayCount()).thenReturn(rentalDayCount);
        when(inputValidationService.validateRentalDayCount(rentalDayCount)).thenReturn(true);

        when(checkoutView.getDiscountPercent()).thenReturn(discountPercent);
        when(inputValidationService.validateDiscountPercent(discountPercent)).thenReturn(true);

        when(checkoutView.getCheckoutDate()).thenReturn(checkoutDate);

        // Act
        checkoutController = new CheckoutController(checkoutView, inputValidationService);

        // Assert
        verify(checkoutView).closeScanner();
        ArgumentCaptor<RentalAgreement> captor = ArgumentCaptor.forClass(RentalAgreement.class);
        verify(checkoutView).displayRentalAgreement(captor.capture());
        assertEquals(rentalAgreement, captor.getValue());
    }
}