import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class UserTransfer {

    static UserTransfer s;
    Scene sc;

    public UserTransfer() {
        GridPane gp = new GridPane();
        gp.setVgap(25);
        gp.setHgap(25);
        gp.setAlignment(Pos.TOP_LEFT);
        gp.setPadding(new Insets(25, 25,25,25));
        sc = new Scene(gp, Main.WIDTH, Main.HEIGHT);

        Button back = new Button("Back");
        gp.add(back, 0, 0);
        back.setOnAction(event -> Main.switchScene(BankAccountScene.getInstance().getScene()));

        Label amount = new Label("Amount");
        TextField money = new TextField();

        Label toSend = new Label("Username to Send To");
        TextField person = new TextField();

        Button submit = new Button("Submit");

        submit.setOnAction(event -> {
            try {
                Registry.getInstance().userBankInfo.get(LoginManager.getInstance().getActiveUser()).removeFromBalance(Double.parseDouble(money.getText()));
                Registry.getInstance().userBankInfo.get(person.getText()).addToBalance(Double.parseDouble(money.getText()));
                money.clear();
            } catch (IOException e) {

            } catch (NullPointerException n) {
                System.out.println("User does not exist.");
            } catch (NumberFormatException a) {
                System.out.println("Invalid Input");
            }
        });

        gp.add(amount, 1,0);
        gp.add(money,2,0);
        gp.add(submit, 3, 0);
        gp.add(toSend, 1, 1);
        gp.add(person, 2,1);
    }

    public static UserTransfer getInstance()  {
        if(s == null) {
            return s = new UserTransfer();
        } else {
            return s;
        }
    }

    public Scene getScene() {
        return sc;
    }
}
