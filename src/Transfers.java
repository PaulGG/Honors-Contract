import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Transfers {

    FileReader fr;
    Writer writer;
    Scanner reader;
    ArrayList<Transfer> transfers;
    private static Transfers t;

    private Transfers() throws IOException {
        transfers = new ArrayList<Transfer>();
        try {
            File f = new File("transfers.txt");
            fr = new FileReader(f);
            reader = new Scanner(fr);
            while(reader.hasNextLine()) {
                String str = reader.nextLine();
                String [] strs = str.split("=");
                // transfers are: date+time=amount=user=recipient
                String date = strs[0];
                double moneySpent = Double.parseDouble(strs[1]);
                String user = strs[2];
                String recipient = strs[3];
                transfers.add(new Transfer(date, moneySpent, user, recipient));
            }
        } catch (IOException e) {

        }
        try {
            // How to make it so tht you do not have to create a new writer? if the file exists we do not want to overwrite it
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("transfers.txt"), "utf-8"));
            System.out.println("File created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeTransfers();
    }

    public static Transfers getInstance() throws IOException {
        if (t == null) {
            return t = new Transfers();
        } else {
            return t;
        }
    }

    public void writeTransfers() throws IOException {
        writer.close();
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("transfers.txt"), "utf-8"));
        for(Transfer t: transfers) {
            System.out.println(t.toString());
            writer.write(t.toString() + "\n");
        }
        writer.flush();
    }

    public void closingProgram() throws IOException {
        writeTransfers();
        writer.close();
    }

    public void addTransfer(Transfer t) throws IOException {
        transfers.add(t);
        Transactions.getInstance().addTransaction(t);
        writeTransfers();
    }

    public ArrayList<Transfer> getUserTransfers(String user) {
        ArrayList<Transfer> toReturn = new ArrayList<Transfer>();
        for(Transfer t: transfers) {
            if(t.user.equals(user)) {
                toReturn.add(t);
            }
        }
        return toReturn;
    }

    public ArrayList<Transfer> getRecipientTransfers(String recipient) {
        ArrayList<Transfer> toReturn = new ArrayList<Transfer>();
        for(Transfer t: transfers) {
            if(t.recipient.equals(recipient)) {
                toReturn.add(t);
            }
        }
        return toReturn;
    }
}
