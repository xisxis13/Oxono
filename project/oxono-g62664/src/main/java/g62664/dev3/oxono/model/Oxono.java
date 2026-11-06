package g62664.dev3.oxono.model;

import g62664.dev3.oxono.model.dp.command.MoveTotemCommand;
import g62664.dev3.oxono.model.dp.observer.Observable;
import g62664.dev3.oxono.model.dp.observer.Observer;
import g62664.dev3.oxono.model.dp.strategy.Strategy;

import java.util.ArrayList;
import java.util.List;

public class Oxono implements Observable {
    // attributes
    private final Game game;
    private GameState state;
    private final List<Observer> observers;
    private final Strategy strategy;

    // constructor
    public Oxono(int gridSize, Strategy strategy) {
        this.game = new Game(gridSize);
        this.state = GameState.MOVE;
        this.observers = new ArrayList<>();
        this.strategy = strategy;
    }

    // getters
    public Game getGame() {
        return game;
    }
    public GameState getState() {
        return state;
    }
    public Board getBoard() {
        return game.getBoard();
    }
    public Strategy getStrategy() {
        return strategy;
    }
    public Player getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    // setter
    public void setState(GameState state) {
        this.state = state;
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
       if (game.isFinish(x, y, lastTokenInserted)) {
           switchCurrentPlayer();
           notifyObservers();
           return true;
       }

       return false;
    }

    /**
     * give the winner
     * @return the winner
     */
    public Player whoWin() {
        return game.whoWin();
    }

    /**
     * return piece at position (x,y) in board
     * @param x - absolute position x in board : (0,0) is the origin and (5,5) is the extremity
     * @param y - absolute position y in board
     * @return the piece at position (x,y) or null if there is no piece
     */
    public Piece getPieceAt(int x, int y) {
        return game.getBoard().getPieceAt(x, y);
    }

    /**
     * move a totem
     * @param totem - totem to move
     * @param x - x position
     * @param y - y position
     * @param currentPlayer - current player
     * @return true if the totem has been moved
     */
    public boolean moveTotem(Totem totem, int x, int y, Player currentPlayer) {
        if (game.getBoard().moveTotem(totem, x, y, currentPlayer)) {
            game.setLastTotemMoved(totem);
            setState(GameState.INSERT);
            notifyObservers();

            return true;
        }

        return false;
    }

    /**
     * insert a token where we want
     * @param x - x position of where we want to insert
     * @param y - y position of where we want to insert
     * @param lastTotemMoved - last totem moved
     * @return true if the token has been insert, else false
     */
    public boolean insertToken(int x, int y, Totem lastTotemMoved) {
        if (game.getBoard().insertToken(x, y, lastTotemMoved, getCurrentPlayer())) {
            setState(GameState.MOVE);
            switchCurrentPlayer();
            notifyObservers();

            return true;
        }

        return false;
    }

    /**
     * check if a totem is enclave or not
     * @param x - x position of totem
     * @param y - y position of totem
     * @return true if totem is enclave, else false
     */
    public boolean isEnclave(int x, int y) {
        return game.getBoard().isEnclave(x, y);
    }

    /**
     * check the position available for a totem
     * @param x - current x position of totem
     * @param y - current y position of totem
     * @param isEnclave - true if totem is enclave, else false
     * @return all the positions available for a totem
     */
    public int[][] positionsAvailableForTotem(int x, int y, boolean isEnclave) {
        return game.getBoard().positionsAvailableForTotem(x, y, isEnclave);
    }

    /**
     * switch the current player
     */
    public void switchCurrentPlayer() {
        game.switchCurrentPlayer();
        notifyObservers();
    }

    /**
     * increase the number of token O of the current player (in case of 'undo')
     */
    public void increaseTokensO() {
        Player player = null;

        if (getCurrentPlayer() == getGame().getPinkPlayer()) {
            player = getGame().getBlackPlayer();
        } else if (getCurrentPlayer() == getGame().getBlackPlayer()) {
            player = getGame().getPinkPlayer();
        }

        player.increaseTokensO();
    }

    /**
     * increase the number of token X of the current player (in case of 'undo')
     */
    public void increaseTokensX() {
        Player player = null;

        if (getCurrentPlayer() == getGame().getPinkPlayer()) {
            player = getGame().getBlackPlayer();
        } else if (getCurrentPlayer() == getGame().getBlackPlayer()) {
            player = getGame().getPinkPlayer();
        }

        player.increaseTokensX();
    }

    /**
     * search the position of a totem
     * @param totem - totem to search position
     * @return the position of the totem in parameter
     */
    public int[] getTotemPosition(Totem totem) {
        return getBoard().getTotemPosition(totem);
    }

    /**
     * place a piece, without consulting rules
     * @param x - x position
     * @param y - y position
     * @param piece - piece to insert
     */
    public void placePieceAt(int x, int y, Piece piece) {
        game.getBoard().placePieceAt(x, y, piece);
    }

    /**
     * remove the piece at position (x,y)
     * @param x - x position
     * @param y - y position
     */
    public void removePieceAt(int x, int y) {
        game.getBoard().removePieceAt(x, y);
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
