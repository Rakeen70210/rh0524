package org.example.Service;

import org.example.Model.ToolCode;

public class InputValidationService {

    /**
     * Check if the tool code is valid
     *
     * @param toolCode the input String to be validated
     * @return true if the tool code is valid, false otherwise
     */
    public boolean validateToolCode(String toolCode) {
        try {
            ToolCode.valueOf(toolCode);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Check if the rental day count is valid
     *
     * @param rentalDayCount the input int to be validated
     * @return true if the rental day count is valid, false otherwise
     */
    public boolean validateRentalDayCount(int rentalDayCount) {
        return rentalDayCount >= 1;
    }

    /**
     * Check if the discount percent is valid
     *
     * @param discountPercent the input int to be validated
     * @return true if the discount percent is valid, false otherwise
     */
    public boolean validateDiscountPercent(int discountPercent) {
        return discountPercent >= 0 && discountPercent <= 100;
    }
}

