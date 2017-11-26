import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Payments {

    FileReader fr;
    Writer writer;
    Scanner reader;
    ArrayList<Payment> payments;
    private static Payments t;

    private Payments() throws IOException {
        payments = new ArrayList<Payment>();
        try {
            File f = new File("payments.txt");
            fr = new FileReader(f);
            reader = new Scanner(fr);
            while(reader.hasNextLine()) {
                String str = reader.nextLine();
                String [] strs = str.split("=");
                // a payment will always look like: date+time=user=reason=company
                String date = strs[0];
                double moneySpent = Double.parseDouble(strs[1]);
                String user = strs[2];
                String reason = strs[3];
                String company = strs[4];
                payments.add(new Payment(date, moneySpent, user, reason, company));
            }
        } catch (IOException e) {

        }
        try {
            // How to make it so tht you do not have to create a new writer? if the file exists we do not want to overwrite it
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("payments.txt"), "utf-8"));
            System.out.println("File created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        writePayments();
    }

    public static Payments getInstance() throws IOException {
        if (t == null) {
            return t = new Payments();
        } else {
            return t;
        }
    }

    public void writePayments() throws IOException {
        writer.close();
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("payments.txt"), "utf-8"));
        for(Payment t: payments) {
            System.out.println(t.toString());
            writer.write(t.toString() + "\n");
        }
        writer.flush();
    }

    public void closingProgram() throws IOException {
        writePayments();
        writer.close();
    }

    public void addPayments(Payment t) throws IOException {
        payments.add(t);
        Transactions.getInstance().addTransaction(t);
        writePayments();
    }

    public ArrayList<Payment> getUserPayments(String user) {
        ArrayList<Payment> toReturn = new ArrayList<Payment>();
        for(Payment t: payments) {
            if(t.user.equals(user)) {
                toReturn.add(t);
            }
        }
        Collections.reverse(toReturn);
        return toReturn;
    }
}
