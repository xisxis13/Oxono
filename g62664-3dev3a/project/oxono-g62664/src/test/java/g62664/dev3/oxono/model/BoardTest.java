package g62664.dev3.oxono.model;

import g62664.dev3.oxono.view.ConsoleView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    // attributes
    private Board board;
    private int boardSize;

    // setup
    @BeforeEach
    void setup() {
        boardSize = 6;
        board = new Board(boardSize);
    }

    // board initialization
    @Test
    void test_boardInitialization() {
        assertEquals(boardSize, board.getBoardSize());
        assertNotNull(board.getTotemX());
        assertNotNull(board.getTotemO());
    }

    // getPieceAt()
    @Test
    void test_getPieceAtReturnRightPositionsForTotems() {
        Totem totemX = board.getTotemX();
        Totem totemO = board.getTotemO();

        assertEquals(totemX, board.getPieceAt(boardSize / 2 - 1, boardSize / 2 - 1));
        assertEquals(totemO, board.getPieceAt(boardSize / 2, boardSize / 2));
    }
    @Test
    void test_getPieceAtReturnNullIfBoxIsEmpty() {
        assertNull(board.getPieceAt(1, 2));
    }

    // getTotemPosition()
    @Test
    void test_getTotemPositionReturnRightPositionIfTotemAtInitialPosition() {
        Totem totemX = board.getTotemX();
        Totem totemO = board.getTotemO();
        int[] expectedPositionX = {boardSize / 2 - 1, boardSize / 2 - 1};
        int[] expectedPositionO = {boardSize / 2, boardSize / 2};

        assertArrayEquals(board.getTotemPosition(totemX), expectedPositionX);
        assertArrayEquals(board.getTotemPosition(totemO), expectedPositionO);
    }
    @Test
    void test_getTotemPositionReturnRightPositionIfTotemMoved() {
        Totem totemX = board.getTotemX();
        Player pinkPlayer = new Player(Color.PINK);
        int[] expectedPosition = {1, 2};

        board.moveTotem(totemX, 1, 2, pinkPlayer);

        assertArrayEquals(board.getTotemPosition(board.getTotemX()), expectedPosition);
    }

    // moveTotem()
    @Test
    void test_moveTotemReturnTrue() {
        Totem totemX = board.getTotemX();
        Player pinkPlayer = new Player(Color.PINK);

        assertTrue(board.moveTotem(totemX, 1, 2, pinkPlayer));
        assertNull(board.getPieceAt(boardSize / 2 - 1, boardSize / 2 - 1));
    }

    // isValidMove()
    @Test
    void test_methodReturnTrueIfValidMove() {
        Totem totemX = board.getTotemX();
        Player pinkPlayer = new Player(Color.PINK);

        assertTrue(board.isValidMove(totemX, 1, 2, pinkPlayer));
    }
    @Test
    void test_methodReturnFalseIfOutOfBoard() {
        Totem totemX = board.getTotemX();
        Player pinkPlayer = new Player(Color.PINK);

        assertFalse(board.isValidMove(totemX, -1, -2, pinkPlayer));
    }
    @Test
    void test_methodReturnFalseIfBoxNotEmpty() {
        Totem totemX = board.getTotemX();
        Player pinkPlayer = new Player(Color.PINK);

        board.placePieceAt(1, 2, new Token(Symbol.X, Color.BLACK));

        assertFalse(board.isValidMove(totemX, 1, 2, pinkPlayer));
    }

    // boxExist()
    @Test
    void test_methodReturnTrueFor0x0() {
        assertTrue(board.boxExist(0, 0));
    }
    @Test
    void test_methodReturnFalseIfBoxNegative() {
        assertFalse(board.boxExist(-1, 2));
    }
    @Test
    void test_methodReturnFalseIfBoxOutOfBoard() {
        assertFalse(board.boxExist(boardSize, boardSize));
    }

    // insertToken()
    @Test
    void test_insertTokenReturnTrueIfInsertIsValid() {
        // doesn't work
        Totem totemX = board.getTotemX();
        Player pinkPlayer = new Player(Color.PINK);

        assertTrue(board.insertToken(0, 0, totemX, pinkPlayer));
        assertNotNull(board.getPieceAt(0, 0));
    }
    @Test
    void test_insertTokenReturnFalseIfBoxNotEmpty() {
        Totem totemX = board.getTotemX();
        Player pinkPlayer = new Player(Color.PINK);

        board.placePieceAt(0, 0, pinkPlayer.getTokenX());

        assertFalse(board.insertToken(0, 0, totemX, pinkPlayer));
    }

    // positionsAvailableForTotem()
    @Test
    void test_methodReturnAllAdjacentBoxIfTotemNotEnclave() {
        Board board = new Board(6);
        Player pinkPlayer = new Player(Color.PINK);
        // totem position : (1,1)
        int[][] allPositionsAvailable = {
                {0, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {1, 0}, {1, 2}, {1, 3}, {1, 4}, {1, 5}
        };

        board.moveTotem(board.getTotemX(), 1, 2, pinkPlayer);
        board.moveTotem(board.getTotemX(), 1, 1, pinkPlayer);

        assertArrayEquals(allPositionsAvailable, board.positionsAvailableForTotem(1, 1, false));
    }

    // isEnclave()
    @Test
    void test_isEnclaveReturnTrue() {
        Totem totemX = board.getTotemX();
        Player pinkPlayer = new Player(Color.PINK);

        board.moveTotem(totemX, 1, 2, pinkPlayer);
        board.placePieceAt(1, 1, new Token(Symbol.X, Color.BLACK));
        board.placePieceAt(0, 2, new Token(Symbol.X, Color.BLACK));
        board.placePieceAt(1, 3, new Token(Symbol.X, Color.BLACK));
        board.placePieceAt(2, 2, new Token(Symbol.X, Color.BLACK));

        assertTrue(board.isEnclave(1, 2));
    }
    @Test
    void test_isEnclaveReturnFalse() {
        Totem totemX = board.getTotemX();
        Player pinkPlayer = new Player(Color.PINK);

        board.moveTotem(totemX, 1, 2, pinkPlayer);
        board.placePieceAt(1, 1, new Token(Symbol.X, Color.BLACK));
        board.placePieceAt(1, 3, new Token(Symbol.X, Color.BLACK));
        board.placePieceAt(2, 2, new Token(Symbol.X, Color.BLACK));

        assertFalse(board.isEnclave(1, 2));
    }

    // moveTotem()
    @Test
    void test_moveTotemIsValid() {
        Board board = new Board(6);
        Player pinkPlayer = new Player(Color.PINK);

        assertTrue(board.moveTotem(board.getTotemX(), 1, 2, pinkPlayer));
    }

    // checkAlignment()
    @Test
    void test_horizontalAlignment() {
        Token token1 = new Token(Symbol.O, Color.PINK);
        Token token2 = new Token(Symbol.X, Color.PINK);

        board.placePieceAt(1, 0, token2);
        board.placePieceAt(1, 1, token1);
        board.placePieceAt(1, 2, token2);
        board.placePieceAt(1, 3, token1);
        assertTrue(board.checkAlignment(1, 3, token1));
    }
    @Test
    void test_trueIfHorizontalSameColorAndSameSymbol() {
        Token token = new Token(Symbol.O, Color.PINK);

        board.placePieceAt(1, 0, token);
        board.placePieceAt(1, 1, token);
        board.placePieceAt(1, 2, token);
        board.placePieceAt(1, 3, token);
        assertTrue(board.checkAlignment(1, 3, token));
    }
    @Test
    void test_trueIfHorizontalSameColorAndDifferentSymbol() {
        Token token1 = new Token(Symbol.O, Color.PINK);
        Token token2 = new Token(Symbol.X, Color.PINK);

        board.placePieceAt(1, 0, token2);
        board.placePieceAt(1, 1, token1);
        board.placePieceAt(1, 2, token2);
        board.placePieceAt(1, 3, token1);
        assertTrue(board.checkAlignment(1, 3, token1));
    }
    @Test
    void test_trueIfHorizontalDifferentColorAndSameSymbol() {
        Token token1 = new Token(Symbol.O, Color.PINK);
        Token token2 = new Token(Symbol.O, Color.BLACK);

        board.placePieceAt(1, 0, token2);
        board.placePieceAt(1, 1, token1);
        board.placePieceAt(1, 2, token2);
        board.placePieceAt(1, 3, token1);
        assertTrue(board.checkAlignment(1, 3, token1));
    }
    @Test
    void test_falseIfHorizontalDifferentColorAndDifferentSymbol() {
        Token token1 = new Token(Symbol.O, Color.PINK);
        Token token2 = new Token(Symbol.X, Color.BLACK);

        board.placePieceAt(1, 0, token2);
        board.placePieceAt(1, 1, token1);
        board.placePieceAt(1, 2, token2);
        board.placePieceAt(1, 3, token1);
        assertFalse(board.checkAlignment(1, 3, token1));
    }
    @Test
    void test_trueIfVerticalSameColorAndSameSymbol() {
        Token token = new Token(Symbol.O, Color.PINK);

        board.placePieceAt(0, 1, token);
        board.placePieceAt(1, 1, token);
        board.placePieceAt(2, 1, token);
        board.placePieceAt(3, 1, token);
        assertTrue(board.checkAlignment(3, 1, token));
    }
    @Test
    void test_trueIfVerticalSameColorAndDifferentSymbol() {
        Token token1 = new Token(Symbol.O, Color.PINK);
        Token token2 = new Token(Symbol.X, Color.PINK);

        board.placePieceAt(0, 1, token1);
        board.placePieceAt(1, 1, token2);
        board.placePieceAt(2, 1, token1);
        board.placePieceAt(3, 1, token2);
        assertTrue(board.checkAlignment(2, 1, token2));
    }
    @Test
    void test_trueIfVerticalDifferentColorAndSameSymbol() {
        Token token1 = new Token(Symbol.X, Color.BLACK);
        Token token2 = new Token(Symbol.X, Color.PINK);

        board.placePieceAt(0, 1, token1);
        board.placePieceAt(1, 1, token2);
        board.placePieceAt(2, 1, token1);
        board.placePieceAt(3, 1, token2);
        assertTrue(board.checkAlignment(2, 1, token2));
    }
    @Test
    void test_falseIfVerticalDifferentColorAndDifferentSymbol() {
        Token token1 = new Token(Symbol.O, Color.PINK);
        Token token2 = new Token(Symbol.X, Color.BLACK);

        board.placePieceAt(0, 1, token1);
        board.placePieceAt(1, 1, token2);
        board.placePieceAt(2, 1, token1);
        board.placePieceAt(3, 1, token2);
        assertFalse(board.checkAlignment(2, 1, token2));
    }
}