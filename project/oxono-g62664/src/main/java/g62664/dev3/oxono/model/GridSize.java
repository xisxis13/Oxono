package g62664.dev3.oxono.model;

public enum GridSize {
    _6_by_6(6),
    _8_by_8(8);

    // attribute
    private final int size;

    // constructor
    GridSize(int size) {
        this.size = size;
    }

    // getter
    public int getSize() {
        return size;
    }
}
