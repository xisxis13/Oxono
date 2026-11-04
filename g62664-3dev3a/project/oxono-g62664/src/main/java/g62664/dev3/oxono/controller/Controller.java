package g62664.dev3.oxono.controller;

import g62664.dev3.oxono.model.Oxono;
import g62664.dev3.oxono.model.Symbol;
import g62664.dev3.oxono.model.Token;
import g62664.dev3.oxono.model.Totem;
import g62664.dev3.oxono.view.ConsoleView;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    // attributes
    private static final Scanner user = new Scanner(System.in);
    private Oxono facade;
    private ConsoleView view;

    // constructor
    public Controller(Oxono facade) {
        this.facade = facade;
        this.view = new ConsoleView(this.facade);
        /*
        initialiser :
        - les joueurs
        - la partie
        - le board
        - l'invoker
        */
    }

    // methods
    public void start() {
        String userInput;
        view.showWelcome();

        do {
            view.showGameStats();
            view.showBoard();
            System.out.println("You can move a totem.");
            view.showCommands();
            System.out.println("What do you want to do ? : ");

            userInput = user.nextLine();

            while (!askMoveTotem(userInput)) {
                userInput = user.nextLine();
            }

            view.showBoard();
            System.out.println("You can place one of your token.");
            view.showCommands();
            System.out.println("What do you want to do ? : ");

            userInput = user.nextLine();

            while (!askPlaceToken(userInput)) {
                userInput = user.nextLine();
            }

            facade.switchCurrentPlayer();
        } while (!facade.isFinish(facade.getGame().getxPositionOfLastTokenPlaced(),
                facade.getGame().getyPositionOfLastTokenPlaced(), facade.getGame().getLastTokenPlaced()));

        view.showBoard();
        view.showWinner(facade.whoWin());
    }
    public boolean askMoveTotem(String userInput) {
        String regex = "^(undo|redo|quit|[XxOo] [0-6] [0-6])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.matches()) {
            String command = matcher.group(1);

            switch (command) {
                case "undo":
                    // undo last action
                    System.out.println("Undo last action");
                    return false;
                case "redo":
                    // redo last action
                    System.out.println("Redo last action");
                    return false;
                default:
                    // move the totem
                    String[] arguments = command.split(" ");
                    Totem totemToMove = null;

                    if (arguments[0].equalsIgnoreCase("x")) {
                        totemToMove = facade.getGame().getBoard().getTotemX();
                    } else if (arguments[0].equalsIgnoreCase("o")) {
                        totemToMove = facade.getGame().getBoard().getTotemO();
                    }

                    if (facade.moveTotem(totemToMove, Integer.parseInt(arguments[1])-1,
                            Integer.parseInt(arguments[2])-1, facade.getCurrentPlayer())) {
                        System.out.println("Success to move the totem");
                        facade.getGame().setLastTotemMoved(totemToMove);
                        return true;
                    } else {
                        System.out.println("You can't move this totem there");
                        return false;
                    }
            }
        }

        System.out.println("Invalid command");
        return false;
    }
    public boolean askPlaceToken(String userInput) {
        String regex = "^(undo|redo|quit|[XxOo] [0-6] [0-6])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.matches()) {
            String command = matcher.group(1);

            switch (command) {
                case "undo":
                    // undo last action
                    System.out.println("Undo last action");
                    return false;
                case "redo":
                    // redo last action
                    System.out.println("Redo last action");
                    return false;
                default:
                    // move the totem
                    String[] arguments = command.split(" ");
                    Token tokenToInsert = null;

                    if (arguments[0].equalsIgnoreCase("x")) {
                        tokenToInsert = facade.getGame().getCurrentPlayer().getTokenX();
                    } else if (arguments[0].equalsIgnoreCase("o")) {
                        tokenToInsert = facade.getGame().getCurrentPlayer().getTokenO();
                    }

//                    if (facade.insertToken(tokenToInsert, Integer.parseInt(arguments[1])-1,
//                            Integer.parseInt(arguments[2])-1, facade.getGame().getLastTotemMoved())) {
//
//                        facade.getGame().setLastTokenPlaced(tokenToInsert);
//                        facade.getGame().setxPositionOfLastTokenPlaced(Integer.parseInt(arguments[1])-1);
//                        facade.getGame().setyPositionOfLastTokenPlaced(Integer.parseInt(arguments[2])-1);
//
//                        if (tokenToInsert.getSymbol() == Symbol.O) {
//                            facade.decreaseTokensO();
//                        } else if (tokenToInsert.getSymbol() == Symbol.X) {
//                            facade.decreaseTokensX();
//                        }
//
//                        System.out.println("Success to place the token");
//                        return true;
//                    } else {
//                        System.out.println("You can't move this token there");
//                        return false;
//                    }
            }
        }

        System.out.println("Invalid command");
        return false;
    }

}
