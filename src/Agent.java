import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Agent extends Rectangle {

    public Agent() {
        setWidth(500);
        setHeight(100);
        //setArcWidth(60);
        //setArcHeight(60);
        setFill(Color.WHITE.deriveColor(0, 1.2, 1, 0.6));
        setStroke(Color.BLUEVIOLET);
    }
}