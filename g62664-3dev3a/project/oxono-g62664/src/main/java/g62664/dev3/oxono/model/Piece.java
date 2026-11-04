package g62664.dev3.oxono.model;

public abstract class Piece {
    // attributes
    private final Symbol symbol;
    private final Color color;

    // constructor
    public Piece(Symbol symbol, Color color) {
        this.symbol = symbol;
        this.color = color;
    }

    // getters
    public Symbol getSymbol() {
        return symbol;
    }
    public Color getColor() {
        return color;
    }
}
