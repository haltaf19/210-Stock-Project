package persistence;

import model.stocks.Stocks;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {
    Reader reader;

    JSONParser jsonParser = new JSONParser();
    JSONArray masterArray;
    JSONArray stocks;
    JSONArray integers;
    String name;
    ArrayList<Stocks> stock;
    ArrayList<Integer> integer;


    @BeforeEach
    void runBefore() {
        stock = new ArrayList<>();
        integer = new ArrayList<>();
        integer.add(3);
    }


    @Test
    void testReaderConstructor() {
        Reader read = new Reader("./data/testAccount.json");
    }

    @Test
    void readDataTest() {
        try {
            reader = new Reader("./data/testAccount.json");
            FileReader read = new FileReader(new File(reader.getName()));
            reader.readData();
            masterArray = (JSONArray) jsonParser.parse(read);
            stocks = (JSONArray) masterArray.get(0);
            integers = (JSONArray) masterArray.get(1);

            assertEquals(353.0, reader.getBalance());
            assertEquals(3, reader.getInteger().get(0));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void readDataTestException(){
        name = "./data/testAccount.json";
        try {
            reader = new Reader(name);
            FileReader read = new FileReader(new File(name));
            reader.readData();
            masterArray = (JSONArray) jsonParser.parse(read);
            masterArray = (JSONArray) jsonParser.parse(read);
            stocks = (JSONArray) masterArray.get(0);
            integers = (JSONArray) masterArray.get(1);
            fail();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void makeStockTest() throws IOException, ParseException {
        reader = new Reader("./data/testAccount.json");
        FileReader read = new FileReader(new File(reader.getName()));
        reader.readData();
        masterArray = (JSONArray) jsonParser.parse(read);
        stocks = (JSONArray) masterArray.get(0);

        reader.makeStock(stocks);
        assertEquals(49.0, reader.getStocks().get(0).getPrice());
        assertEquals("PER",reader.getStocks().get(0).getTicker());
    }
}
