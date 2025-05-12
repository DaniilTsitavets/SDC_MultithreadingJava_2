package orbitalbase.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Setter
@Getter
@Slf4j
public class Pier {
    private static final int LOAD_TIME = 100;

    private int id;
    private int pierCapacity = 100;
    private int pierCargoWeight = 50;

    public Pier() {
        log.info("Pier is created");
    }

    public Pier(int id) {
        log.info(String.format("Pier #%d is created", id));
        this.id = id;
    }

    public Pier(int id, int pierCapacity, int pierCargoWeight) {
        log.info(String.format("Pier #%d is created", id));
        this.id = id;
        this.pierCapacity = pierCapacity;
        this.pierCargoWeight = pierCargoWeight;
    }

    public void loadShip(Ship ship) {
        log.info(String.format("Pier #%d : Loading method starts : ship #%d capacity %d ton",
                this.id, ship.getShipId(), ship.getCapacity()));

        try {
            TimeUnit.MILLISECONDS.sleep(ship.getCapacity() * LOAD_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (pierCargoWeight == 0) {
            log.info(String.format("No cargo in pier #%d. Generate cargo.", this.id));
            fillPier();
        }

        if (pierCargoWeight < ship.getCapacity()) {
            log.info(String.format("Not enough cargo in pier #%d to load ship full. Generate cargo.", this.id));
            pierCargoWeight += ship.getCapacity() - pierCargoWeight;
        }

        pierCargoWeight -= ship.getCapacity();
        ship.setShipCargoWeight(ship.getCapacity());
    }

    public void unloadShip(Ship ship) {
        log.info(String.format("Pier #%d : Unloading method starts : ship #%d cargo %d ton",
                this.id, ship.getShipId(), ship.getShipCargoWeight()));

        try {
            TimeUnit.MILLISECONDS.sleep(ship.getShipCargoWeight() * LOAD_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int freeSpace = pierCapacity - pierCargoWeight;
        if (freeSpace <= ship.getShipCargoWeight()) {
            emptyPier();
        }
        pierCargoWeight += ship.getShipCargoWeight();
        ship.setShipCargoWeight(0);
    }

    private void fillPier() {
        pierCargoWeight = pierCapacity / 2;
        log.info(String.format("Pier #%d was filled to %d ton", this.id, pierCapacity / 2));
    }

    private void emptyPier() {
        pierCargoWeight = 0;
        log.info(String.format("Pier #%d was emptied.", this.id));
    }
}
