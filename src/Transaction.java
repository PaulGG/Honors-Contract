import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public abstract class Transaction {

    // Every transaction has a date, and a user, and an amount.
    long time; // epoch time
    double amount;
    String user;
    SimpleDateFormat dateFormatter;
    Date now;
    String date;

    public Transaction(long time, double amount, String user) {
        this.time = time;
        this.amount = amount;
        this.user = user;
        dateFormatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss a");
        now = new Date(time);
        System.out.println("Here is the thing you are looking for: " + now);
        date = dateFormatter.format(now);
        System.out.println("formatted:  " + date);
    }

    public Transaction(double amount, String user) {
        this.amount = amount;
        this.user = user;
        dateFormatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss a");
        now = new Date();
        time = now.getTime();
        date = dateFormatter.format(now);
    }

    public final static Comparator<Transaction> TRANSACTION_SORT = (Transaction trans1, Transaction trans2) -> {
        if (trans1.time > trans2.time) {
            return -1;
        } else if (trans1.time < trans2.time) {
            return 1;
        }
        return 0;
    };

    public abstract String toString();

    public abstract String formatForPortal();

    public long getTime() {
        return time;
    }

    public double getAmount() {
        return amount;
    }

    public String getUser() {
        return user;
    }

    public String getDateString() {
        return date;
    }

}
