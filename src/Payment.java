import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment extends Transaction {

    // A transaction must have the following:
    // A date
    // An amount of money spent
    // The person who spent it
    // The reason it was spent
    // The company it is going to

    String reason;
    String company;

    public Payment(long time, double amount, String user, String reason, String company) {
        super(time, amount, user);
        this.reason = reason;
        this.company = company;
    }

    public Payment(double amount, String user, String reason, String company) {
        super(amount, user);
        this.reason = reason;
        this.company = company;
    }

    public String toString() {
        return time + "=" + amount + "=" + user + "=" + reason + "=" + company + "=PAYMENT";
    }

    public String formatForPortal() {
        return date + "\n" + "PAYMENT" + " " + DecimalFormat.getCurrencyInstance().format(amount) + " TO: " + company + " FOR: " + reason;
    }

    public String getReason() {
        return reason;
    }

    public String getCompany() {
        return company;
    }
}
