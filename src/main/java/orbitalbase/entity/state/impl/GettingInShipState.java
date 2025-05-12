package orbitalbase.entity.state.impl;

import lombok.extern.slf4j.Slf4j;
import orbitalbase.entity.Ship;
import orbitalbase.entity.state.ShipState;

@Slf4j
public class GettingInShipState implements ShipState {

    @Override
    public void doAction(Ship ship) {
        log.info(String.format("%s is getting in port.", ship));
        if (ship.hasCargo()) {
            ship.setShipState(new UnloadingShipState());
        } else {
            ship.setShipState(new LoadingShipState());
        }
    }
}
