import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class WithdrawScene {

    static WithdrawScene s;
    Scene sc;

    public WithdrawScene() {
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

        Button submit = new Button("Submit");

        submit.setOnAction(event -> {
            try {
                Registry.getInstance().userBankInfo.get(LoginManager.getInstance().getActiveUser()).removeFromBalance(Double.parseDouble(money.getText()));
            } catch (IOException e) {

            }
        });

        gp.add(amount, 1,0);
        gp.add(money,2,0);
        gp.add(submit, 3, 0);
    }

    public static WithdrawScene getInstance()  {
        if(s == null) {
            return s = new WithdrawScene();
        } else {
            return s;
        }
    }

    public Scene getScene() {
        return sc;
    }

}
