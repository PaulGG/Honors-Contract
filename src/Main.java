import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;

public class Main extends Application {

    private String user;
    private String password;
    private Stage primaryStage;
    private final int WIDTH = 1130;
    private final int HEIGHT = 576;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Hello World");
        this.primaryStage.show();
        this.primaryStage.setScene(login());
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        this.primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        this.primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }

    private void switchScene(Scene scene) {
        this.primaryStage.setScene(scene);
    }

    /** LOGIN SCENE **/

    private Scene login() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 75));

        Scene scene = new Scene(grid, WIDTH, HEIGHT);

        // Welcome to Maze Bank

        Text welcome = new Text("Welcome to Maze Bank");
        welcome.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(welcome, 0, 0, 2, 1);

        // If you do not have an account, please click register.

        Text info = new Text("If you do not have an account, please click register.");
        info.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        grid.add(info, 0, 1, 2, 1);

        // User Name:

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 2);

        // textfield...

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 2);

        // Password:

        Label pw = new Label("Password:");
        grid.add(pw, 0, 3);

        // textfield...

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 3);

        // login button

        Button login = new Button("Login");
        grid.add(login, 0, 4);

        login.setOnAction(event -> {
            setUser(userTextField.getText());
            setPassword(pwBox.getText());

            try {
                if(Registry.getInstance().getUser(password, user)) {
                    info.setText("Logging in...");
                    PauseTransition p = new PauseTransition(new Duration(1000));
                    p.setOnFinished((e) -> {
                        switchScene(portal());
                    });
                    p.play();
                } else {
                    info.setText("Invalid username or password.");
                }
            } catch (IOException e) {

            }

        });

        // register button

        Button register = new Button("Register");
        grid.add(register, 1, 4);

        register.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    if(pwBox.getText().length() <= 0 || userTextField.getText().length() <= 0) {
                        info.setText("Username or password field incomplete.");
                        return;
                    }
                    if(!Registry.getInstance().getUser(pwBox.getText(), userTextField.getText()) && !Registry.getInstance().userinfo.containsValue(userTextField.getText())) {
                        Registry.getInstance().addNewUser(pwBox.getText(), userTextField.getText());
                        info.setText("Account registered.");
                    } else {
                        info.setText("Account is already in the system. Please login.");
                    }

                } catch (IOException io) {
                }
            }
        });
        return scene;
    }

    /** BANK ACCOUNT SCENE **/
    private Scene portal() {
        BorderPane bp = new BorderPane();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        bp.setTop(grid);
        Scene scene = new Scene(bp, WIDTH, HEIGHT);
        grid.getColumnConstraints().add(new ColumnConstraints(75));
        grid.getColumnConstraints().add(new ColumnConstraints(342));
        grid.getColumnConstraints().add(new ColumnConstraints(150));
        grid.getColumnConstraints().add(new ColumnConstraints(105));
        grid.getColumnConstraints().add(new ColumnConstraints(118));
        grid.getColumnConstraints().add(new ColumnConstraints(112));
        grid.getColumnConstraints().add(new ColumnConstraints(115));

        // logout button

        Button logout = new Button("Sign Out");

        logout.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                switchScene(login());
            }
        });
        grid.add(logout, 0, 0);

        TabPane tabPane = new TabPane();
        Tab transactions = new Tab("Transactions");
        transactions.setClosable(false);
        tabPane.getTabs().add(transactions);
        Tab deposits = new Tab("Deposits");
        deposits.setClosable(false);
        tabPane.getTabs().add(deposits);
        Tab checks = new Tab("Checks");
        checks.setClosable(false);
        tabPane.getTabs().add(checks);
        Tab withdrawals = new Tab("Withdrawals");
        withdrawals.setClosable(false);
        tabPane.getTabs().add(withdrawals);

        grid.add(tabPane, 1,0);

        try {
            Text balance = new Text(Registry.getInstance().userBankInfo.get(user).getBalanceString());
            grid.add(balance, 2,0);
        } catch (IOException e) {

        }

        Button deposit = new Button("Deposit Cash");
        grid.add(deposit, 3, 0);
        deposit.setOnAction(event -> {
            switchScene(deposit());
        });
        Button withdraw = new Button("Withdraw Cash");
        grid.add(withdraw, 4, 0);
        withdraw.setOnAction(event -> {
            switchScene(withdraw());
        });
        Button checkD = new Button("Deposit Check");
        grid.add(checkD, 5, 0);
        Button pay = new Button("Make Payment");
        grid.add(pay, 6, 0);
        pay.setOnAction(event -> {
            switchScene(makePayment());
        });
        return scene;
    }

    /** DEPOSIT SCENE **/
    private Scene deposit() {
        GridPane gp = new GridPane();
        gp.setVgap(25);
        gp.setHgap(25);
        gp.setAlignment(Pos.TOP_LEFT);
        gp.setPadding(new Insets(25, 25,25,25));
        Scene scene = new Scene(gp, WIDTH, HEIGHT);

        Button back = new Button("Back");
        gp.add(back, 0, 0);
        back.setOnAction(event -> {
            switchScene(portal());
        });

        Label amount = new Label("Amount");
        TextField money = new TextField();

        Button submit = new Button("Submit");

        submit.setOnAction(event -> {
            try {
                Registry.getInstance().userBankInfo.get(user).addToBalance(Double.parseDouble(money.getText()));
            } catch (IOException e) {

            }
        });

        gp.add(amount, 1,0);
        gp.add(money,2,0);
        gp.add(submit, 3, 0);

        return scene;

    }

    private Scene withdraw() {
        GridPane gp = new GridPane();
        gp.setVgap(25);
        gp.setHgap(25);
        gp.setAlignment(Pos.TOP_LEFT);
        gp.setPadding(new Insets(25, 25,25,25));
        Scene scene = new Scene(gp, WIDTH, HEIGHT);

        Button back = new Button("Back");
        gp.add(back, 0, 0);
        back.setOnAction(event -> {
            switchScene(portal());
        });

        Label amount = new Label("Amount");
        TextField money = new TextField();

        Button submit = new Button("Submit");

        submit.setOnAction(event -> {
            try {
                Registry.getInstance().userBankInfo.get(user).removeFromBalance(Double.parseDouble(money.getText()));
            } catch (IOException e) {

            }
        });

        gp.add(amount, 1,0);
        gp.add(money,2,0);
        gp.add(submit, 3, 0);

        return scene;

    }

    private Scene makePayment() {
        GridPane gp = new GridPane();
        gp.setVgap(25);
        gp.setHgap(25);
        gp.setAlignment(Pos.TOP_LEFT);
        gp.setPadding(new Insets(25, 25,25,25));
        Scene scene = new Scene(gp, WIDTH, HEIGHT);

        Button back = new Button("Back");
        gp.add(back, 0, 0);
        back.setOnAction(event -> {
            switchScene(portal());
        });

        Label amount = new Label("Amount");
        TextField money = new TextField();

        Label toSend = new Label("Username to Send To");
        TextField person = new TextField();

        Button submit = new Button("Submit");

        submit.setOnAction(event -> {
            try {
                Registry.getInstance().userBankInfo.get(user).removeFromBalance(Double.parseDouble(money.getText()));
                Registry.getInstance().userBankInfo.get(person.getText()).addToBalance(Double.parseDouble(money.getText()));
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

        return scene;

    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setUser(String user) {
        this.user = user;
    }

    public static void main(String[] args) throws IOException {
        launch(args);
        Registry.getInstance().closingProgram();
        System.out.println("i have closed");
    }
}