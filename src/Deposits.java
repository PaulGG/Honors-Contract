import java.io.IOException;

public class Deposits extends HistoryWriter {

    static Deposits d;

    public Deposits() throws IOException {
        super("deposits.txt");
    }

    public void read() {
        while(reader.hasNextLine()) {
            String str = reader.nextLine();
            String [] strs = str.split("=");

            long time = Long.parseLong(strs[0]);
            System.out.println("i wonder if this is null " + time);
            double moneySpent = Double.parseDouble(strs[1]);
            String user = strs[2];
            transactions.add(new Deposit(time, moneySpent, user));
        }
    }

    public static Deposits getInstance() throws IOException {
        if (d == null) {
            return d = new Deposits();
        } return d;
    }

}
