package g62664.dev3.oxono.model.dp.strategy;

import g62664.dev3.oxono.model.Board;
import g62664.dev3.oxono.model.Oxono;
import g62664.dev3.oxono.model.Totem;
import g62664.dev3.oxono.model.dp.command.CommandManager;
import g62664.dev3.oxono.model.dp.command.InsertTokenCommand;
import g62664.dev3.oxono.model.dp.command.MoveTotemCommand;

import java.util.Random;

public class Level0Strategy implements Strategy {
    // attribute
    private final Random random = new Random();

    // method
    @Override
    public void play(Oxono facade, CommandManager commandManager) {
        Board board = facade.getBoard();
        int size = board.getBoardSize();
        int col, row;

        Totem totem;

        if (random.nextBoolean()) {
            totem = facade.getBoard().getTotemX();
        } else {
            totem = facade.getBoard().getTotemO();
        }

        MoveTotemCommand moveCommand;
        InsertTokenCommand insertCommand;

        do {
            col = random.nextInt(size);
            row = random.nextInt(size);

            moveCommand = new MoveTotemCommand(facade, totem,
                    facade.getTotemPosition(totem), col, row);

        } while (!board.isEmpty(col, row) || !commandManager.doIt(moveCommand));

        do {
            col = random.nextInt(size);
            row = random.nextInt(size);
            
            insertCommand = new InsertTokenCommand(facade, facade.getGame().getLastTotemMoved(), col, row);

        } while (!board.isEmpty(col, row) || !commandManager.doIt(insertCommand));
    }
}
