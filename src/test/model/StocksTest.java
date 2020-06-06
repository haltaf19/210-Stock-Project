package model;

import model.stocks.Stocks;
import model.account.StocksOwned;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StocksTest {

    StocksOwned ownedStocks;
    Stocks stock1;
    Stocks stock2;

    @BeforeEach
    public void runBefore() {
        ownedStocks = new StocksOwned();
        stock1 = new Stocks("companyname0", 0.0,
                "sector0", "ticker0", "description0");
        stock2 = new Stocks("companyname1", 1.0, "sector1", "ticker1",
                "description1");
    }

    @Test
    public void createStock(){
        Stocks s3 = new Stocks("companyname2", 2.0,
                "sector2", "ticker2", "description2");

        assertEquals(2, s3.getPrice());

    }

    @Test
    public void getPriceTest(){

        assertEquals(0, stock1.getPrice());
        assertEquals(1, stock2.getPrice());
    }
    @Test
    public void getTickerTest(){


        assertEquals("ticker0", stock1.getTicker());
        assertEquals("ticker1", stock2.getTicker());
    }
    @Test
    public void getSectorTest(){

        assertEquals("sector0", stock1.getSector());
        assertEquals("sector1", stock2.getSector());
    }
    @Test
    public void getCompanyNameTest(){

        assertEquals("companyname0", stock1.getCompanyName());
        assertEquals("companyname1", stock2.getCompanyName());
    }
    @Test
    public void getDescriptionTest(){

        assertEquals("description0", stock1.getDescription());
        assertEquals("description1", stock2.getDescription());
    }


}
