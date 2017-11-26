import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class HistoryWriter {

    FileReader fr;
    Writer writer;
    Scanner reader;
    String fileName;
    File f;
    ArrayList<Transaction> transactions;
    static HistoryWriter t;

    public HistoryWriter(String fileName) throws IOException {
        transactions = new ArrayList<>();
        this.fileName = fileName;
        try {
            f = new File(fileName);
            fr = new FileReader(f);
            reader = new Scanner(fr);
            read();
        } catch (IOException e) {

        }
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.fileName), "utf-8"));
            System.out.println("file created");
        } catch (IOException e) {

        }
        write(fileName);
    }

    public void write(String fileName) throws IOException {
        writer.close();
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
        for(Transaction t: transactions) {
            writer.write(t.toString() + "\n");
        }
        writer.flush();
    }

    public abstract void read();

    public void add(Transaction t) throws IOException {
        transactions.add(t);
        Transactions.getInstance().add(t);
        write(fileName);
    }

    public ArrayList<Transaction> getUserTransactions(String user) {
        ArrayList<Transaction> toReturn = new ArrayList<>();
        for(Transaction t: transactions) {
            if(t.user.equals(user)) {
                toReturn.add(t);
            }
        }
        return toReturn;
    }

    public ArrayList<Transaction> getRecipientTransactions(String recipient) {
        ArrayList<Transaction> toReturn = new ArrayList<>();
        for(Transaction t: transactions) {
            if(t instanceof Transfer) {
                if(((Transfer) t).recipient.equals(recipient)) {
                    toReturn.add(t);
                }
            }
        }
        return toReturn;
    }
}
