package orbitalbase.entity.state.impl;

import lombok.extern.slf4j.Slf4j;
import orbitalbase.entity.Ship;
import orbitalbase.entity.state.ShipState;

@Slf4j
public class GettingOutShipState implements ShipState {

    @Override
    public void doAction(Ship ship) {
        log.info(String.format("Ship #%d has left the port.", ship.getShipId()));
    }
}
