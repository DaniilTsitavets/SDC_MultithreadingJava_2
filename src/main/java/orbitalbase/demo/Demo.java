package orbitalbase.demo;

import lombok.extern.slf4j.Slf4j;
import orbitalbase.creator.ShipCreator;
import orbitalbase.entity.Ship;
import orbitalbase.exception.CustomException;
import orbitalbase.parser.DataParser;
import orbitalbase.reader.DataReader;


import java.util.List;
import java.util.Queue;
@Slf4j
public class Demo {
    public static void main(String[] args) throws CustomException {

        DataReader dataReader = new DataReader();
        List<String> lines = dataReader.readData("data/shipdata.txt");

        DataParser dataParser = new DataParser();
        List<List<Integer>> valuesList = dataParser.parseData(lines);

        ShipCreator shipCreator = new ShipCreator();
        Queue<Ship> ships = shipCreator.createShipQueue(valuesList);
        for (Ship ship : ships) {
            log.info(String.valueOf(ship));
        }

        for (Ship ship : ships) {
            ship.start();
        }
    }
}
