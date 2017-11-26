import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;

public class TransferScene {

    Scene sc;

    public TransferScene() {
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

        Label status = new Label("");

        Button submit = new Button("Submit");

        submit.setOnAction(event -> {
            try {
                double moneyAmount = Double.parseDouble(money.getText());
                BankAccount activeUser = Registry.getInstance().userBankInfo.get(LoginManager.getInstance().getActiveUser());
                if(activeUser.hasEnoughFunds(Double.parseDouble(money.getText()))) {
                    Registry.getInstance().userBankInfo.get(LoginManager.getInstance().getActiveUser()).removeFromBalance(moneyAmount);
                    Registry.getInstance().userBankInfo.get(person.getText()).addToBalance(Double.parseDouble(money.getText()));
                    Transfers.getInstance().addTransfer(new Transfer(moneyAmount, activeUser.getUserString(), person.getText()));
                    status.setText("Funds transferred.");
                } else {
                    status.setText("You do not have enough funds to perform this transfer.");
                }

                money.clear();
                person.clear();
            } catch (IOException e) {

            } catch (NullPointerException n) {
                System.out.println("User does not exist.");
                status.setText("User does not exist in our database.");
            } catch (NumberFormatException a) {
                System.out.println("Invalid Input");
                status.setText("Please enter a valid input.");
            }
        });
        Label title = new Label("Transfer");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        gp.add(title, 1, 0);
        gp.add(amount, 1,1);
        gp.add(money,2,1);
        gp.add(submit, 3, 1);
        gp.add(toSend, 1, 2);
        gp.add(person, 2,2);
        // may need to change position of this one below
        gp.add(status, 4, 1);
    }

    public static TransferScene getInstance()  {
        return new TransferScene();
    }

    public Scene getScene() {
        return sc;
    }
}
