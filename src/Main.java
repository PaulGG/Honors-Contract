import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage myStage;
    public static final int WIDTH = 1130;
    public static final int HEIGHT = 576;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        myStage = primaryStage;
        myStage.setTitle("Bank Account Portal Simulator");
        myStage.show();
        myStage.setScene(LoginScene.getInstance().getScene());
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        myStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        myStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }

    public static void switchScene(Scene scene) {
        myStage.setScene(scene);
    }

    public static void main(String[] args) throws IOException {
        launch(args);
        // TODO: try getting rid of this garbage
        Registry.getInstance().closingProgram();
        Payments.getInstance().closingProgram();
        System.out.println("Closing program.");
    }

}