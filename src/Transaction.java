import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Transaction {

    // Every transaction has a date, and a user, and an amount.
    Date now;
    String date;
    double amount;
    String user;
    SimpleDateFormat dateFormatter;

    public Transaction(String date, double amount, String user) {
        this.date = date;
        this.amount = amount;
        this.user = user;
    }

    public Transaction(double amount, String user) {
        this.amount = amount;
        this.user = user;
        dateFormatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss a");
        now = new Date();
        date = dateFormatter.format(now);
    }

    public abstract String toString();

    public abstract String formatForPortal();

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getUser() {
        return user;
    }

}
