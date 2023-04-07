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

    /**
     * Býr til nýja greiðsluleið sem er greiðslukort (debit-kort eða credit-kort).
     *
     * @param cardNumber kortanúmer korts (16 tölustafir)
     * @param monthValid gilidsmánuður korts (2 tölustafir)
     * @param yearValid gildisár korts (síðustu 2 tölustafir)
     * @param cvv CVV tala korts (3 tölustafir)
     * @throws Exception ef kort er ekki gilt
     */
    public PaymentInfo(String cardNumber, String monthValid, String yearValid, String cvv) throws Exception {
        Pattern cardNumberRegex = Pattern.compile("^\\d{16}$");
        if (!cardNumberRegex.matcher(cardNumber).matches())
            throw new Exception("Card number format wrong.");
        if (!(Integer.parseInt(monthValid) < 13 && Integer.parseInt(monthValid) > 0))
            throw new Exception("Month format wrong.");
        if (!(Integer.parseInt(yearValid) <= 99 && Integer.parseInt(yearValid) >= 0))
            throw new Exception("Year format wrong.");
        if (!(Integer.parseInt(cvv) <= 999 && Integer.parseInt(cvv) >= 0))
            throw new Exception("CVV format wrong.");
        this.cardNumber = cardNumber;
        this.monthValid = monthValid;
        this.yearValid = yearValid;
        this.cvv = cvv;
    }

    /**
     * Skilar kortanúmeri korts.
     *
     * @return kortanúmer korts, þ.e. {@link PaymentInfo#cardNumber}
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Skilar mánuði þegar kort rennur út.
     *
     * @return gilidsmánuður korts, þ.e. {@link PaymentInfo#monthValid}
     */
    public String getMonthValid() {
        return monthValid;
    }

    /**
     * Skilar seinustu tveimur tölustöfum ártals þegar kort rennur út.
     *
     * @return gildisártal korts, þ.e. {@link PaymentInfo#yearValid}
     */
    public String getYearValid() {
        return yearValid;
    }

    /**
     * Sækir CVV tölu kortsins.
     * @return CVV talan, þ.e. {@link PaymentInfo#cvv}
     */
    public String getCvv() {
        return cvv;
    }
}
