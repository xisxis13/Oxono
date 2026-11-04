package g62664.dev3.oxono.model;

import java.util.Arrays;

public class Board {
    // attributes
    private final Piece[][] board;
    private final Totem totemX;
    private final Totem totemO;

    // constructor
    public Board(int size) {
        this.board = new Piece[size][size];
        this.totemX = new Totem(Symbol.X);
        this.totemO = new Totem(Symbol.O);

        board[size/2-1][size/2-1] = this.totemX;
        board[size/2][size/2] = this.totemO;
    }

    // getters
    public Totem getTotemX() {
        return totemX;
    }
    public Totem getTotemO() {
        return totemO;
    }
    public int getBoardSize() {
        return board.length;
    }

    // methods
    /**
     * return piece at position (x,y) in board
     * @param x - absolute position x in board : (0,0) is the origin and (5,5) is the extremity
     * @param y - absolute position y in board
     * @return the piece at position (x,y) or null if there is no piece
     */
    public Piece getPieceAt(int x, int y) {
        return board[x][y];
    }

    /**
     * search the position of a totem
     * @param totem - totem to search position
     * @return the position of the totem in parameter
     */
    public int[] getTotemPosition(Totem totem) {
        int[] positionTotem = new int[2];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (getPieceAt(i, j) == totem) {
                    positionTotem[0] = i;
                    positionTotem[1] = j;
                }
            }
        }

        return positionTotem;
    }

    /**
     * check if a box is empty
     * @param x - x position of the box
     * @param y - y position of the box
     * @return true if the box is empty, else false
     */
    public boolean isEmpty(int x, int y) {
        return getPieceAt(x, y) == null;
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
        if (isValidMove(totem, x, y, currentPlayer)) {
            int[] previousPositionTotem = getTotemPosition(totem);
            board[x][y] = totem;
            board[previousPositionTotem[0]][previousPositionTotem[1]] = null;
            return true;
        }

        return false;
    }

    /**
     * check if we can move the totem at the place we want
     * @param totem - totem we want to move
     * @param x - x position
     * @param y - y position
     * @param currentPlayer - currentPlayer
     * @return - true if we can move the totem at the place we want, else false
     */
    public boolean isValidMove(Totem totem, int x, int y, Player currentPlayer) {
        int[] actualPositionTotem = getTotemPosition(totem);
        int[][] positionsAvailable = positionsAvailableForTotem(actualPositionTotem[0],
                actualPositionTotem[1], isEnclave(actualPositionTotem[0], actualPositionTotem[1]));
        boolean positionWantedIsOk = false;

        if (totem.getSymbol() == Symbol.X) {
            if (currentPlayer.getNbTokensX() == 0) {
                return false;
            }
        } else if (totem.getSymbol() == Symbol.O) {
            if (currentPlayer.getNbTokensO() == 0) {
                return false;
            }
        }

        for (int[] positionAvailable : positionsAvailable) {
            if (positionAvailable[0] == x && positionAvailable[1] == y) {
                positionWantedIsOk = true;
                break;
            }
        }

        return positionWantedIsOk;
    }

    /**
     * check the position available for a totem
     * @param x - current x position of totem
     * @param y - current y position of totem
     * @param isEnclave - true if totem is enclave, else false
     * @return all the positions available for a totem
     */
    public int[][] positionsAvailableForTotem(int x, int y, boolean isEnclave) {
        int[][] positionAvailable = new int[(board.length*2)-2][2];

        for (int i = 0; i < positionAvailable.length; i++) {
            positionAvailable[i][0] = -1;
            positionAvailable[i][1] = -1;
        }

        int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        int i = 0;

        if (!isEnclave) {
            for (int[] direction : directions) {
                int adjacentX = x + direction[0];
                int adjacentY = y + direction[1];

                while (boxExist(adjacentX, adjacentY) && isEmpty(adjacentX, adjacentY)) {
                    positionAvailable[i][0] = adjacentX;
                    positionAvailable[i][1] = adjacentY;
                    i++;
                    adjacentX = adjacentX + direction[0];
                    adjacentY = adjacentY + direction[1];
                }
            }
        } else {
            for (int[] direction : directions) {
                int adjacentX = x + direction[0];
                int adjacentY = y + direction[1];

                while (boxExist(adjacentX, adjacentY) && !isEmpty(adjacentX, adjacentY)) {
                    adjacentX = adjacentX + direction[0];
                    adjacentY = adjacentY + direction[1];
                }

                if (boxExist(adjacentX, adjacentY) && isEmpty(adjacentX, adjacentY)) {
                    positionAvailable[i][0] = adjacentX;
                    positionAvailable[i][1] = adjacentY;
                    i++;
                }
            }

            if (x != 0 && y != 0 || isEmpty(0, 0)) {
                if (positionAvailable[0][0] == 0 && positionAvailable[0][1] == 0) {
                    int counter = 0;
                    for (int a = 0; a < board.length; a++) {
                        for (int b = 0; b < board.length; b++) {
                            if (isEmpty(a, b)) {
                                positionAvailable[counter][0] = a;
                                positionAvailable[counter][1] = b;
                            }

                            counter++;
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(positionAvailable));
        return positionAvailable;
    }

    /**
     * check if a totem is enclave or not
     * @param x - x position of totem
     * @param y - y position of totem
     * @return true if totem is enclave, else false
     */
    public boolean isEnclave(int x, int y) {
        int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};

        for (int[] direction : directions) {
            int adjacentX = x + direction[0];
            int adjacentY = y + direction[1];

            if (boxExist(adjacentX, adjacentY)) {
                if (isEmpty(adjacentX, adjacentY)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * insert a token where we want
     * @param x - x position of where we want to insert
     * @param y - y position of where we want to insert
     * @param lastTotemMoved - last totem moved
     * @return true if the token has been insert, else false
     */
    public boolean insertToken(int x, int y, Totem lastTotemMoved, Player currentPlayer) {
        if (isValidInsert(x, y, lastTotemMoved)) {
            if (lastTotemMoved.getSymbol() == Symbol.O) {
                board[x][y] = currentPlayer.getTokenO();
                currentPlayer.decreaseTokensO();
            } else {
                board[x][y] = currentPlayer.getTokenX();
                currentPlayer.decreaseTokensX();
            }

            return true;
        }

        return false;
    }

    /**
     * check if we can insert a token where we want
     * @param x - x position of where we want to insert
     * @param y - y position of where we want to insert
     * @param lastTotemMoved - last totem moved
     * @return true if we can insert token where we want, else false
     */
    public boolean isValidInsert(int x, int y, Totem lastTotemMoved) {
        int[] positionLastTotemMoved = getTotemPosition(lastTotemMoved);

        if (isEnclave(positionLastTotemMoved[0], positionLastTotemMoved[1])) {
            return boxExist(x, y) && isEmpty(x, y);
        }

        int[][] positionsAvailable = positionsAvailableForToken(positionLastTotemMoved[0],
                positionLastTotemMoved[1]);
        boolean positionWantedIsOk = false;

        for (int[] positionAvailable : positionsAvailable) {
            if (positionAvailable[0] == x && positionAvailable[1] == y) {
                positionWantedIsOk = true;
                break;
            }
        }

        return positionWantedIsOk;
    }

    /**
     * check the position available for a token
     * @param x - current x position of last totem moved
     * @param y - current y position of last totem moved
     * @return all the positions available for a token
     */
    public int[][] positionsAvailableForToken(int x, int y) {
        int[][] positionAvailable = new int[4][2];

        for (int i = 0; i < positionAvailable.length; i++) {
            positionAvailable[i][0] = -1;
            positionAvailable[i][1] = -1;
        }

        int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        int i = 0;

        for (int[] direction : directions) {
            int adjacentX = x + direction[0];
            int adjacentY = y + direction[1];

            if (boxExist(adjacentX, adjacentY) && isEmpty(adjacentX, adjacentY)) {
                positionAvailable[i][0] = adjacentX;
                positionAvailable[i][1] = adjacentY;
                i++;
            }
        }

        return positionAvailable;
    }

    /**
     * check if there is 4 tokens align with the same color or same symbol
     * @param x - x position of last token inserted
     * @param y - y position of last token inserted
     * @param lastTokenInserted - last token inserted
     * @return true if there is 4 tokens align with the same color or same symbol, else false
     */
    public boolean checkAlignment(int x, int y, Token lastTokenInserted) {
        return checkDirection(x, y, lastTokenInserted, 1, 0) ||
                checkDirection(x, y, lastTokenInserted, 0, 1);
    }

    /**
     * check for consecutive tokens in a specified direction
     * @param x - x coordinate to start the check
     * @param y - y coordinate to start the check
     * @param lastTokenInserted - last token placed
     * @param dx - x direction to check
     * @param dy - y direction to check
     * @return true if four or more consecutive tokens of the same symbol or same color are aligned, else false
     */
    public boolean checkDirection(int x, int y, Token lastTokenInserted, int dx, int dy) {
        int countSymbol = 1;
        int countColor = 1;

        countSymbol += countConsecutive(x, y, lastTokenInserted, dx, dy, true);
        countColor += countConsecutive(x, y, lastTokenInserted, dx, dy, false);

        countSymbol += countConsecutive(x, y, lastTokenInserted, -dx, -dy, true);
        countColor += countConsecutive(x, y, lastTokenInserted, -dx, -dy, false);

        return countSymbol >= 4 || countColor >= 4;
    }

    /**
     * Count consecutive tokens in one direction, stopping at the first invalid token
     * @param x - x coordinate to start the check
     * @param y - y coordinate to start the check
     * @param lastTokenInsert - last token inserted
     * @param dx - x direction to check
     * @param dy - y direction to check
     * @param checkSymbol - true to check for symbol alignment, false for color
     * @return the count of consecutive tokens in the specified direction
     */
    public int countConsecutive(int x, int y, Token lastTokenInsert, int dx, int dy, boolean checkSymbol) {
        int count = 0;
        int i = x + dx;
        int j = y + dy;

        while (boxExist(i,j) && getPieceAt(i, j) instanceof Token token) {
            if ((checkSymbol && !(token.getSymbol() == lastTokenInsert.getSymbol())) || (!checkSymbol && !(token.getColor() == lastTokenInsert.getColor()))) {
                break;
            }

            count++;
            i += dx;
            j += dy;
        }
        return count;
    }

    /**
     * check if a box exist
     * @param x - x position in board
     * @param y - y position in board
     * @return true if box exist, else false
     */
    public boolean boxExist(int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board.length;
    }

    /**
     * place a piece, without consulting rules
     * @param x - x position
     * @param y - y position
     * @param piece - piece to insert
     */
    public void placePieceAt(int x, int y, Piece piece) {
        board[x][y] = piece;
    }

    /**
     * remove the piece at position (x,y)
     * @param x - x position
     * @param y - y position
     */
    public void removePieceAt(int x, int y) {
        board[x][y] = null;
    }
}
