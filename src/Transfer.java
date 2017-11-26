import java.text.DecimalFormat;

public class Transfer extends Transaction {

    String recipient;

    public Transfer(long time, double amount, String user, String recipient) {
        super(time, amount, user);
        this.recipient = recipient;
    }

    public Transfer(double amount, String user, String recipient) {
        super(amount, user);
        this.recipient = recipient;
    }

    public String toString() {
        return time + "=" + amount + "=" + user + "=" + recipient + "=TRANSFER";
    }

    public String formatForPortal() {
        return date + "\n" + "TRANSFER" + " " + DecimalFormat.getCurrencyInstance().format(amount) + " TO " + recipient;
    }

    public String formatForPortalRecipient() {
        return date + "\n" + "TRANSFER" + " " + DecimalFormat.getCurrencyInstance().format(amount) + " FROM " + user;
    }
}
