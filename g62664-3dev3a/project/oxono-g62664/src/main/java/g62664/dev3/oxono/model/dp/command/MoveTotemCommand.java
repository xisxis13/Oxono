package g62664.dev3.oxono.model.dp.command;

import g62664.dev3.oxono.model.Oxono;
import g62664.dev3.oxono.model.Totem;

public class MoveTotemCommand implements Command {
    // attribute
    private final Oxono facade;
    private final Totem totemToMove;
    private final int[] previousPosition;
    private final int xPosition;
    private final int yPosition;

    // constructor
    public MoveTotemCommand(Oxono facade, Totem totemToMove, int[] previousPosition,
                            int xPosition, int yPosition) {
        this.facade = facade;
        this.totemToMove = totemToMove;
        this.previousPosition = previousPosition;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    // methods
    @Override
    public boolean execute() {
        return facade.moveTotem(totemToMove, xPosition, yPosition, facade.getCurrentPlayer());
    }
    @Override
    public void unexecute() {
        facade.placePieceAt(previousPosition[0], previousPosition[1], totemToMove);
        facade.removePieceAt(xPosition, yPosition);
    }
}
