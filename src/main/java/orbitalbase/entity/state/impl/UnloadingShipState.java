package orbitalbase.entity.state.impl;

import lombok.extern.slf4j.Slf4j;
import orbitalbase.entity.Pier;
import orbitalbase.entity.Port;
import orbitalbase.entity.Ship;
import orbitalbase.entity.state.ShipState;
import orbitalbase.exception.CustomException;

@Slf4j
public class UnloadingShipState implements ShipState {

    @Override
    public void doAction(Ship ship) {
        Port port = Port.getInstance();
        Pier pier = null;

        try {
            pier = port.getPier();
            log.info(String.format("Ship #%d got pier #%d.", ship.getShipId(), pier.getId()));

            pier.unloadShip(ship);
            log.info(String.format("Ship #%d is unloaded. New ship cargo weight is %d.",
                    ship.getShipId(), ship.getShipCargoWeight()));
        } catch (CustomException e) {
            log.error(String.valueOf(e));
        } finally {
            if (pier != null) {
                log.info(String.format(
                        "Pier #%d is released by ship #%d. New pier cargo weight is %d.",
                        pier.getId(), ship.getShipId(), pier.getPierCargoWeight()));
                port.returnPier(pier);
            } else {
                log.error(String.format("Pier is null for ship #%d", ship.getShipId()));
            }
        }
        ship.setShipState(new GettingOutShipState());
    }
}
