import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Registry {

    FileReader fr;
    Writer writer;
    Scanner reader;
    HashMap<String, String> userinfo;
    HashMap<String, BankAccount> userBankInfo;
    private static Registry r;


    private Registry() throws IOException {
        userinfo = new HashMap<String, String>();
        userBankInfo = new HashMap<String, BankAccount>();
        try {
            File f = new File("registry.txt");
            fr = new FileReader(f);
            reader = new Scanner(fr);
            while(reader.hasNextLine()) {
                String str = reader.nextLine();
                String [] strs = str.split("=");
                if(strs.length == 4) {
                    String username = strs[1];
                    double checkingBalance = Double.parseDouble(strs[2]);
                    double checkingNumber = Double.parseDouble(strs[3]);
                    userBankInfo.put(username, new BankAccount(username, checkingBalance, checkingNumber));
                } else if (strs.length > 0) {
                    String password = strs[0];
                    String username = strs[1];
                    userinfo.put(password, username);
                }
                /*
                String str = reader.nextLine();
                String[] strs = str.split("=");
                String key = strs[0];
                String value = strs[1];
                double checkingNumber = Double.parseDouble(strs[2]);
                double checkingBalance = Double.parseDouble(strs[3]);
                double savingsNumber = Double.parseDouble(strs[4]);
                double savingsBalance = Double.parseDouble(strs[5]);
                System.out.println("Username: " + value + " Password: " + key);
                BankAccount name = new BankAccount(value, checkingBalance, checkingNumber, savingsBalance, savingsNumber);
                userinfo.put(key, name); */
            }
        } catch (IOException e) {
            // nothing to report
        }
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("registry.txt"), "utf-8"));
            System.out.println("File created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeRegistry() throws IOException {
        for(Map.Entry<String, String> user: userinfo.entrySet()) {
            writer.write(user.toString() + "\n");
            System.out.println(user.toString());
        }
        for(Map.Entry<String, BankAccount> user: userBankInfo.entrySet()) {
            writer.write(user.toString() + "\n");
            System.out.println(user.toString());
        }
        System.out.println("I DID IT");
    }

    public void addNewUser(String key, String value) throws IOException {
        userinfo.put(key, value);
        userBankInfo.put(value, new BankAccount(value));
        writeRegistry();
    }

    public static Registry getInstance() throws IOException {
        if(r == null) {
            return r = new Registry();
        } else {
            return r;
        }
    }

    public void closingProgram() throws IOException {
        writeRegistry();
        writer.close();
    }

    public boolean getUser(String password, String username) {
        if (userinfo.get(password) == null) {
            System.out.println("RIP");
            return false;
        }
        if (userinfo.get(password).equals(username)) {
            System.out.println("Success!");
            return true;
        }
        return false;
    }
}
