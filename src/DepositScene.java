import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.InputMismatchException;

public class DepositScene {

    static DepositScene s;
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
                Registry.getInstance().userBankInfo.get(LoginManager.getInstance().getActiveUser()).addToBalance(Double.parseDouble(money.getText()));
                status.setText("Money successfuly deposited.");
                money.clear();
            } catch (IOException e) {

            } catch (NumberFormatException i) {
                status.setText("Please input a correct value for currency.");
            }
        });

        gp.add(amount, 1,0);
        gp.add(money,2,0);
        gp.add(submit, 3, 0);
        gp.add(status, 4, 0);
    }

    public static DepositScene getInstance()  {
        if(s == null) {
            return s = new DepositScene();
        } else {
            return s;
        }
    }

    public Scene getScene() {
        return sc;
    }

}
