package g62664.dev3.oxono.view;

import g62664.dev3.oxono.model.*;

public class ConsoleView {
    // attribute
    private final Oxono facade;

    // constructor
    public ConsoleView(Oxono facade) {
        this.facade = facade;
    }

    // methods
    public void showWelcome() {
        System.out.println("***** OXONO *****");
        System.out.println("-----------------");
    }
    public void showCommands() {
        System.out.println("-> move/play a piece : <pieceSymbol> <xPosition> <yPosition>");
        System.out.println("-> undo last action : 'undo'");
        System.out.println("-> redo last action : 'redo'");
        System.out.println("-> quit game : 'quit'");
    }
    public void showBoard() {
        Board board = facade.getGame().getBoard();
        String boardString = "";
        boardString = boardString.concat("  1 2 3 4 5 6");
        boardString = boardString.concat("\n");

        for (int i = 0; i < 6; i++) {
            boardString = boardString.concat((i+1) + " ");

            for (int j = 0; j < 6; j++) {
                if (board.isEmpty(i, j)) {
                    boardString = boardString.concat("  ");

                } else if (board.getPieceAt(i, j).getSymbol() == Symbol.O) {
                    if (board.getPieceAt(i, j).getColor() == Color.BLUE) {
                        boardString = boardString.concat("\u001B[36m" + "O " + "\u001B[0m");
                    } else if (board.getPieceAt(i, j).getColor() == Color.PINK) {
                        boardString = boardString.concat("\u001B[35m" + "O " + "\u001B[0m");
                    } else {
                        boardString = boardString.concat("O ");
                    }

                } else if (board.getPieceAt(i, j).getSymbol() == Symbol.X) {
                    if (board.getPieceAt(i, j).getColor() == Color.BLUE) {
                        boardString = boardString.concat("\u001B[36m" + "X " + "\u001B[0m");
                    } else if (board.getPieceAt(i, j).getColor() == Color.PINK) {
                        boardString = boardString.concat("\u001B[35m" + "X " + "\u001B[0m");
                    } else {
                        boardString = boardString.concat("X ");
                    }
                }
            }

            boardString = boardString.concat("\n");
        }

        System.out.println(boardString);
    }
    public void showGameStats() {
        System.out.println(facade.getGame().getPinkPlayer());
        System.out.println(facade.getGame().getBlackPlayer());
    }
    public void showWinner(Player winner) {
        System.out.println("Le gagnant est : " + winner);
    }
}
