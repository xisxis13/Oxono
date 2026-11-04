package g62664.dev3.oxono.model.dp.command;

import g62664.dev3.oxono.controller.FXController;
import g62664.dev3.oxono.model.GameState;

import java.util.Stack;

public class CommandManager {
    // attributes
    private final FXController controller;
    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;

    // constructor
    public CommandManager(FXController controller) {
        this.controller = controller;
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    // methods
    public boolean doIt(Command command) {
        undoStack.add(command);
        redoStack.clear();

        return command.execute();
    }
    public void undo() {
        if (undoStack.isEmpty()) {
            throw new IllegalStateException("There is nothing to undo");
        } else {
            Command c = undoStack.pop();
            c.unexecute();
            redoStack.push(c);

            if (c instanceof MoveTotemCommand) {
                controller.getFacade().setState(GameState.MOVE);
            } else if (c instanceof InsertTokenCommand) {
                controller.getFacade().setState(GameState.INSERT);
                controller.getFacade().switchCurrentPlayer();
            }
        }
    }
    public void redo() {
        if (redoStack.isEmpty()) {
            throw new IllegalStateException("There is nothing to redo");
        } else {
            Command c = redoStack.pop();
            c.execute();
            undoStack.push(c);

            if (c instanceof InsertTokenCommand) {
                controller.resetMove();
            }
        }
    }
}
