import game.constans.GameConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayingField {
    private int width;
    private int height;
    private Map<Coordinate,Ship> mapShips;
    private Map<Coordinate, ShotResult> mapShots;
    private int shipCounts;
    public static final int MAX_COUNT_SHIPS = 3;




    public PlayingField(int width, int height, boolean isOwnField) {
        this.width = width;
        this.height = height;
        this.mapShips = isOwnField ? new HashMap<>() : null;
        this.mapShots = new HashMap<>();
        this.shipCounts = 0;
    }

    public Map<Coordinate, ShotResult> getMapShots() {
        return mapShots;
    }

    public int getShipCounts() {
        return shipCounts;
    }

    public void plusShipCounts() {
        this.shipCounts++;
    }
    public void minusShipCounts() {
        this.shipCounts--;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Map<Coordinate, Ship> getMapShips() {
        return mapShips;
    }

    public void printField(FieldType fieldType){
        System.out.println();
        for (int y = 1; y <= getHeight(); y++) {
            System.out.print(y != 10 ? y + " ": y);
            for (int x = 1; x <= getWidth(); x++) {
                Coordinate coord = new Coordinate(x, y);
                ShotResult shot = mapShots.get(coord);
                if(shot != null){
                    System.out.print(shot.getSymbol());
                }
                else if(fieldType == FieldType.PLAYER && isOccupied(x, y)){
                    System.out.print(ConstantUnicodeEmoji.SHIP);
                }
                else {
                    System.out.print(ConstantUnicodeEmoji.EMPTY);
                }
            }
            System.out.println();
        }
    }
    public void installShip(Ship ship){
        if(isValidShipPlacement(ship)) {
            for (int i = 0; i < ship.getSize(); i++) {
                Coordinate coordinate;
                if (ship.getDirection() == Direction.HORIZONTAL) {
                    coordinate = ship.getCoordinateHead().shift(i, 0);
                } else {
                    coordinate = ship.getCoordinateHead().shift(0, i);
                }
                this.mapShips.put(coordinate, ship);

            }
            plusShipCounts();
            System.out.printf(GameConstants.Templates.SHIP_SETUP_TEMPLATE,(MAX_COUNT_SHIPS - shipCounts));
        }
        else {
            System.out.println(GameConstants.Errors.SHIP_SETUP_ERROR);
        }
    }
    public boolean shot(Coordinate coordinateShoot){
        if(this.mapShips.containsKey(coordinateShoot)){
            mapShips.remove(coordinateShoot);
            mapShots.put(coordinateShoot,ShotResult.HIT);
            System.out.println(GameConstants.Messages.HIT_TARGET);
            if(!hasAdjacentShips(coordinateShoot)){
                System.out.println(GameConstants.Messages.SHIP_DESTROYED);
                minusShipCounts();
            }
            return true;
        }
            mapShots.put(coordinateShoot,ShotResult.MISS);
            System.out.println(GameConstants.Messages.MISS);
        return false;
    }

    public Ship getShipByCoordinates(int x, int y){
        return mapShips.get(new Coordinate(x, y));
    }

    public boolean isOccupied(int x, int y){
        return mapShips.containsKey(new Coordinate(x,y));
    }

    private boolean isValidShipPlacement(Ship ship) {
        if (isShipOutOfBounds(ship)) {
            return false;
        }
        for (Coordinate shipPart : getAllShipCoordinates(ship)) {
            if (isCoordinateOccupied(shipPart) || hasAdjacentShips(shipPart)) {
                return false;
            }
        }
        return true;
    }

    private List<Coordinate> getAllShipCoordinates(Ship ship) {
        List<Coordinate> coordinates = new ArrayList<>();
        int dx = ship.getDirection() == Direction.HORIZONTAL ? 1 : 0;
        int dy = ship.getDirection() == Direction.VERTICAL ? 1 : 0;

        for (int i = 0; i < ship.getSize(); i++) {
            coordinates.add(ship.getCoordinateHead().shift(dx * i, dy * i));
        }
        return coordinates;
    }
    private boolean isCoordinateOccupied(Coordinate coordinate) {
        return mapShips.containsKey(coordinate);
    }
    private boolean hasAdjacentShips(Coordinate center) {
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue;
                Coordinate neighbor = center.shift(x, y);
                if (isCoordinateOccupied(neighbor)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isShipOutOfBounds(Ship ship){
        if(ship.getDirection() == Direction.HORIZONTAL){
            return ship.getCoordinateHead().getX() + (ship.getSize() - 1) > this.width;
        }
        else {
            return ship.getCoordinateHead().getY() + (ship.getSize() - 1) > this.height;
        }

    }


}
