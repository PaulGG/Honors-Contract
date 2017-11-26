import java.io.*;

public class Transfers extends HistoryWriter {

    static Transfers t;

    private Transfers() throws IOException {
        super("transfers.txt");
    }

    public void read() {
        while (reader.hasNextLine()) {
            String str = reader.nextLine();
            String [] strs = str.split("=");
            // transfers are: date+time=amount=user=recipient
            long time= Long.parseLong(strs[0]);
            double moneySpent = Double.parseDouble(strs[1]);
            String user = strs[2];
            String recipient = strs[3];
            transactions.add(new Transfer(time, moneySpent, user, recipient));
        }
    }

    public static Transfers getInstance() throws IOException {
        if (t == null) {
            return t = new Transfers();
        } return t;
    }
}
