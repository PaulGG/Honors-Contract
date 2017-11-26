import java.io.IOException;
import java.util.Comparator;

public class Transactions extends HistoryWriter {

    static Transactions t;

    public Transactions() throws IOException {
        super("transactions.txt");
    }

    public void read() {
        while(reader.hasNextLine()) {
            String str = reader.nextLine();
            if (str.contains("DEPOSIT")) {
                // if Deposit
                String[] strs = str.split("=");
                // a transaction will always look like: date+time=user=reason=company
                long time = Long.parseLong(strs[0]);
                double moneySpent = Double.parseDouble(strs[1]);
                String user = strs[2];
                transactions.add(new Deposit(time, moneySpent, user));
            } else if (str.contains("WITHDRAWAL")) {
                // if Withdrawal
                String[] strs = str.split("=");
                // a transaction will always look like: date+time=user=reason=company
                long time = Long.parseLong(strs[0]);
                double moneySpent = Double.parseDouble(strs[1]);
                String user = strs[2];
                transactions.add(new Withdrawal(time, moneySpent, user));
            } else if (str.contains("PAYMENT")) {
                // if Payment
                String[] strs = str.split("=");
                // a payment will always look like: date+time=user=reason=company
                long time = Long.parseLong(strs[0]);
                double moneySpent = Double.parseDouble(strs[1]);
                String user = strs[2];
                String reason = strs[3];
                String company = strs[4];
                transactions.add(new Payment(time, moneySpent, user, reason, company));
            } else if (str.contains("TRANSFER")) {
                // if Transfer
                String[] strs = str.split("=");
                // transfers are: date+time=amount=user=recipient
                long time = Long.parseLong(strs[0]);
                double moneySpent = Double.parseDouble(strs[1]);
                String user = strs[2];
                String recipient = strs[3];
                transactions.add(new Transfer(time, moneySpent, user, recipient));
            }
        }
    }

    public void add(Transaction t) throws IOException {
        transactions.add(t);
        write("transactions.txt");
    }

    public static Transactions getInstance() throws IOException{
        if (t == null) {
            return t = new Transactions();
        } return t;
    }


}
