package g62664.dev3.oxono.model.dp.strategy;

import g62664.dev3.oxono.model.Oxono;
import g62664.dev3.oxono.model.dp.command.CommandManager;

public interface Strategy {
    // method
    void play(Oxono facade, CommandManager commandManager);
}
