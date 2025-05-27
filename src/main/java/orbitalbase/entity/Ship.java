package orbitalbase.entity;
import lombok.Getter;
import lombok.Setter;
import orbitalbase.entity.state.ShipState;
import orbitalbase.entity.state.impl.GettingInShipState;
import orbitalbase.entity.state.impl.GettingOutShipState;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Ship extends Thread implements Comparable<Ship> {

    @Setter
    private int shipId;
    @Setter
    @Getter
    private int capacity; //грузоподъёмность
    @Setter
    @Getter
    private int shipCargoWeight;

    private ShipState state = new GettingInShipState();

    public Ship(int shipId, int capacity, int shipCargoWeight) {
        this.shipId = shipId;
        this.capacity = capacity;
        this.shipCargoWeight = shipCargoWeight;
    }

    public long getShipId() {
        return shipId;
    }

    public ShipState getShipState() {
        return state;
    }

    public void setShipState(ShipState state) {
        this.state = state;
    }

    public boolean hasCargo() {
        return shipCargoWeight > 0;
    }

    @Override
    public void run() {
        do {
            state.doAction(this);
        } while (state.getClass() != GettingOutShipState.class);

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ship{");
        sb.append("id=").append(shipId);
        sb.append(", capacity=").append(capacity);
        sb.append(", cargo weight=").append(shipCargoWeight);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        return new EqualsBuilder()
                .append(shipId, ship.shipId)
                .append(capacity, ship.capacity)
                .append(shipCargoWeight, ship.shipCargoWeight)
                .append(state, ship.state)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(shipId)
                .append(capacity)
                .append(shipCargoWeight)
                .append(state)
                .toHashCode();
    }

    @Override
    public int compareTo(Ship o) {
        return Integer.compare(this.shipId, o.shipId);
    }
}
