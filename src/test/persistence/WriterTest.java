package persistence;

import model.account.Account;
import model.account.StocksOwned;
import model.stocks.Stocks;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Reader;
import persistence.Writer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

public class WriterTest {
    StocksOwned stock = new StocksOwned();
    Stocks stock1 = new Stocks("companyname0", 1.0,
            "sector0", "ticker0", "description0");
    Account account = new Account(500.0);
    ArrayList<Stocks> stocksArrayList;
    ArrayList<Integer> sharesArrayList;
    double balance;
    JSONArray masterArray;
    JSONArray stocksArray;
    JSONArray sharesArray;
    String name;
    Reader reader;


    @BeforeEach
    void runBefore() {
        stocksArrayList = new ArrayList<>();
        sharesArrayList = new ArrayList<>();

        stocksArrayList.add(stock1);
        sharesArrayList.add(2);

        account.setBalance(498.0);
        balance = 498.0;

        stock.setShares(sharesArrayList);
        stock.setStocksOwned(stocksArrayList);

        masterArray = new JSONArray();
        stocksArray = new JSONArray();
        sharesArray = new JSONArray();



        name = "./data/testWriterAccount.json";
        reader = new Reader(name);

    }

    @Test
    void testWriterConstructor() {
        Writer writer = new Writer(stock, account, name);
    }


    @Test
    void testWriter() {
        Writer writer = new Writer(stock, account, "./data/testWriterAccount.json");
        writer.writer();
        reader.readData();


        assertEquals(498.0, reader.getBalance());
        assertEquals(2, reader.getInteger().get(0));
    }

    @Test
    void testException() throws Exception{


       try{
           Reader fail = new Reader("./data/failTestAccount");
           Writer writer = new Writer(stock, account,"./data/failTestAccount");
           writer.writer();
           fail.readData();


       } catch (Exception e){
           e.printStackTrace();

       }


    }





}
