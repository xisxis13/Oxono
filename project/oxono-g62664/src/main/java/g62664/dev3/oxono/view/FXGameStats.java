package g62664.dev3.oxono.view;

import g62664.dev3.oxono.model.*;
import g62664.dev3.oxono.model.dp.command.CommandManager;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import static g62664.dev3.oxono.view.Style.setGameStatsStyle;

public class FXGameStats extends BorderPane {
    // attributes
    private final FXPlayerTokens blackPlayerTokens;
    private final FXPlayerTokens pinkPlayerTokens;
    private final FXInput fxInput;
    private final BorderPane borderPane;

    // constructor
    public FXGameStats(Oxono facade, CommandManager commandManager) {
        this.borderPane = new BorderPane();
        borderPane.setPrefSize(100, 40);
        borderPane.setPadding(new Insets(10));

        this.blackPlayerTokens = new FXPlayerTokens(facade.getGame().getBlackPlayer());
        this.pinkPlayerTokens = new FXPlayerTokens(facade.getGame().getPinkPlayer());
        fxInput = new FXInput(commandManager);

        this.borderPane.setLeft(this.pinkPlayerTokens);
        this.borderPane.setRight(this.blackPlayerTokens);
        this.borderPane.setCenter(fxInput);

        setGameStatsStyle(this);
    }

    // getters
    public BorderPane getBorderPane() {
        return borderPane;
    }
    public Button getQuitButton() {
        return fxInput.getQuitButton();
    }

    // method
    public void updateGameStats(Oxono facade) {
        System.out.println("GameStats updated");
        blackPlayerTokens.setNbTokensO(facade.getGame().getBlackPlayer().getNbTokensO());
        blackPlayerTokens.setNbTokensX(facade.getGame().getBlackPlayer().getNbTokensX());
        pinkPlayerTokens.setNbTokensO(facade.getGame().getPinkPlayer().getNbTokensO());
        pinkPlayerTokens.setNbTokensX(facade.getGame().getPinkPlayer().getNbTokensX());
    }
}
