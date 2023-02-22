package hi.throunhugbunadar.backend;

/**
 * Geymir greiðsluupplýsingar.
 */

public class PaymentInfo {
    private final String cardNumber;
    private final String monthValid;
    private final String yearValid;
    private final String cvv;

    public PaymentInfo(String cardNumber, String monthValid, String yearValid, String cvv) {
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
