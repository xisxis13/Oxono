package g62664.dev3.oxono.view;

import g62664.dev3.oxono.model.dp.command.CommandManager;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import static g62664.dev3.oxono.view.Style.setInputsStyle;

public class FXInput extends HBox {
    // attributes
    private final Button undoButton;
    private final ImageView undoImg;
    private final Button redoButton;
    private final ImageView redoImg;
    private final Button quitButton;

    // constructor
    public FXInput(CommandManager commandManager) {
        undoImg = new ImageView(Images.UNDO);

        this.undoButton = new Button();
        this.undoButton.setPrefSize(30, 30);
        this.undoButton.setGraphic(undoImg);
        this.undoButton.setOnAction(e -> {
            commandManager.undo();
            System.out.println("UNDO");
        });

        redoImg = new ImageView(Images.REDO);
        redoImg.setFitHeight(30);
        redoImg.setPreserveRatio(true);

        this.redoButton = new Button();
        this.redoButton.setPrefSize(30, 30);
        this.redoButton.setGraphic(redoImg);
        this.redoButton.setOnAction(e -> {
            commandManager.redo();
            System.out.println("REDO");
        });

        this.quitButton = new Button("Quit");

        this.getChildren().addAll(undoButton, quitButton, redoButton);

        setInputsStyle(this);
    }

    // getters
    public Button getUndoButton() {
        return undoButton;
    }
    public ImageView getUndoImg() {
        return undoImg;
    }
    public Button getRedoButton() {
        return redoButton;
    }
    public ImageView getRedoImg() {
        return redoImg;
    }
    public Button getQuitButton() {
        return quitButton;
    }
}
