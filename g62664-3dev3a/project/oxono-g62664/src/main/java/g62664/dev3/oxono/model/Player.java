package g62664.dev3.oxono.model;

public class Player {
    // attributes
    private int nbTokensO;
    private int nbTokensX;
    private final Color color;
    private final Token tokenO;
    private final Token tokenX;

    // constructor
    public Player(Color color) {
        this.nbTokensO = 8;
        this.nbTokensX = 8;
        this.color = color;
        this.tokenO = new Token(Symbol.O, this.color);
        this.tokenX = new Token(Symbol.X, this.color);
    }

    // getters
    public int getNbTokensO() {
        return nbTokensO;
    }
    public int getNbTokensX() {
        return nbTokensX;
    }
    public Color getColor() {
        return color;
    }
    public Token getTokenO() {
        return tokenO;
    }
    public Token getTokenX() {
        return tokenX;
    }

    // methods
    /**
     * decrease the number of token O of player
     */
    public void decreaseTokensO() {
        this.nbTokensO--;
    }

    /**
     * decrease the number of token X of player
     */
    public void decreaseTokensX() {
        this.nbTokensX--;
    }

    /**
     * increase the number of token O of player (in case of 'undo')
     */
    public void increaseTokensO() {
        this.nbTokensO++;
    }

    /**
     * increase the number of token X of player (in case of 'undo')
     */
    public void increaseTokensX() {
        this.nbTokensX++;
    }

    // toString
    @Override
    public String toString() {
        return color + " player";
    }
}
