import java.io.*;

public class Withdrawals extends HistoryWriter {

    static Withdrawals w;

    private Withdrawals() throws IOException {
        super("withdrawals.txt");
    }

    public void read() {
        while(reader.hasNextLine()) {
            String str = reader.nextLine();
            String [] strs = str.split("=");
            // a transaction will always look like: date+time=user=reason=company
            long time = Long.parseLong(strs[0]);
            double moneySpent = Double.parseDouble(strs[1]);
            String user = strs[2];
            transactions.add(new Withdrawal(time, moneySpent, user));
        }
    }

    public static Withdrawals getInstance() throws IOException {
        if (w == null) {
            return w = new Withdrawals();
        } return w;
    }


}
