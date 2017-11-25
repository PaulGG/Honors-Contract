import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

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
    String reason;
    String company;
    Date now;

    public Transaction(String date, double moneySpent, String user, String reason, String company) {
        this.date = date;
        this.moneySpent = moneySpent;
        this.user = user;
        this.reason = reason;
        this.company = company;
    }

    public Transaction(double moneySpent, String user, String reason, String company) {
        this.moneySpent = moneySpent;
        this.user = user;
        this.reason = reason;
        this.company = company;
        dateFormatter = new SimpleDateFormat("y/M/d h:m:s a");
        now = new Date();
        date = dateFormatter.format(now);
    }

    public String toString() {
        return date + "=" + moneySpent + "=" + user + "=" + reason + "=" + company;
    }

    public String formatForPortal() {
        return date + "\n" + DecimalFormat.getCurrencyInstance().format(moneySpent) + " " + company + " " + reason;
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

    public String getReason() {
        return reason;
    }

    public String getCompany() {
        return company;
    }
}
