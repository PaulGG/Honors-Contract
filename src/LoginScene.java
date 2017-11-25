import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;

public class LoginScene {

    Scene sc;

    public LoginScene() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 75));

        sc = new Scene(grid, Main.WIDTH, Main.HEIGHT);

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
            LoginManager.getInstance().setUser(userTextField.getText());
            LoginManager.getInstance().setPassword(pwBox.getText());

            try {
                if (Registry.getInstance().getUser(LoginManager.getInstance().getActiveUser(), LoginManager.getInstance().getActivePassword())) {
                    info.setText("Logging in...");
                    PauseTransition p = new PauseTransition(new Duration(1000));
                    p.setOnFinished((e) -> Main.switchScene(BankAccountScene.getInstance().getScene()));
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

        register.setOnAction(e -> {
            try {
                String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
                if(userTextField.getText().length() <= 0 || pwBox.getText().length() <= 0) {
                    info.setText("Username or password fields incomplete.");
                    return;
                }
                if (!pwBox.getText().matches(pattern)) {
                    info.setText("Password must have one digit, uppercase letter, lowercase letter, special symbol, and at least 8 characters.");
                    return;
                }
                if (Registry.getInstance().getUser(userTextField.getText(), pwBox.getText()) || Registry.getInstance().userInfo.containsKey(userTextField.getText())) {
                    info.setText("Account is already in the system. Please login.");
                } else {
                    Registry.getInstance().addNewUser(userTextField.getText(), pwBox.getText());
                    info.setText("Account registered.");
                }

            } catch (IOException io) {
            }
        });
    }

    public static LoginScene getInstance()  {
        return new LoginScene();
    }

    public Scene getScene() {
        return sc;
    }
}
