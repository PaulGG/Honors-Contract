import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Collections;

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
        ListView<String> transactionList = new ListView<String>();
        ObservableList<String> transactionItems = FXCollections.observableArrayList();


        try {
            for(Transaction t: Transactions.getInstance().getUserTransactions(LoginManager.getInstance().getActiveUser())) {
                transactionItems.add(t.formatForPortal());
            }
            for(Transaction t: Transactions.getInstance().getRecipientTransactions(LoginManager.getInstance().getActiveUser())) {
                transactionItems.add(((Transfer) t).formatForPortalRecipient());
            }
            transactionList.setItems(transactionItems);
            transactions.setContent(transactionList);
        } catch (IOException e) {

        }

        tabPane.getTabs().add(transactions);

        Tab payments = new Tab("Payments");
        payments.setClosable(false);
        ListView<String> paymentList = new ListView<String>();
        ObservableList<String> paymentItems = FXCollections.observableArrayList();

        try {
            for(Payment t: Payments.getInstance().getUserPayments(LoginManager.getInstance().getActiveUser())) {
                paymentItems.add(t.formatForPortal());
            }
            paymentList.setItems(paymentItems);
            payments.setContent(paymentList);
        } catch (IOException e) {

        }
        tabPane.getTabs().add(payments);
        Tab transfers = new Tab("Transfers");
        transfers.setClosable(false);
        ListView<String> transferList = new ListView<String>();
        ObservableList<String> transferItems = FXCollections.observableArrayList();

        try {
            for(Transfer t: Transfers.getInstance().getUserTransfers(LoginManager.getInstance().getActiveUser())) {
                transferItems.add(t.formatForPortal());
            }
            for(Transfer t: Transfers.getInstance().getRecipientTransfers(LoginManager.getInstance().getActiveUser())) {
                transferItems.add(t.formatForPortalRecipient());
            }
            Collections.reverse(transferItems);
            transferList.setItems(transferItems);
            transfers.setContent(transferList);
        } catch (IOException e) {

        }
        tabPane.getTabs().add(transfers);
        Tab deposits = new Tab("Deposits");
        deposits.setClosable(false);
        ListView<String> depositsList = new ListView<String>();
        ObservableList<String> depositItems = FXCollections.observableArrayList();
        try {
            for(Deposit d: Deposits.getInstance().getUserDeposits(LoginManager.getInstance().getActiveUser())) {
                depositItems.add(d.formatForPortal());
            }
            Collections.reverse(depositItems);
            depositsList.setItems(depositItems);
            deposits.setContent(depositsList);
        } catch (IOException e) {

        }
        tabPane.getTabs().add(deposits);

        Tab withdrawals = new Tab("Withdrawals");
        withdrawals.setClosable(false);
        ListView<String> withdrawalsList = new ListView<String>();
        ObservableList<String> withdrawalsItems = FXCollections.observableArrayList();

        try {
            for(Withdrawal t: Withdrawals.getInstance().getUserWithdrawals(LoginManager.getInstance().getActiveUser())) {
                withdrawalsItems.add(t.formatForPortal());
            }
            Collections.reverse(withdrawalsItems);
            withdrawalsList.setItems(withdrawalsItems);
            withdrawals.setContent(withdrawalsList);
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
