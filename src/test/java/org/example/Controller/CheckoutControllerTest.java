package org.example.Controller;

import org.example.Model.RentalAgreement;
import org.example.Model.ToolBrand;
import org.example.Model.ToolCode;
import org.example.Model.ToolType;
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
    private CheckoutController checkoutController;

    @BeforeEach
    public void setUp() {
        checkoutView = mock(CheckoutView.class);
        inputValidationService = mock(InputValidationService.class);
        checkoutController = new CheckoutController(checkoutView, inputValidationService);
    }

    @Test
    public void testProcessCheckoutIntegration_Test1() {
        System.out.println("Test Scenario 1");
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

        System.out.println(exception.getMessage());

    }

    @Test
    public void testProcessCheckoutIntegration_Test2() {
        System.out.println("\nTest Scenario 2");
        // Arrange
        String toolCode = "LADW";
        int rentalDayCount = 3;
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        LocalDate dueDate = LocalDate.of(2020, 7, 5);
        double dailyRentalCharge = 1.99;
        int chargeDays = 2;
        double preDiscountCharge = 3.98;
        int discountPercent = 10;
        double discountAmount = 0.40;
        double finalCharge = 3.58;

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
        assertEquals(ToolCode.LADW, rentalAgreement.getToolCode());
        assertEquals(ToolType.Ladder, rentalAgreement.getToolType());
        assertEquals(ToolBrand.Werner, rentalAgreement.getToolBrand());
        assertEquals(rentalDayCount, rentalAgreement.getRentalDays());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
        assertEquals(dueDate, rentalAgreement.getDueDate());
        assertEquals(dailyRentalCharge, rentalAgreement.getDailyRentalCharge());
        assertEquals(chargeDays, rentalAgreement.getChargeDays());
        assertEquals(preDiscountCharge, rentalAgreement.getPreDiscountCharge());
        assertEquals(discountPercent, rentalAgreement.getDiscountPercent());
        assertEquals(discountAmount, rentalAgreement.getDiscountAmount());
        assertEquals(finalCharge, rentalAgreement.getFinalCharge());

        checkoutView = new CheckoutView();
        checkoutView.displayRentalAgreement(rentalAgreement);
    }

    @Test
    public void testProcessCheckoutIntegration_Test3() {
        System.out.println("\nTest Scenario 3");
        // Arrange
        String toolCode = "CHNS";
        int rentalDayCount = 5;
        LocalDate checkoutDate = LocalDate.of(2015, 7, 2);
        LocalDate dueDate = LocalDate.of(2015, 7, 7);
        double dailyRentalCharge = 1.49;
        int chargeDays = 4;
        double preDiscountCharge = 5.96;
        int discountPercent = 25;
        double discountAmount = 1.49;
        double finalCharge = 4.47;

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
        assertEquals(ToolCode.CHNS, rentalAgreement.getToolCode());
        assertEquals(ToolType.Chainsaw, rentalAgreement.getToolType());
        assertEquals(ToolBrand.Stihl, rentalAgreement.getToolBrand());
        assertEquals(rentalDayCount, rentalAgreement.getRentalDays());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
        assertEquals(dueDate, rentalAgreement.getDueDate());
        assertEquals(dailyRentalCharge, rentalAgreement.getDailyRentalCharge());
        assertEquals(chargeDays, rentalAgreement.getChargeDays());
        assertEquals(preDiscountCharge, rentalAgreement.getPreDiscountCharge());
        assertEquals(discountPercent, rentalAgreement.getDiscountPercent());
        assertEquals(discountAmount, rentalAgreement.getDiscountAmount());
        assertEquals(finalCharge, rentalAgreement.getFinalCharge());

        checkoutView = new CheckoutView();
        checkoutView.displayRentalAgreement(rentalAgreement);
    }

    @Test
    public void testProcessCheckoutIntegration_Test4() {
        System.out.println("\nTest Scenario 4");
        // Arrange
        String toolCode = "JAKD";
        int rentalDayCount = 6;
        LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
        LocalDate dueDate = LocalDate.of(2015, 9, 9);
        double dailyRentalCharge = 2.99;
        int chargeDays = 4;
        double preDiscountCharge = 11.96;
        int discountPercent = 0;
        double discountAmount = 0;
        double finalCharge = 11.96;

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
        assertEquals(ToolCode.JAKD, rentalAgreement.getToolCode());
        assertEquals(ToolType.Jackhammer, rentalAgreement.getToolType());
        assertEquals(ToolBrand.Dewalt, rentalAgreement.getToolBrand());
        assertEquals(rentalDayCount, rentalAgreement.getRentalDays());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
        assertEquals(dueDate, rentalAgreement.getDueDate());
        assertEquals(dailyRentalCharge, rentalAgreement.getDailyRentalCharge());
        assertEquals(chargeDays, rentalAgreement.getChargeDays());
        assertEquals(preDiscountCharge, rentalAgreement.getPreDiscountCharge());
        assertEquals(discountPercent, rentalAgreement.getDiscountPercent());
        assertEquals(discountAmount, rentalAgreement.getDiscountAmount());
        assertEquals(finalCharge, rentalAgreement.getFinalCharge());

        checkoutView = new CheckoutView();
        checkoutView.displayRentalAgreement(rentalAgreement);
    }

    @Test
    public void testProcessCheckoutIntegration_Test5() {
        System.out.println("\nTest Scenario 5");
        // Arrange
        String toolCode = "JAKR";
        int rentalDayCount = 9;
        LocalDate checkoutDate = LocalDate.of(2015, 7, 2);
        LocalDate dueDate = LocalDate.of(2015, 7, 11);
        double dailyRentalCharge = 2.99;
        int chargeDays = 5;
        double preDiscountCharge = 14.95;
        int discountPercent = 0;
        double discountAmount = 0;
        double finalCharge = 14.95;

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
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
        assertEquals(dueDate, rentalAgreement.getDueDate());
        assertEquals(dailyRentalCharge, rentalAgreement.getDailyRentalCharge());
        assertEquals(chargeDays, rentalAgreement.getChargeDays());
        assertEquals(preDiscountCharge, rentalAgreement.getPreDiscountCharge());
        assertEquals(discountPercent, rentalAgreement.getDiscountPercent());
        assertEquals(discountAmount, rentalAgreement.getDiscountAmount());
        assertEquals(finalCharge, rentalAgreement.getFinalCharge());

        checkoutView = new CheckoutView();
        checkoutView.displayRentalAgreement(rentalAgreement);
    }

    @Test
    public void testProcessCheckoutIntegration_Test6() {
        System.out.println("\nTest Scenario 6");
        // Arrange
        String toolCode = "JAKR";
        int rentalDayCount = 4;
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        LocalDate dueDate = LocalDate.of(2020, 7, 6);
        double dailyRentalCharge = 2.99;
        int chargeDays = 1;
        double preDiscountCharge = 2.99;
        int discountPercent = 50;
        double discountAmount = 1.50;
        double finalCharge = 1.49;

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
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
        assertEquals(dueDate, rentalAgreement.getDueDate());
        assertEquals(dailyRentalCharge, rentalAgreement.getDailyRentalCharge());
        assertEquals(chargeDays, rentalAgreement.getChargeDays());
        assertEquals(preDiscountCharge, rentalAgreement.getPreDiscountCharge());
        assertEquals(discountPercent, rentalAgreement.getDiscountPercent());
        assertEquals(discountAmount, rentalAgreement.getDiscountAmount());
        assertEquals(finalCharge, rentalAgreement.getFinalCharge());

        checkoutView = new CheckoutView();
        checkoutView.displayRentalAgreement(rentalAgreement);
    }
}