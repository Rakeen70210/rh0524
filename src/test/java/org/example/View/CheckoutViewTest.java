package org.example.View;

import org.example.Model.RentalAgreement;
import org.example.Model.ToolBrand;
import org.example.Model.ToolCode;
import org.example.Model.ToolType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutViewTest {

    private CheckoutView checkoutView;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        checkoutView = new CheckoutView();
    }

    @ParameterizedTest
    @CsvSource({
            "LADW, LADW",
            "CHNS, CHNS",
            "JAKR, JAKR",
            "JAKD, JAKD"
    })
    public void testGetToolCode(String input, String expected) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        checkoutView = new CheckoutView();

        String toolCode = checkoutView.getToolCode();
        assertEquals(expected, toolCode);
    }

    @ParameterizedTest
    @CsvSource({
            "5, 5",
            "3, 3",
            "6, 6",
            "4, 4",
            "9, 9"
    })
    public void testGetRentalDayCount(String input, int expected) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        checkoutView = new CheckoutView();

        int rentalDayCount = checkoutView.getRentalDayCount();
        assertEquals(expected, rentalDayCount);
    }

    @ParameterizedTest
    @CsvSource({
            "101, 101",
            "10, 10",
            "25, 25",
            "0, 0",
            "50, 50"
    })
    public void testGetDiscountPercent(String input, int expected) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        checkoutView = new CheckoutView();

        int discountPercent = checkoutView.getDiscountPercent();
        assertEquals(expected, discountPercent);
    }

    @ParameterizedTest
    @CsvSource({
            "9/3/15, 09/03/15",
            "7/2/20, 07/02/20",
            "7/2/15, 07/02/15"
    })
    public void testGetCheckoutDate(String input, String expected) throws ParseException {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        checkoutView = new CheckoutView();

        LocalDate checkoutDate = checkoutView.getCheckoutDate();
        Date date = dateFormat.parse(expected);
        LocalDate expectedDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        assertEquals(expectedDate, checkoutDate);
    }

    @Test
    public void testDisplayToolCodeError() {
        checkoutView.displayToolCodeError();
        assertEquals("Invalid Tool Code.\n", outContent.toString());
    }

    @Test
    public void testDisplayRentalAgreement() {
        RentalAgreement rentalAgreement = Mockito.mock(RentalAgreement.class);
        Mockito.when(rentalAgreement.getToolCode()).thenReturn(ToolCode.LADW);
        Mockito.when(rentalAgreement.getToolType()).thenReturn(ToolType.Ladder);
        Mockito.when(rentalAgreement.getToolBrand()).thenReturn(ToolBrand.Werner);
        Mockito.when(rentalAgreement.getRentalDays()).thenReturn(5);
        Mockito.when(rentalAgreement.getCheckoutDate()).thenReturn(LocalDate.of(2023, 10, 1));
        Mockito.when(rentalAgreement.getDueDate()).thenReturn(LocalDate.of(2023, 10, 6));
        Mockito.when(rentalAgreement.getDailyRentalCharge()).thenReturn(1.99);
        Mockito.when(rentalAgreement.getChargeDays()).thenReturn(4);
        Mockito.when(rentalAgreement.getPreDiscountCharge()).thenReturn(7.96);
        Mockito.when(rentalAgreement.getDiscountPercent()).thenReturn(10);
        Mockito.when(rentalAgreement.getDiscountAmount()).thenReturn(0.80);
        Mockito.when(rentalAgreement.getFinalCharge()).thenReturn(7.16);

        checkoutView.displayRentalAgreement(rentalAgreement);

        String expectedOutput = "Tool Code: LADW\n" + "Tool Type: Ladder\n" +
                "Tool Brand: Werner\n" +
                "Rental Days: 5\n" +
                "Checkout Date: 10/01/23\n" +
                "Due Date: 10/06/23\n" +
                "Daily Rental Charge: $1.99\n" +
                "Charge Days: 4\n" +
                "Pre-discount Charge: $7.96\n" +
                "Discount Percent: 10%\n" +
                "Discount Amount: $0.80\n" +
                "Final Charge: $7.16\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}