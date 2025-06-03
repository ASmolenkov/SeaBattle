public class Ship {
    private String icon;
    private int size;
    private Coordinate coordinateHead;
    private Direction direction;

    public Ship(int size, Coordinate coordinateHead, Direction direction) {
        icon = "🚢";
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

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void printShip(){
        System.out.println(icon);
    }
}
