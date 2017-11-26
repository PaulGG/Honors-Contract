import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;

public class BankAccountScene {

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
        grid.getColumnConstraints().add(new ColumnConstraints(150));
        grid.getColumnConstraints().add(new ColumnConstraints(440));
        grid.getColumnConstraints().add(new ColumnConstraints(105));
        grid.getColumnConstraints().add(new ColumnConstraints(118));
        grid.getColumnConstraints().add(new ColumnConstraints(112));
        grid.getColumnConstraints().add(new ColumnConstraints(115));

        Label title = new Label("Account Portal");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(title,0,0);

        Label accountNumber = new Label();
        try {
            accountNumber = new Label("Account Number: " + Registry.getInstance().userBankInfo.get(LoginManager.getInstance().getActiveUser()).getAccountNumber());
        } catch (IOException e) {

        }
        grid.add(accountNumber, 1,0);

        // logout button

        Button logout = new Button("Sign Out");
        logout.setPrefWidth(120);

        logout.setOnAction(e -> Main.switchScene(LoginScene.getInstance().getScene()));
        grid.add(logout, 0, 1);

        // TODO: add scroll panes to all vboxes

        TabPane tabPane = new TabPane();

        Tab transactions = new Tab("Transactions");
        transactions.setClosable(false);
        VBox transactionsVBox = new VBox();

        try {
            for(Transaction t: Transactions.getInstance().getUserTransactions(LoginManager.getInstance().getActiveUser())) {
                transactionsVBox.getChildren().add(new Label(t.formatForPortal()));
            }
            for(Transaction t: Transactions.getInstance().getRecipientTransactions(LoginManager.getInstance().getActiveUser())) {
                transactionsVBox.getChildren().add(new Label(((Transfer) t).formatForPortalRecipient()));
            }
            transactions.setContent(transactionsVBox);
        } catch (IOException e) {

        }

        Label options = new Label("Please select a tab to \nview the desired \noption.");
        grid.add(options, 0, 2);

        tabPane.getTabs().add(transactions);

        Tab payments = new Tab("Payments");
        payments.setClosable(false);
        VBox paymentsVBox = new VBox();

        try {
            for(Payment t: Payments.getInstance().getUserPayments(LoginManager.getInstance().getActiveUser())) {
                paymentsVBox.getChildren().add(new Label(t.formatForPortal()));
            }
            payments.setContent(paymentsVBox);
        } catch (IOException e) {

        }
        tabPane.getTabs().add(payments);
        Tab transfers = new Tab("Transfers");
        transfers.setClosable(false);
        VBox transfersVBox = new VBox();

        try {
            for(Transfer t: Transfers.getInstance().getUserTransfers(LoginManager.getInstance().getActiveUser())) {
                transfersVBox.getChildren().add(new Label(t.formatForPortal()));
            }
            for(Transfer t: Transfers.getInstance().getRecipientTransfers(LoginManager.getInstance().getActiveUser())) {
                transfersVBox.getChildren().add(new Label(t.formatForPortalRecipient()));
            }
            transfers.setContent(transfersVBox);
        } catch (IOException e) {

        }
        tabPane.getTabs().add(transfers);
        Tab deposits = new Tab("Deposits");
        deposits.setClosable(false);
        VBox depositsBox = new VBox();

        try {
            for(Deposit d: Deposits.getInstance().getUserDeposits(LoginManager.getInstance().getActiveUser())) {
                depositsBox.getChildren().add(new Label(d.formatForPortal()));
            }
            deposits.setContent(depositsBox);
        } catch (IOException e) {

        }
        tabPane.getTabs().add(deposits);

        Tab withdrawals = new Tab("Withdrawals");
        withdrawals.setClosable(false);
        VBox withdrawalsVBox = new VBox();

        try {
            for(Withdrawal t: Withdrawals.getInstance().getUserWithdrawals(LoginManager.getInstance().getActiveUser())) {
                withdrawalsVBox.getChildren().add(new Label(t.formatForPortal()));
            }
            withdrawals.setContent(withdrawalsVBox);
        } catch (IOException e) {

        }
        tabPane.getTabs().add(withdrawals);

        grid.add(tabPane, 1,2);

        try {
            Text balance = new Text("Balance: " + Registry.getInstance().userBankInfo.get(LoginManager.getInstance().getActiveUser()).getBalanceString());
            grid.add(balance, 1,1);
        } catch (IOException e) {

        }

        Button deposit = new Button("Deposit Cash");
        grid.add(deposit, 2, 0);
        deposit.setOnAction(event -> Main.switchScene(DepositScene.getInstance().getScene()));
        Button withdraw = new Button("Withdraw Cash");
        grid.add(withdraw, 3, 0);
        withdraw.setOnAction(event -> Main.switchScene(WithdrawalsScene.getInstance().getScene()));
        Button makeTransfer = new Button("Transfer Cash");
        grid.add(makeTransfer, 4, 0);
        makeTransfer.setOnAction(event -> Main.switchScene(TransferScene.getInstance().getScene()));
        Button pay = new Button("Make Payment");
        grid.add(pay, 5, 0);
        pay.setOnAction(event -> Main.switchScene(PaymentScene.getInstance().getScene()));
    }

    public static BankAccountScene getInstance()  {
        return new BankAccountScene();
    }

    public Scene getScene() {
        return sc;
    }

}
