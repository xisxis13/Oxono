package g62664.dev3.oxono.view;

import g62664.dev3.oxono.model.GridSize;
import g62664.dev3.oxono.model.Level;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static g62664.dev3.oxono.view.Style.setFromStyle;

public class FXFormView extends VBox {
    // attributes
    private final HBox gridSizeHbox;
    private final Label gridSizeLabel;
    private final ComboBox<GridSize> gridSizeChoices;
    private final HBox iaLevelHbox;
    private final Label iaLevelLabel;
    private final ComboBox<Level> iaLevelChoices;
    private final Button startButton;

    // constructor
    public FXFormView() {
        gridSizeHbox = new HBox(5);
        gridSizeLabel = new Label("Grid size : ");
        this.gridSizeChoices = new ComboBox<>();
        gridSizeChoices.getItems().addAll(
            GridSize._6_by_6,
            GridSize._8_by_8
        );

        iaLevelHbox = new HBox(5);
        iaLevelLabel = new Label("Level : ");
        this.iaLevelChoices = new ComboBox<>();
        iaLevelChoices.getItems().addAll(
                Level.no_IA,
                Level.level_0
        );

        this.startButton = new Button("Start game");

        gridSizeHbox.getChildren().addAll(gridSizeLabel, gridSizeChoices);
        iaLevelHbox.getChildren().addAll(iaLevelLabel, iaLevelChoices);

        this.getChildren().addAll(gridSizeHbox, iaLevelHbox, startButton);

        setFromStyle(this);
    }

    // getter
    public HBox getGridSizeHbox() {
        return gridSizeHbox;
    }
    public Label getGridSizeLabel() {
        return gridSizeLabel;
    }
    public HBox getIaLevelHbox() {
        return iaLevelHbox;
    }
    public Label getIaLevelLabel() {
        return iaLevelLabel;
    }

    public ComboBox<GridSize> getGridSizeChoices() {
        return gridSizeChoices;
    }
    public ComboBox<Level> getIaLevelChoices() {
        return iaLevelChoices;
    }
    public Button getStartButton() {
        return startButton;
    }
}
