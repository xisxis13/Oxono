package g62664.dev3.oxono.view;

import g62664.dev3.oxono.model.Color;
import g62664.dev3.oxono.model.Player;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

import static g62664.dev3.oxono.view.Style.setPlayerStyle;

public class FXPlayerTokens extends VBox {
    // attributes
    private final HBox tokensO;
    private ImageView tokenOImg;
    private final HBox tokensX;
    private ImageView tokenXImg;
    private final Label nbTokensO;
    private final Label nbTokensX;

    // constructor
    public FXPlayerTokens(Player player) {
        this.nbTokensO = new Label(String.valueOf(player.getNbTokensO()));

        this.tokensO = new HBox(8);

        if (player.getColor() == Color.PINK) {
            tokenOImg = new ImageView(Images.PINK_O);
            this.tokensO.getChildren().addAll(tokenOImg, nbTokensO);
        } else if (player.getColor() == Color.BLACK) {
            tokenOImg = new ImageView(Images.BLACK_O);
            this.tokensO.getChildren().addAll(nbTokensO, tokenOImg);
        }

        this.nbTokensX = new Label(String.valueOf(player.getNbTokensX()));
        nbTokensX.setFont(Font.font("SF Pro Display", FontWeight.BOLD, 35));

        this.tokensX = new HBox(8);

        if (player.getColor() == Color.PINK) {
            tokenXImg = new ImageView(Images.PINK_X);
            this.tokensX.getChildren().addAll(tokenXImg, nbTokensX);

        } else if (player.getColor() == Color.BLACK) {
            tokenXImg = new ImageView(Images.BLACK_X);
            this.tokensX.getChildren().addAll(nbTokensX, tokenXImg);
        }

        this.getChildren().addAll(this.tokensX, this.tokensO);

        setPlayerStyle(this);
    }

    // getters
    public HBox getTokensO() {
        return tokensO;
    }
    public ImageView getTokenOImg() {
        return tokenOImg;
    }
    public HBox getTokensX() {
        return tokensX;
    }
    public ImageView getTokenXImg() {
        return tokenXImg;
    }
    public Label getNbTokensO() {
        return nbTokensO;
    }
    public Label getNbTokensX() {
        return nbTokensX;
    }

    // setters
    public void setNbTokensO(int nbTokensO) {
        this.nbTokensO.setText(String.valueOf(nbTokensO));
    }
    public void setNbTokensX(int nbTokensX) {
        this.nbTokensX.setText(String.valueOf(nbTokensX));
    }

    public static void main(String[] args) {
        List<String> fontFamilies = Font.getFamilies();
        List<String> fontNames = Font.getFontNames();

        for (String item : fontFamilies) {
            System.out.println(item);
        }
        for (String item : fontNames) {
            System.out.println(item);
        }
    }
}
