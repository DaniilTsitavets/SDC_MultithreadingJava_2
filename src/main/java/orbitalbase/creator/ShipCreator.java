package orbitalbase.creator;

import lombok.extern.slf4j.Slf4j;
import orbitalbase.entity.Ship;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

@Slf4j
public class ShipCreator {

    public Ship create(List<Integer> values, int id) {
        int capacity = values.get(0);
        int shipCargoWeight = values.get(1);
        Ship ship = new Ship(id, capacity, shipCargoWeight);
        log.info(String.valueOf(ship));
        return ship;
    }

    public Queue<Ship> createShipQueue(List<List<Integer>> valuesList) {
        Queue<Ship> ships = new PriorityQueue<>();

        for (int i = 0; i < valuesList.size(); i++) {
            Ship ship = create(valuesList.get(i), i + 1);
            ships.offer(ship);
        }

        if (ships.isEmpty()) {
            log.error("Ship list is not created.");
        } else {
            log.info("Ship list created.");
        }
        return ships;
    }
}
