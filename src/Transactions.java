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
            // TODO: READER
            while(reader.hasNextLine()) {
                String str = reader.nextLine();
                if(str.contains("DEPOSIT")) {
                    // if Deposit
                    String [] strs = str.split("=");
                    // a transaction will always look like: date+time=user=reason=company
                    String date = strs[0];
                    double moneySpent = Double.parseDouble(strs[1]);
                    String user = strs[2];
                    transactions.add(new Deposit(date, moneySpent, user));
                } else if(str.contains("WITHDRAWAL")) {
                    // if Withdrawal
                    String [] strs = str.split("=");
                    // a transaction will always look like: date+time=user=reason=company
                    String date = strs[0];
                    double moneySpent = Double.parseDouble(strs[1]);
                    String user = strs[2];
                    transactions.add(new Withdrawal(date, moneySpent, user));
                } else if(str.contains("PAYMENT")) {
                    // if Payment
                    String [] strs = str.split("=");
                    // a payment will always look like: date+time=user=reason=company
                    String date = strs[0];
                    double moneySpent = Double.parseDouble(strs[1]);
                    String user = strs[2];
                    String reason = strs[3];
                    String company = strs[4];
                    transactions.add(new Payment(date, moneySpent, user, reason, company));
                } else if(str.contains("TRANSFER")) {
                    // if Transfer
                    String [] strs = str.split("=");
                    // transfers are: date+time=amount=user=recipient
                    String date = strs[0];
                    double moneySpent = Double.parseDouble(strs[1]);
                    String user = strs[2];
                    String recipient = strs[3];
                    transactions.add(new Transfer(date, moneySpent, user, recipient));
                }
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
        writeTransactions();
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

    public ArrayList<Transaction> getRecipientTransactions(String recipient) {
        ArrayList<Transaction> toReturn = new ArrayList<Transaction>();
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
