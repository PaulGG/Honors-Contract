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

public class WithdrawalsScene {

    Scene sc;

    public WithdrawalsScene() {
        GridPane gp = new GridPane();
        gp.setVgap(25);
        gp.setHgap(25);
        gp.setAlignment(Pos.TOP_LEFT);
        gp.setPadding(new Insets(25, 25,25,25));
        sc = new Scene(gp, Main.WIDTH, Main.HEIGHT);

        Button back = new Button("Back");
        gp.add(back, 0, 0);

        Label amount = new Label("Amount");
        TextField money = new TextField();

        Button submit = new Button("Submit");

        Label status = new Label("");

        back.setOnAction(event -> {
            status.setText("");
            Main.switchScene(BankAccountScene.getInstance().getScene());
        });

        submit.setOnAction(event -> {
            try {
                // checks if user has enough funds
                double moneyAmount = Double.parseDouble(money.getText());
                if (moneyAmount > 0) {
                    BankAccount activeUser = Registry.getInstance().userBankInfo.get(LoginManager.getInstance().getActiveUser());
                    if (activeUser.hasEnoughFunds(moneyAmount)) {
                        // removes funds
                        activeUser.removeFromBalance(moneyAmount);
                        status.setText("Funds withdrawn.");
                        Registry.getInstance().userBankInfo.get(activeUser.getUserString()).addToBalance(moneyAmount);
                        Withdrawals.getInstance().addWithdrawal((new Withdrawal(moneyAmount, activeUser.getUserString())));
                    } else {
                        // warning that user does not have enough funds
                        status.setText("You do not have enough funds to withdraw this amount.");
                    }
                } else {
                    status.setText("You cannot withdraw 0 or a negative amount.");
                }



                money.clear();
            } catch (IOException e) {

            }
        });

        Label title = new Label("Withdraw");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        gp.add(title, 1, 0);
        gp.add(amount, 1,1);
        gp.add(money,2,1);
        gp.add(submit, 3, 1);
        gp.add(status, 4, 1);
    }

    public static WithdrawalsScene getInstance()  {
        return new WithdrawalsScene();
    }

    public Scene getScene() {
        return sc;
    }

}
