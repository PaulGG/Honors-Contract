import java.text.DecimalFormat;

public class Transfer extends Transaction {

    String recipient;

    public Transfer(String date, double amount, String user, String recipient) {
        super(date, amount, user);
        this.recipient = recipient;
    }

    public Transfer(double amount, String user, String recipient) {
        super(amount, user);
        this.recipient = recipient;
    }

    public String toString() {
        return date + "=" + amount + "=" + user + "=" + recipient + "=TRANSFER";
    }

    public String formatForPortal() {
        return date + "\n" + "TRANSFER" + " " + DecimalFormat.getCurrencyInstance().format(amount) + " TO " + recipient;
    }

    public String formatForPortalRecipient() {
        return date + "\n" + "TRANSFER" + " " + DecimalFormat.getCurrencyInstance().format(amount) + " FROM " + user;
    }
}
