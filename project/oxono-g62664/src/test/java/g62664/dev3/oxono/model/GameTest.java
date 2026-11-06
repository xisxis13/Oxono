package g62664.dev3.oxono.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    // attributes
    private Game game;
    private Board board;
    private Player pinkPlayer;
    private Player blackPlayer;

    // setup
    @BeforeEach
    void setup() {
        game = new Game(6);
        board = game.getBoard();
        pinkPlayer = game.getPinkPlayer();
        blackPlayer = game.getBlackPlayer();
    }

    // isFinish
    @Test
    void test_methodReturnTrueIf4PieceAlign() {
        Token token1 = new Token(Symbol.X, Color.BLACK);
        Token token2 = new Token(Symbol.X, Color.PINK);
        Token token3 = new Token(Symbol.X, Color.PINK);
        Token token4 = new Token(Symbol.X, Color.BLACK);
        board.placePieceAt(0, 0, token1);
        board.placePieceAt(0, 1, token2);
        board.placePieceAt(0, 2, token3);
        board.placePieceAt(0, 3, token4);

        assertTrue(game.isFinish(0, 1, token2));
    }
    @Test
    void test_methodReturnTrueIfAllPlayerHaveNoToken() {
        for (int i = 0; i < 8; i++) {
            pinkPlayer.decreaseTokensX();
            pinkPlayer.decreaseTokensO();
            blackPlayer.decreaseTokensX();
            blackPlayer.decreaseTokensO();
        }

        assertTrue(game.isFinish(0, 1, new Token(Symbol.X, Color.BLACK)));
    }

    // whoWin()
    @Test
    void test_whoWinReturnRightPlayer() {
        Totem totemX = board.getTotemX();

        board.insertToken(0, 0, totemX, pinkPlayer);
        board.insertToken(0, 1, totemX, blackPlayer);
        board.insertToken(0, 2, totemX, pinkPlayer);
        board.insertToken(0, 3, totemX, blackPlayer);

        // I suppose that the blackPlayer place the last piece
        assertEquals(game.whoWin(), blackPlayer);
    }

    // switchCurrentPlayer()
    @Test
    void test_switchCurrentMethod() {
        // by default, pink player is the first player to play
        game.switchCurrentPlayer();
        assertEquals(game.getCurrentPlayer(), blackPlayer);
        game.switchCurrentPlayer();
        assertEquals(game.getCurrentPlayer(), pinkPlayer);
    }
}