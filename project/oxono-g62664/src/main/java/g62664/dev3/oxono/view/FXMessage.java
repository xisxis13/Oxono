package g62664.dev3.oxono.view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import static g62664.dev3.oxono.view.Style.setMessageStyle;

public class FXMessage extends HBox {
    // attribute
    private final Label message;

    // constructor
    public FXMessage() {
        this.message = new Label("Welcome to OXONO");

        this.getChildren().addAll(message);

        setMessageStyle(this);
    }

    // getter
    public Label getMessage() {
        return message;
    }

    // setter
    public void setMessage(String message) {
        this.message.setText(message);
    }
}
