package org.example.Controller;

import org.example.Model.RentalAgreement;
import org.example.Model.ToolBrand;
import org.example.Model.ToolCode;
import org.example.Model.ToolType;
import org.example.Service.CheckoutService;
import org.example.Service.InputValidationService;
import org.example.View.CheckoutView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CheckoutControllerTest {

    private CheckoutView checkoutView;
    private InputValidationService inputValidationService;
    private CheckoutService checkoutService;
    private CheckoutController checkoutController;

    @BeforeEach
    public void setUp() {
        checkoutView = mock(CheckoutView.class);
        inputValidationService = mock(InputValidationService.class);
        checkoutController = new CheckoutController(checkoutView, inputValidationService);
    }

    @Test
    public void testProcessCheckoutIntegration_Test1() {
        // Arrange
        String toolCode = "JAKR";
        LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
        int rentalDayCount = 5;
        int discountPercent = 101;

        when(checkoutView.getToolCode()).thenReturn(toolCode);
        when(inputValidationService.validateToolCode(toolCode)).thenReturn(true);

        when(checkoutView.getRentalDayCount()).thenReturn(rentalDayCount);
        when(inputValidationService.validateRentalDayCount(rentalDayCount)).thenReturn(true);

        when(checkoutView.getDiscountPercent()).thenReturn(discountPercent);
        when(inputValidationService.validateDiscountPercent(discountPercent)).thenReturn(false);

        when(checkoutView.getCheckoutDate()).thenReturn(checkoutDate);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        checkoutController.processCheckout();
        });

        // Assert

        assertEquals("Invalid discount percent, must be greater than 0 and less than 100", exception.getMessage());
    }

    @Test
    public void testProcessCheckoutIntegration_Test2() {
        // Arrange
        String toolCode = "LADW";
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        int rentalDayCount = 3;
        int discountPercent = 10;

        when(checkoutView.getToolCode()).thenReturn(toolCode);
        when(inputValidationService.validateToolCode(toolCode)).thenReturn(true);

        when(checkoutView.getRentalDayCount()).thenReturn(rentalDayCount);
        when(inputValidationService.validateRentalDayCount(rentalDayCount)).thenReturn(true);

        when(checkoutView.getDiscountPercent()).thenReturn(discountPercent);
        when(inputValidationService.validateDiscountPercent(discountPercent)).thenReturn(true);

        when(checkoutView.getCheckoutDate()).thenReturn(checkoutDate);

        // Act
        checkoutController.processCheckout();

        // Assert
        verify(checkoutView).closeScanner();
        ArgumentCaptor<RentalAgreement> captor = ArgumentCaptor.forClass(RentalAgreement.class);
        verify(checkoutView).displayRentalAgreement(captor.capture());

        RentalAgreement rentalAgreement = captor.getValue();
        assertEquals(ToolType.Ladder, rentalAgreement.getToolType());
        assertEquals(ToolBrand.Werner, rentalAgreement.getToolBrand());
        assertEquals(rentalDayCount, rentalAgreement.getRentalDays());
        assertEquals(discountPercent, rentalAgreement.getDiscountPercent());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
    }

    @Test
    public void testProcessCheckoutIntegration_Test3() {
        // Arrange
        String toolCode = "CHNS";
        LocalDate checkoutDate = LocalDate.of(2015, 7, 2);
        int rentalDayCount = 5;
        int discountPercent = 25;

        when(checkoutView.getToolCode()).thenReturn(toolCode);
        when(inputValidationService.validateToolCode(toolCode)).thenReturn(true);

        when(checkoutView.getRentalDayCount()).thenReturn(rentalDayCount);
        when(inputValidationService.validateRentalDayCount(rentalDayCount)).thenReturn(true);

        when(checkoutView.getDiscountPercent()).thenReturn(discountPercent);
        when(inputValidationService.validateDiscountPercent(discountPercent)).thenReturn(true);

        when(checkoutView.getCheckoutDate()).thenReturn(checkoutDate);

        // Act
        checkoutController.processCheckout();

        // Assert
        verify(checkoutView).closeScanner();
        // 
        ArgumentCaptor<RentalAgreement> captor = ArgumentCaptor.forClass(RentalAgreement.class);
        verify(checkoutView).displayRentalAgreement(captor.capture());

        RentalAgreement rentalAgreement = captor.getValue();
        assertEquals(ToolType.Chainsaw, rentalAgreement.getToolType());
        assertEquals(ToolBrand.Stihl, rentalAgreement.getToolBrand());
        assertEquals(rentalDayCount, rentalAgreement.getRentalDays());
        assertEquals(discountPercent, rentalAgreement.getDiscountPercent());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
    }

    @Test
    public void testProcessCheckoutIntegration_Test4() {
        // Arrange
        String toolCode = "JAKD";
        LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
        int rentalDayCount = 6;
        int discountPercent = 0;

        when(checkoutView.getToolCode()).thenReturn(toolCode);
        when(inputValidationService.validateToolCode(toolCode)).thenReturn(true);

        when(checkoutView.getRentalDayCount()).thenReturn(rentalDayCount);
        when(inputValidationService.validateRentalDayCount(rentalDayCount)).thenReturn(true);

        when(checkoutView.getDiscountPercent()).thenReturn(discountPercent);
        when(inputValidationService.validateDiscountPercent(discountPercent)).thenReturn(true);

        when(checkoutView.getCheckoutDate()).thenReturn(checkoutDate);

        // Act
        checkoutController.processCheckout();

        // Assert
        verify(checkoutView).closeScanner();
        ArgumentCaptor<RentalAgreement> captor = ArgumentCaptor.forClass(RentalAgreement.class);
        verify(checkoutView).displayRentalAgreement(captor.capture());

        RentalAgreement rentalAgreement = captor.getValue();
        assertEquals(ToolType.Jackhammer, rentalAgreement.getToolType());
        assertEquals(ToolBrand.Dewalt, rentalAgreement.getToolBrand());
        assertEquals(rentalDayCount, rentalAgreement.getRentalDays());
        assertEquals(discountPercent, rentalAgreement.getDiscountPercent());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
    }

    @Test
    public void testProcessCheckoutIntegration_Test5() {
        // Arrange
        String toolCode = "JAKR";
        LocalDate checkoutDate = LocalDate.of(2015, 7, 2);
        int rentalDayCount = 9;
        int discountPercent = 0;

        when(checkoutView.getToolCode()).thenReturn(toolCode);
        when(inputValidationService.validateToolCode(toolCode)).thenReturn(true);

        when(checkoutView.getRentalDayCount()).thenReturn(rentalDayCount);
        when(inputValidationService.validateRentalDayCount(rentalDayCount)).thenReturn(true);

        when(checkoutView.getDiscountPercent()).thenReturn(discountPercent);
        when(inputValidationService.validateDiscountPercent(discountPercent)).thenReturn(true);

        when(checkoutView.getCheckoutDate()).thenReturn(checkoutDate);

        // Act
        checkoutController.processCheckout();

        // Assert
        verify(checkoutView).closeScanner();
        ArgumentCaptor<RentalAgreement> captor = ArgumentCaptor.forClass(RentalAgreement.class);
        verify(checkoutView).displayRentalAgreement(captor.capture());

        RentalAgreement rentalAgreement = captor.getValue();
        assertEquals(ToolCode.JAKR, rentalAgreement.getToolCode());
        assertEquals(ToolType.Jackhammer, rentalAgreement.getToolType());
        assertEquals(ToolBrand.Ridgid, rentalAgreement.getToolBrand());
        assertEquals(rentalDayCount, rentalAgreement.getRentalDays());
        assertEquals(discountPercent, rentalAgreement.getDiscountPercent());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
    }

    @Test
    public void testProcessCheckoutIntegration_Test6() {
        // Arrange
        String toolCode = "JAKR";
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        int rentalDayCount = 4;
        int discountPercent = 50;

        when(checkoutView.getToolCode()).thenReturn(toolCode);
        when(inputValidationService.validateToolCode(toolCode)).thenReturn(true);

        when(checkoutView.getRentalDayCount()).thenReturn(rentalDayCount);
        when(inputValidationService.validateRentalDayCount(rentalDayCount)).thenReturn(true);

        when(checkoutView.getDiscountPercent()).thenReturn(discountPercent);
        when(inputValidationService.validateDiscountPercent(discountPercent)).thenReturn(true);

        when(checkoutView.getCheckoutDate()).thenReturn(checkoutDate);

        // Act
        checkoutController.processCheckout();

        // Assert
        verify(checkoutView).closeScanner();
        ArgumentCaptor<RentalAgreement> captor = ArgumentCaptor.forClass(RentalAgreement.class);
        verify(checkoutView).displayRentalAgreement(captor.capture());

        RentalAgreement rentalAgreement = captor.getValue();
        assertEquals(ToolType.Jackhammer, rentalAgreement.getToolType());
        assertEquals(ToolBrand.Ridgid, rentalAgreement.getToolBrand());
        assertEquals(rentalDayCount, rentalAgreement.getRentalDays());
        assertEquals(discountPercent, rentalAgreement.getDiscountPercent());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
    }
}