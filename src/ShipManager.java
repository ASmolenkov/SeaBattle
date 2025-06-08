import java.util.HashMap;
import java.util.Map;

public class ShipManager {
    private final Map <Integer, Integer> shipLimits;
    private final Map <Integer, Integer> currentShips;

    public ShipManager() {
        this.shipLimits = new HashMap<>();
        shipLimits.put(1,4);
        shipLimits.put(2,3);
        shipLimits.put(3,2);
        shipLimits.put(4,1);
        this.currentShips = new HashMap<>();
    }

    public void addShip(int deckCount) {
        if (!shipLimits.containsKey(deckCount)) {
            throw new IllegalArgumentException("Неверное количество палуб: " + deckCount);
        }

        int current = currentShips.getOrDefault(deckCount, 0);
        int limit = shipLimits.get(deckCount);

        if (current >= limit) {
            throw new IllegalStateException(
                    "Лимит " + deckCount + "-палубных кораблей исчерпан (макс. " + limit + ")"
            );
        }
        currentShips.put(deckCount, current + 1);
    }

    public int getShipCount(int deckCount) {
        return currentShips.getOrDefault(deckCount, 0);
    }

    public int getShipLimit(int deckCount) {
        return shipLimits.getOrDefault(deckCount, 0);
    }

    public boolean canPlaceShip(int deckCount) {
        if (!shipLimits.containsKey(deckCount)) {
            return false;
        }
        int placed = currentShips.getOrDefault(deckCount, 0);
        int limit = shipLimits.get(deckCount);
        return placed < limit;
    }


}
