package g62664.dev3.oxono.model.dp.command;

import g62664.dev3.oxono.model.Oxono;
import g62664.dev3.oxono.model.Token;
import g62664.dev3.oxono.model.Totem;

public class InsertTokenCommand implements Command {
    // attribute
    private final Oxono facade;
    private final Totem lastTotemMoved;
    private final int xPosition;
    private final int yPosition;

    // constructor
    public InsertTokenCommand(Oxono facade, Totem lastTotemMoved,
                              int xPosition, int yPosition) {
        this.facade = facade;
        this.lastTotemMoved = lastTotemMoved;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    // methods
    @Override
    public boolean execute() {
        return facade.insertToken(xPosition, yPosition, lastTotemMoved);
    }
    @Override
    public void unexecute() {
        Token tokenToRemove = (Token) facade.getPieceAt(xPosition, yPosition);
        facade.removePieceAt(xPosition, yPosition);

        switch (tokenToRemove.getSymbol()) {
            case X -> facade.increaseTokensX();
            case O -> facade.increaseTokensO();
        }
    }
}
