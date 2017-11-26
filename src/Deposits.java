import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Deposits {

    FileReader fr;
    Writer writer;
    Scanner reader;
    ArrayList<Deposit> deposits;
    private static Deposits d;

    private Deposits() throws IOException {
        deposits = new ArrayList<Deposit>();
        try {
            File f = new File("deposits.txt");
            fr = new FileReader(f);
            reader = new Scanner(fr);
            while(reader.hasNextLine()) {
                String str = reader.nextLine();
                String [] strs = str.split("=");
                // a transaction will always look like: date+time=user=reason=company
                String date = strs[0];
                double moneySpent = Double.parseDouble(strs[1]);
                String user = strs[2];
                deposits.add(new Deposit(date, moneySpent, user));
            }
        } catch (IOException e) {

        }
        try {
            // How to make it so tht you do not have to create a new writer? if the file exists we do not want to overwrite it
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("deposits.txt"), "utf-8"));
            System.out.println("File created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeDeposits();
    }

    public static Deposits getInstance() throws IOException {
        if (d == null) {
            return d = new Deposits();
        } else {
            return d;
        }
    }

    public void writeDeposits() throws IOException {
        writer.close();
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("deposits.txt"), "utf-8"));
        for(Deposit d: deposits) {
            System.out.println(d.toString());
            writer.write(d.toString() + "\n");
        }
        writer.flush();
    }

    public void closingProgram() throws IOException {
        writeDeposits();
        writer.close();
    }

    public void addDeposit(Deposit d) throws IOException {
        deposits.add(d);
        Transactions.getInstance().addTransaction(d);
        writeDeposits();
    }

    public ArrayList<Deposit> getUserDeposits(String user) {
        ArrayList<Deposit> toReturn = new ArrayList<Deposit>();
        for(Deposit d: deposits) {
            if(d.user.equals(user)) {
                toReturn.add(d);
            }
        }
        return toReturn;
    }
}
