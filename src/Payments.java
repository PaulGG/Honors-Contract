import java.io.IOException;

public class Payments extends HistoryWriter {

    static Payments p;

    public Payments() throws IOException {
        super("payments.txt");
    }

    public void read() {
        while(reader.hasNextLine()) {
            String str = reader.nextLine();
            String [] strs = str.split("=");
            // a payment will always look like: date+time=user=reason=company
            long time= Long.parseLong(strs[0]);
            double moneySpent = Double.parseDouble(strs[1]);
            String user = strs[2];
            String reason = strs[3];
            String company = strs[4];
            transactions.add(new Payment(time, moneySpent, user, reason, company));
        }
    }

    public static Payments getInstance() throws IOException {
        if (p == null) {
            return p = new Payments();
        } return p;
    }

}
