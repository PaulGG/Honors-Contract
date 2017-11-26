import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Withdrawal extends Transaction {

    public Withdrawal(long time, double moneySpent, String user) {
        super(time, moneySpent, user);
    }

    public Withdrawal(double amount, String user) {
        super(amount, user);
    }

    public String toString() {
        return time + "=" + amount + "=" + user + "=WITHDRAWAL";
    }

    public String formatForPortal() {
        return date + "\n" + "WITHDRAWAL" + " " + DecimalFormat.getCurrencyInstance().format(amount);
    }

}
