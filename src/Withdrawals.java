import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Withdrawals {

    FileReader fr;
    Writer writer;
    Scanner reader;
    ArrayList<Withdrawal> withdrawals;
    private static Withdrawals w;

    private Withdrawals() throws IOException {
        withdrawals = new ArrayList<Withdrawal>();
        try {
            File f = new File("withdrawals.txt");
            fr = new FileReader(f);
            reader = new Scanner(fr);
            while(reader.hasNextLine()) {
                String str = reader.nextLine();
                String [] strs = str.split("=");
                // a transaction will always look like: date+time=user=reason=company
                String date = strs[0];
                double moneySpent = Double.parseDouble(strs[1]);
                String user = strs[2];
                withdrawals.add(new Withdrawal(date, moneySpent, user));
            }
        } catch (IOException e) {

        }
        try {
            // How to make it so tht you do not have to create a new writer? if the file exists we do not want to overwrite it
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("withdrawals.txt"), "utf-8"));
            System.out.println("File created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeWithdrawals();
    }

    public static Withdrawals getInstance() throws IOException {
        if (w == null) {
            return w = new Withdrawals();
        } else {
            return w;
        }
    }

    public void writeWithdrawals() throws IOException {
        writer.close();
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("withdrawals.txt"), "utf-8"));
        for(Withdrawal w: withdrawals) {
            System.out.println(w.toString());
            writer.write(w.toString() + "\n");
        }
        writer.flush();
    }

    public void closingProgram() throws IOException {
        writeWithdrawals();
        writer.close();
    }

    public void addWithdrawal(Withdrawal w) throws IOException {
        withdrawals.add(w);
        Transactions.getInstance().addTransaction(w);
        writeWithdrawals();
    }

    public ArrayList<Withdrawal> getUserWithdrawals(String user) {
        ArrayList<Withdrawal> toReturn = new ArrayList<Withdrawal>();
        for(Withdrawal w: withdrawals) {
            if(w.user.equals(user)) {
                toReturn.add(w);
            }
        }
        return toReturn;
    }
}
