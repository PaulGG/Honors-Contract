import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Withdrawal extends Transaction {

    public Withdrawal(String date, double moneySpent, String user) {
        super(date, moneySpent, user);
    }

    public Withdrawal(double amount, String user) {
        super(amount, user);
    }

    public String toString() {
        return date + "=" + amount + "=" + user + "=WITHDRAWAL";
    }

    public String formatForPortal() {
        return date + "\n" + "WITHDRAWAL" + " " + DecimalFormat.getCurrencyInstance().format(amount);
    }

}
