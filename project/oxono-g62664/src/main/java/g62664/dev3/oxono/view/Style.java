package g62664.dev3.oxono.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Style {
    // methods
    public static void setFromStyle(FXFormView fxFormView) {
        fxFormView.setAlignment(Pos.CENTER);
        fxFormView.setSpacing(25);
        fxFormView.setBackground(Background.fill(Color.valueOf("#E4CCFF")));

        fxFormView.getGridSizeHbox().setAlignment(Pos.CENTER);
        fxFormView.getGridSizeLabel().setFont(Font.font("SF Pro Display", FontWeight.BOLD, 18));

        fxFormView.getIaLevelHbox().setAlignment(Pos.CENTER);
        fxFormView.getIaLevelLabel().setFont(Font.font("SF Pro Display", FontWeight.BOLD, 18));

        fxFormView.getStartButton().setPrefSize(110, 40);
        fxFormView.getStartButton().setFont(Font.font("SF Pro Display", FontWeight.BOLD, 16));

        BackgroundFill buttonBackground = new BackgroundFill(
                Color.WHITE,
                new CornerRadii(15),
                Insets.EMPTY
        );

        fxFormView.getStartButton().setBackground(new Background(buttonBackground));
    }

    public static void setPlayerStyle(FXPlayerTokens fxPlayerTokens) {
        HBox tokensO = fxPlayerTokens.getTokensO();
        HBox tokensX = fxPlayerTokens.getTokensX();
        ImageView tokenOImg = fxPlayerTokens.getTokenOImg();
        ImageView tokenXImg = fxPlayerTokens.getTokenXImg();
        Label nbTokensO = fxPlayerTokens.getNbTokensO();
        Label nbTokensX = fxPlayerTokens.getNbTokensX();

        // main container
        fxPlayerTokens.setPrefSize(80, 40);
        fxPlayerTokens.setSpacing(10);

        // secondary container
        tokensO.setAlignment(Pos.CENTER);
        tokensX.setAlignment(Pos.CENTER);

        // images
        tokenOImg.setFitHeight(50);
        tokenOImg.setPreserveRatio(true);

        tokenXImg.setFitHeight(50);
        tokenXImg.setPreserveRatio(true);

        // labels
        nbTokensO.setFont(Font.font("SF Pro Display", FontWeight.BOLD, 35));
        nbTokensX.setFont(Font.font("SF Pro Display", FontWeight.BOLD, 35));
    }

    public static void setInputsStyle(FXInput fxInput) {
        Button redoBtn = fxInput.getRedoButton();
        ImageView redoImg = fxInput.getRedoImg();
        Button undoBtn = fxInput.getUndoButton();
        ImageView undoImg = fxInput.getUndoImg();
        Button quitBtn = fxInput.getQuitButton();

        fxInput.setSpacing(10);
        fxInput.setAlignment(Pos.CENTER);

        BackgroundFill buttonBackground = new BackgroundFill(
                Color.WHITE,
                new CornerRadii(15),
                Insets.EMPTY
        );

        BorderStroke borderStroke = new BorderStroke(
                Color.valueOf("#E4CCFF"),
                BorderStrokeStyle.SOLID,
                new CornerRadii(10),
                new BorderWidths(1),
                Insets.EMPTY
        );

        undoBtn.setBorder(new Border(borderStroke));
        undoBtn.setBackground(new Background(buttonBackground));
        undoBtn.setPrefSize(28, 38);

        undoImg.setFitHeight(25);
        undoImg.setPreserveRatio(true);

        redoBtn.setBorder(new Border(borderStroke));
        redoBtn.setBackground(new Background(buttonBackground));
        redoBtn.setPrefSize(28, 38);

        redoImg.setFitHeight(25);
        redoImg.setPreserveRatio(true);

        quitBtn.setBorder(new Border(borderStroke));
        quitBtn.setBackground(new Background(buttonBackground));
        quitBtn.setPrefSize(80, 38);
        quitBtn.setFont(Font.font("SF Pro Display", FontWeight.BOLD, 16));
    }

    public static void setGameStatsStyle(FXGameStats fxGameStats) {
        fxGameStats.setBackground(Background.fill(Color.valueOf("#FF0000")));

    }

    public static void setMessageStyle(FXMessage fxMessage) {
        fxMessage.setAlignment(Pos.CENTER);
        fxMessage.setMaxWidth(Double.MAX_VALUE);
        fxMessage.setMaxHeight(Double.MAX_VALUE);

        fxMessage.getMessage().setFont(Font.font("SF Pro Display", FontWeight.BOLD, 20));
        fxMessage.getMessage().setMaxWidth(Double.MAX_VALUE);
        fxMessage.getMessage().setAlignment(Pos.CENTER);
    }

    public static void setMainStyle(FXMainView fxMainView) {
        fxMainView.getRoot().setBackground(Background.fill(Color.valueOf("#E4CCFF")));
    }
}
