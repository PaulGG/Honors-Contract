import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Registry {

    FileReader fr;
    Writer writer;
    Scanner reader;
    HashMap<String, String> userInfo;
    HashMap<String, BankAccount> userBankInfo;
    private static Registry r;

    private Registry() throws IOException {
        userInfo = new HashMap<>();
        userBankInfo = new HashMap<>();
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
                    long checkingNumber = Long.parseLong(strs[3]);
                    userBankInfo.put(username, new BankAccount(username, checkingBalance, checkingNumber));
                } else if (strs.length > 0) {
                    String username = strs[0];
                    String password = strs[1];
                    userInfo.put(username, password);
                }
            }
        } catch (IOException e) {
            // nothing to report
        }
        try {
            // How to make it so tht you do not have to create a new writer? if the file exists we do not want to overwrite it
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("registry.txt"), "utf-8"));
            System.out.println("File created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeRegistry();
    }

    public void writeRegistry() throws IOException {
        writer.close();
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("registry.txt"), "utf-8"));
        for(Map.Entry<String, String> user: userInfo.entrySet()) {
            System.out.println(user.toString());
            writer.write(user.toString() + "\n");
        }
        for(Map.Entry<String, BankAccount> user: userBankInfo.entrySet()) {
            System.out.println(user.toString());
            writer.write(user.toString() + "\n");
        }
        writer.flush();
    }

    public void addNewUser(String key, String value) throws IOException {
        userInfo.put(key, value);
        userBankInfo.put(key, new BankAccount(key));
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
        if (userInfo.get(password) == null) {
            return false;
        }
        if (userInfo.get(password).equals(username)) {
            return true;
        }
        return false;
    }
}
