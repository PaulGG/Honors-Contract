import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deposit {

    // A transaction must have the following:
    // A date
    // An amount of money spent
    // The person who spent it
    // The reason it was spent
    // The company it is going to

    String date;
    SimpleDateFormat dateFormatter;
    double moneySpent;
    String user;
    Date now;

    public Deposit(String date, double moneySpent, String user) {
        this.date = date;
        this.moneySpent = moneySpent;
        this.user = user;
    }

    public Deposit(double moneySpent, String user) {
        this.moneySpent = moneySpent;
        this.user = user;
        dateFormatter = new SimpleDateFormat("y/M/d h:m:s a");
        now = new Date();
        date = dateFormatter.format(now);
    }

    public String toString() {
        return date + "=" + moneySpent + "=" + user;
    }

    public String formatForPortal() {
        return date + "\n" + DecimalFormat.getCurrencyInstance().format(moneySpent);
    }

    public String getDate() {
        return date;
    }

    public double getMoneySpent() {
        return moneySpent;
    }

    public String getUser() {
        return user;
    }
}
