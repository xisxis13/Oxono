package g62664.dev3.oxono.model;

public class Game {
    // attributes
    private final Board board;
    private final Player pinkPlayer;
    private final Player blackPlayer;
    private Player currentPlayer;
    private Totem lastTotemMoved;
    private Token lastTokenPlaced;
    private int xPositionOfLastTokenPlaced;
    private int yPositionOfLastTokenPlaced;

    // constructor
    public Game(int boardSize) {
        this.board = new Board(boardSize);
        this.pinkPlayer = new Player(Color.PINK);
        this.blackPlayer = new Player(Color.BLACK);
        this.currentPlayer = this.pinkPlayer;
        this.lastTotemMoved = board.getTotemX();
    }

    // getters
    public Board getBoard() {
        return board;
    }
    public Player getPinkPlayer() {
        return pinkPlayer;
    }
    public Player getBlackPlayer() {
        return blackPlayer;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public Totem getLastTotemMoved(){
        return lastTotemMoved;
    }
    public Token getLastTokenPlaced() {
        return lastTokenPlaced;
    }
    public int getxPositionOfLastTokenPlaced() {
        return xPositionOfLastTokenPlaced;
    }
    public int getyPositionOfLastTokenPlaced() {
        return yPositionOfLastTokenPlaced;
    }

    // setter
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public void setLastTotemMoved(Totem lastTotemMoved) {
        this.lastTotemMoved = lastTotemMoved;
    }
    public void setLastTokenPlaced(Token lastTokenPlaced) {
        this.lastTokenPlaced = lastTokenPlaced;
    }

    // methods
    /**
     * check if the party is over
     * @param x - x position of last token inserted
     * @param y - y position of last token inserted
     * @param lastTokenInserted - last token inserted
     * @return true if the party is over, else false
     */
    public boolean isFinish(int x, int y, Token lastTokenInserted) {
        if (board.checkAlignment(x, y, lastTokenInserted)) {
            return true;
        } else {
            return !board.checkAlignment(x, y, lastTokenInserted) && pinkPlayer.getNbTokensO() == 0
                    && pinkPlayer.getNbTokensX() == 0 && blackPlayer.getNbTokensX() == 0
                    && blackPlayer.getNbTokensO() == 0;
        }
    }

    /**
     * give the winner
     * @return the winner
     */
    public Player whoWin() {
        return currentPlayer;
    }

    /**
     * switch the current player
     */
    public void switchCurrentPlayer() {
        if (currentPlayer == pinkPlayer) {
            setCurrentPlayer(blackPlayer);
        } else {
            setCurrentPlayer(pinkPlayer);
        }
    }
}
