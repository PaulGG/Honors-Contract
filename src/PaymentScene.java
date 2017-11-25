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

public class PaymentScene {

    Scene sc;

    public PaymentScene() {
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

        // reason for payment
        Label reason = new Label("Reason for Payment");
        TextField reasonInput = new TextField();

        // company payment is going to
        Label company = new Label("Company to Pay");
        TextField companyInput = new TextField();

        Button submit = new Button("Submit");

        submit.setOnAction(event -> {

        });

        Label title = new Label("Make Payment");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        gp.add(title, 1, 0);
        gp.add(amount, 1,1);
        gp.add(money,2,1);
        gp.add(reason, 1, 2);
        gp.add(reasonInput, 2,2);
        gp.add(company, 1,3);
        gp.add(companyInput, 2, 3);
        gp.add(submit, 3, 4);
    }

    public static PaymentScene getInstance()  {
        return new PaymentScene();
    }

    public Scene getScene() {
        return sc;
    }
}

