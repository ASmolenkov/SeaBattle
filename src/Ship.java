public class Ship {
    private final int  size;
    private final Coordinate coordinateHead;
    private final Direction direction;

    public Ship(int size, Coordinate coordinateHead, Direction direction) {

        this.size = size;
        this.coordinateHead = coordinateHead;
        this.direction = direction;
    }

    public int getSize() {
        return size;
    }

    public Coordinate getCoordinateHead() {
        return coordinateHead;
    }

    public Direction getDirection() {
        return direction;
    }

}
