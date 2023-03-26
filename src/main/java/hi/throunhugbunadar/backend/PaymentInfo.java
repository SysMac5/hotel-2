package hi.throunhugbunadar.backend;

import java.util.regex.Pattern;

/**
 * Geymir greiðsluupplýsingar.
 */

public class PaymentInfo {
    private final String cardNumber;
    private final String monthValid;
    private final String yearValid;
    private final String cvv;

    public PaymentInfo(String cardNumber, String monthValid, String yearValid, String cvv) throws Exception {
        Pattern cardNumberRegex = Pattern.compile("^\\d{16}$");
        if (!cardNumberRegex.matcher(cardNumber).matches())
            throw new Exception("Card number format wrong.");
        if (!(Integer.parseInt(monthValid) <= 13 && Integer.parseInt(monthValid) > 0))
            throw new Exception("Month format wrong.");
        if (!(Integer.parseInt(yearValid) <= 13 && Integer.parseInt(yearValid) > 0))
            throw new Exception("Year format wrong.");
        if (!(Integer.parseInt(cvv) <= 999 && Integer.parseInt(cvv) >= 0))
            throw new Exception("CVV format wrong.");
        this.cardNumber = cardNumber;
        this.monthValid = monthValid;
        this.yearValid = yearValid;
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getMonthValid() {
        return monthValid;
    }

    public String getYearValid() {
        return yearValid;
    }

    public String getCvv() {
        return cvv;
    }
}
