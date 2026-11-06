package g62664.dev3.oxono.controller;

import g62664.dev3.oxono.model.*;
import g62664.dev3.oxono.model.GameState;
import g62664.dev3.oxono.model.dp.command.CommandManager;
import g62664.dev3.oxono.model.dp.command.InsertTokenCommand;
import g62664.dev3.oxono.model.dp.command.MoveTotemCommand;
import g62664.dev3.oxono.model.dp.strategy.Level0Strategy;
import g62664.dev3.oxono.model.dp.strategy.NoIAStrategy;
import g62664.dev3.oxono.model.dp.strategy.Strategy;
import g62664.dev3.oxono.view.FXMainView;
import javafx.application.Platform;

public class FXController {
    // attributes
    private Oxono facade;
    private FXMainView view;
    private Totem totemSelected;
    private boolean placingTotem;
    private int gridSize;
    private final CommandManager commandManager;

    // constructor
    public FXController() {
        this.totemSelected = null;
        this.placingTotem = false;
        this.commandManager = new CommandManager(this);
    }

    // getters
    public Oxono getFacade() {
        return facade;
    }
    public Board getBoard() {
        return facade.getBoard();
    }
    public CommandManager getCommandManager() {
        return commandManager;
    }

    // methods
    public void handleGridClick(int col, int row) {
        Platform.runLater(() -> {
            Piece piece = facade.getPieceAt(col, row);

            if (facade.getState() == GameState.MOVE) {
                if (piece instanceof Totem) {
                    if (totemSelected == null && !placingTotem) {
                        totemSelected = (Totem) piece;
                        placingTotem = true;

                        int[][] positionsAvailable = facade.positionsAvailableForTotem(col, row,
                                facade.isEnclave(col, row));
                        // view.getFxBoard().showAvailablePositions(positionsAvailable);
                    }
                } else if (piece instanceof Token) {
                    view.showMessage(ErrorType.INVALID_MOVE, facade);
                } else {
                    if (placingTotem && totemSelected != null) {
                        MoveTotemCommand moveCommand = new MoveTotemCommand(facade, totemSelected,
                                getBoard().getTotemPosition(totemSelected), col, row);

                        if (commandManager.doIt(moveCommand)) {
                            facade.setState(GameState.INSERT);
                            facade.getGame().setLastTotemMoved(totemSelected);
                            placingTotem = false;
                            totemSelected = null;
                        } else {
                            view.showMessage(ErrorType.INVALID_MOVE, facade);
                        }
                    }
                }
            } else if (facade.getState() == GameState.INSERT) {
                if (piece == null) {
                    InsertTokenCommand insertCommand = new InsertTokenCommand(facade,
                            facade.getGame().getLastTotemMoved(), col, row);

                    if (commandManager.doIt(insertCommand)) {
                        facade.getGame().setLastTokenPlaced((Token) facade.getPieceAt(col, row));

                        view.getFxGameStats().updateGameStats(facade);

                        if (facade.isFinish(col, row, facade.getGame().getLastTokenPlaced())) {
                            facade.setState(GameState.END);
                            view.showMessage(ErrorType.NO_ERROR, facade);
                        } else {
                            if (facade.getStrategy() instanceof Level0Strategy) {
                                facade.getStrategy().play(facade, commandManager);
                            }

                            facade.setState(GameState.MOVE);
                        }
                    } else {
                        view.showMessage(ErrorType.INVALID_INSERT, facade);
                    }
                }
            }
        });
    }
    public void initializeStartGameButton(GridSize gridSize, Level iaLevel) {
        this.gridSize = gridSize.getSize();

        Strategy strategy = null;

        if (iaLevel == Level.no_IA) {
            strategy = new NoIAStrategy();
        } else if (iaLevel == Level.level_0) {
            strategy = new Level0Strategy();
        }

        this.facade = new Oxono(this.gridSize, strategy);
        this.view = new FXMainView(this);
    }
    public void resetMove() {
        totemSelected = null;
        placingTotem = false;
    }
}
