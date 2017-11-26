import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

public class BankAccount {

    private double balance;
    private long number;
    private String username;

    public BankAccount(String username) {
        this.username = username;
        Random r = new Random();
        number = 1000000000L + (long) (r.nextDouble() * 8999999999L);
        System.out.println(number);
        balance = 0;
    }

    public BankAccount(String username, double balance, long number) {
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
        try {
            balance += adding;
            Registry.getInstance().writeRegistry();
        } catch (IOException e) {

        }
    }

    public long getAccountNumber() {
        return number;
    }

    public void removeFromBalance(double removing) {
        try {
            balance -= removing;
            Registry.getInstance().writeRegistry();
        } catch (IOException e) {

        }
    }

    public String getUserString() {
        return username;
    }

    public boolean hasEnoughFunds(double removing) {
        if(balance - removing >= 0) {
            return true;
        }
        return false;
    }

}
