import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Transactions {

    FileReader fr;
    Writer writer;
    Scanner reader;
    ArrayList<Transaction> transactions;
    private static Transactions t;

    private Transactions() throws IOException {
        transactions = new ArrayList<Transaction>();
        try {
            File f = new File("transactions.txt");
            fr = new FileReader(f);
            reader = new Scanner(fr);
            while(reader.hasNextLine()) {
                String str = reader.nextLine();
                String [] strs = str.split("=");
                // a transaction will always look like: date+time=user=reason=company
                String date = strs[0];
                double moneySpent = Double.parseDouble(strs[1]);
                String user = strs[2];
                String reason = strs[3];
                String company = strs[4];
                transactions.add(new Transaction(date, moneySpent, user, reason, company));
            }
        } catch (IOException e) {

        }
        try {
            // How to make it so tht you do not have to create a new writer? if the file exists we do not want to overwrite it
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("transactions.txt"), "utf-8"));
            System.out.println("File created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Transactions getInstance() throws IOException {
        if (t == null) {
            return t = new Transactions();
        } else {
            return t;
        }
    }

    public void writeTransactions() throws IOException {
        writer.close();
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("transactions.txt"), "utf-8"));
        for(Transaction t: transactions) {
            System.out.println(t.toString());
            writer.write(t.toString() + "\n");
        }
        writer.flush();
    }

    public void closingProgram() throws IOException {
        writeTransactions();
        writer.close();
    }

    public void addTransaction(Transaction t) throws IOException {
        transactions.add(t);
        writeTransactions();
    }

    public ArrayList<Transaction> getUserTransactions(String user) {
        ArrayList<Transaction> toReturn = new ArrayList<Transaction>();
        for(Transaction t: transactions) {
            if(t.user.equals(user)) {
                toReturn.add(t);
            }
        }
        return toReturn;
    }
}
