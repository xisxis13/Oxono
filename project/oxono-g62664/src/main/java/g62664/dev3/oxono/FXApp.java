package g62664.dev3.oxono;

import g62664.dev3.oxono.controller.FXController;
import g62664.dev3.oxono.model.GridSize;
import g62664.dev3.oxono.model.Level;
import g62664.dev3.oxono.view.FXFormView;
import g62664.dev3.oxono.view.FXMainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class FXApp extends Application {
    // attribute
    private Stage primaryStage;

    // methods
    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        showFormView();
    }
    public void showFormView() {
        FXController controller = new FXController();
        FXFormView formView = new FXFormView();

        Scene formScene = new Scene(formView, 300, 300);
        primaryStage.setTitle("OXONO - settings");
        primaryStage.setScene(formScene);
        primaryStage.show();

        formView.getStartButton().setOnAction(e -> {
            GridSize gridSize = formView.getGridSizeChoices().getValue();
            Level iaLevel = formView.getIaLevelChoices().getValue();

            if (gridSize == null) {
                gridSize = GridSize._6_by_6;
            }

            if (iaLevel == null) {
                iaLevel = Level.no_IA;
            }

            controller.initializeStartGameButton(gridSize, iaLevel);
            showMainView(controller);
        });
    }
    public void showMainView(FXController controller) {
        FXMainView mainView = new FXMainView(controller);
        mainView.getFxGameStats().getQuitButton().setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Quit game ?");
            alert.setHeaderText("Are you sure you want to quit this game?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                primaryStage.close();
            }
        });

        Scene mainScene = new Scene(mainView.getRoot(), 500, 700);
        primaryStage.setTitle("OXONO");
        primaryStage.setScene(mainScene);
    }

    // main
    public static void main(String[] args) {
        launch(args);
    }
}
