package g62664.dev3.oxono.view;

import g62664.dev3.oxono.model.Board;
import g62664.dev3.oxono.controller.FXController;
import g62664.dev3.oxono.model.ErrorType;
import g62664.dev3.oxono.model.GameState;
import g62664.dev3.oxono.model.Oxono;
import g62664.dev3.oxono.model.dp.observer.Observer;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;

import static g62664.dev3.oxono.view.Style.setMainStyle;

public class FXMainView implements Observer {
    // attributes
    private final BorderPane root;
    private final FXBoard fxBoard;
    private final FXGameStats fxGameStats;
    private final FXController fxController;
    private final FXMessage fxMessage;

    // constructor
    public FXMainView(FXController controller) {
        this.root = new BorderPane();
        this.fxBoard = new FXBoard(controller.getBoard().getBoardSize());
        this.fxGameStats = new FXGameStats(controller.getFacade(), controller.getCommandManager());
        this.fxController = controller;
        this.fxMessage = new FXMessage();

        initializeView();
        setupListeners();
    }

    // getters
    public BorderPane getRoot() {
        return root;
    }
    public FXGameStats getFxGameStats() {
        return fxGameStats;
    }

    // methods
    public void initializeView() {
        fxBoard.setController(fxController);
        root.setTop(fxMessage.getMessage());
        root.setCenter(fxBoard.getGridPane());
        root.setBottom(fxGameStats.getBorderPane());
        setMainStyle(this);
        update(fxController.getFacade());
    }
    public void setupListeners() {
        fxController.getFacade().addObserver(this);
    }
    public void displayBoard(Board board) {
        fxBoard.displayBoardInConsole(board);
    }
    public void showMessage(ErrorType errorType, Oxono facade) {
        Alert alert;

        if (facade.getState() == GameState.END) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Result");
            alert.setHeaderText(facade.whoWin() + " wins this game !");
            alert.setContentText("Congratulation ! You are amazing, just like your game !");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(errorType.getTitle());
            alert.setHeaderText(errorType.getHeader());
            alert.setContentText(errorType.getMessage());
        }

        alert.showAndWait();
    }
    @Override
    public void update(Oxono facade) {
        Platform.runLater(() -> {
            if (facade.getState() == GameState.END) {
                fxMessage.setMessage("The winner is the " + facade.whoWin() + " !");

            } else if (facade.getState() == GameState.MOVE) {
                fxMessage.setMessage(facade.getCurrentPlayer() + "'s turn");
            }

            fxBoard.updateBoard(facade.getBoard());
            fxGameStats.updateGameStats(facade);
            displayBoard(facade.getBoard());
        });
    }
}
