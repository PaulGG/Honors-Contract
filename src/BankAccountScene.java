import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class BankAccountScene {

    private static BankAccountScene s;
    Scene sc;

    /** BANK ACCOUNT SCENE **/

    public BankAccountScene() {
        BorderPane bp = new BorderPane();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        bp.setTop(grid);
        sc = new Scene(bp, Main.WIDTH, Main.HEIGHT);
        grid.getColumnConstraints().add(new ColumnConstraints(75));
        grid.getColumnConstraints().add(new ColumnConstraints(358));
        grid.getColumnConstraints().add(new ColumnConstraints(150));
        grid.getColumnConstraints().add(new ColumnConstraints(105));
        grid.getColumnConstraints().add(new ColumnConstraints(118));
        grid.getColumnConstraints().add(new ColumnConstraints(112));
        grid.getColumnConstraints().add(new ColumnConstraints(115));

        // logout button

        Button logout = new Button("Sign Out");

        logout.setOnAction(e -> Main.switchScene(LoginScene.getInstance().getScene()));
        grid.add(logout, 0, 0);

        TabPane tabPane = new TabPane();
        Tab transactions = new Tab("Transactions");
        transactions.setClosable(false);
        tabPane.getTabs().add(transactions);
        Tab deposits = new Tab("Deposits");
        deposits.setClosable(false);
        tabPane.getTabs().add(deposits);
        Tab transfers = new Tab("Transfers");
        transfers.setClosable(false);
        tabPane.getTabs().add(transfers);
        Tab withdrawals = new Tab("Withdrawals");
        withdrawals.setClosable(false);
        tabPane.getTabs().add(withdrawals);

        grid.add(tabPane, 1,0);

        try {
            Text balance = new Text(Registry.getInstance().userBankInfo.get(LoginManager.getInstance().getActiveUser()).getBalanceString());
            grid.add(balance, 2,0);
        } catch (IOException e) {

        }

        Button deposit = new Button("Deposit Cash");
        grid.add(deposit, 3, 0);
        deposit.setOnAction(event -> Main.switchScene(DepositScene.getInstance().getScene()));
        Button withdraw = new Button("Withdraw Cash");
        grid.add(withdraw, 4, 0);
        withdraw.setOnAction(event -> Main.switchScene(WithdrawScene.getInstance().getScene()));
        Button makeTransfer = new Button("Transfer Cash");
        grid.add(makeTransfer, 5, 0);
        Button pay = new Button("Make Payment");
        grid.add(pay, 6, 0);
        pay.setOnAction(event -> Main.switchScene(UserTransfer.getInstance().getScene()));
    }

    public static BankAccountScene getInstance()  {
        return new BankAccountScene();
    }

    public Scene getScene() {
        return sc;
    }

}
