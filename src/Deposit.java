import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deposit extends Transaction {

    public Deposit(long time, double moneySpent, String user) {
        super(time, moneySpent, user);
    }

    public Deposit(double amount, String user) {
        super(amount, user);
    }

    public String toString() {
        return time + "=" + amount + "=" + user + "=DEPOSIT";
    }

    public String formatForPortal() {
        return date + "\n" + "DEPOSIT" + " " + DecimalFormat.getCurrencyInstance().format(amount);
    }

}
