import java.text.DecimalFormat;
import java.util.Random;

public class BankAccount {

    private double balance;
    private double number;
    private String username;

    public BankAccount(String username) {
        this.username = username;
        Random r = new Random();
        number = r.nextDouble() * Math.pow(10, 10);
        number = Math.round(number);
        System.out.println(number);
        balance = 0;
    }

    public BankAccount(String username, double balance, double number) {
        this.username = username;
        this.balance = balance;
        this.number = number;
    }

    public String toString() {
        return username + "=" + balance + "=" + number;
    }

    public String getBalanceString() {
        return DecimalFormat.getCurrencyInstance().format(balance);
    }

    public void addToBalance(double adding) {
        balance += adding;
    }

    public void removeFromBalance(double removing) {
        balance -= removing;
    }

}
