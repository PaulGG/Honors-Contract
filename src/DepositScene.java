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

public class DepositScene {

    Scene sc;

    public DepositScene() {
        GridPane gp = new GridPane();
        gp.setVgap(25);
        gp.setHgap(25);
        gp.setAlignment(Pos.TOP_LEFT);
        gp.setPadding(new Insets(25, 25,25,25));
        sc = new Scene(gp, Main.WIDTH, Main.HEIGHT);

        Label amount = new Label("Amount");
        TextField money = new TextField();
        money.requestFocus();

        Button submit = new Button("Submit");

        Label status = new Label();

        Button back = new Button("Back");

        gp.add(back, 0, 0);
        back.setOnAction(event -> {
            status.setText("");
            Main.switchScene(BankAccountScene.getInstance().getScene());
        });

        submit.setOnAction(event -> {
            try {
                double deposited = Double.parseDouble(money.getText());
                if (deposited > 0) {
                    String activeUser = LoginManager.getInstance().getActiveUser();
                    Registry.getInstance().userBankInfo.get(activeUser).addToBalance(deposited);
                    Deposits.getInstance().add((new Deposit(deposited, activeUser)));
                    status.setText("Money successfuly deposited.");
                    money.clear();
                } else {
                    status.setText("Cannot deposit 0 or a negative amount.");
                }

            } catch (IOException e) {

            } catch (NumberFormatException i) {
                status.setText("Please input a correct value for currency.");
            }
        });

        Label title = new Label("Deposit");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        gp.add(title, 1, 0);
        gp.add(amount, 1,1);
        gp.add(money,2,1);
        gp.add(submit, 3, 1);
        gp.add(status, 4, 1);
    }

    public static DepositScene getInstance()  {
        return new DepositScene();
    }

    public Scene getScene() {
        return sc;
    }

}
