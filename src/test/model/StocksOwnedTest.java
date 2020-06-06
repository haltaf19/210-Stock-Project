package model;

import model.stocks.Stocks;
import model.account.StocksOwned;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StocksOwnedTest {
    StocksOwned ownedStocks;
    Stocks stock1;
    Stocks stock2;

    @BeforeEach
    public void runBefore(){
        ownedStocks = new StocksOwned();
        stock1 = new Stocks("companyname0", 0.0,
                "sector0", "ticker0", "description0");
        stock2 = new Stocks("companyname1", 1.0, "sector1", "ticker1",
                "description1");
    }

    @Test
    public void StocksOwned(){
        Stocks s3 = new Stocks("companyname2", 2.0,
                "sector2", "ticker2", "description2");
        ownedStocks.addStock(s3,10);

        assertTrue(ownedStocks.ownEnoughShares(s3, 5));
        assertFalse(ownedStocks.ownEnoughShares(s3, 20));
        assertFalse(ownedStocks.ownEnoughShares(stock1, 20));
        assertEquals(10, ownedStocks.getShares().get(0));
        assertEquals("ticker2", ownedStocks.getStocksOwned().get(0).getTicker());
    }

    @Test
    void testSetters(){
        ownedStocks.addStock(stock1, 20);

        ownedStocks.setStocksOwned(ownedStocks.getStocksOwned());
        ownedStocks.setShares(ownedStocks.getShares());



    }

    @Test
    public void addStockTest(){
        ownedStocks.addStock(stock1, 20);
        ownedStocks.addStock(stock1, 20);
        ownedStocks.addStock(stock2, 10);

        assertTrue(ownedStocks.ownEnoughShares(stock1, 39));
        assertFalse(ownedStocks.ownEnoughShares(stock1, 41));
        assertFalse(ownedStocks.ownEnoughShares(stock2, 40));

    }

    @Test
    public void check(){
        ownedStocks.addStock(stock1, 20);
        ownedStocks.addStock(stock2, 10);

        assertTrue(ownedStocks.ownEnoughShares(stock1, 19));
        assertFalse(ownedStocks.ownEnoughShares(stock2, 40));


    }

    @Test
    public void removeStockTest(){
        ownedStocks.addStock(stock1,30);
        ownedStocks.addStock(stock2, 10);
        ownedStocks.removeStocks(stock1, 30);
        ownedStocks.removeStocks(stock2,2);

        assertTrue(ownedStocks.ownEnoughShares(stock2, 5));
        assertFalse(ownedStocks.ownEnoughShares(stock2, 20));
        assertFalse(ownedStocks.ownEnoughShares(stock1, 10));
    }

    @Test
    void testEmptyList(){
        assertEquals(" You do not own any stocks ", ownedStocks.displayOwnedStocks());
    }

    @Test
    public void displaySingleOwnedStocksTest(){
        ownedStocks.addStock(stock1, 20);

        assertEquals(1 + ". " + "Company Name: " + "companyname0" + "\n" + "Ticker: "
                + "ticker0" + "\n" + "Price: " + 0.0 + "\n" + "Sector: " + "sector0"
                + "\n" + "Description: " + "description0" + "\n"
                + "Shares Owned: " + 20 + "\n" + "\n", ownedStocks.displayOwnedStocks());
    }
    @Test
    public void displayMultipleOwnedStocksTest(){
        ownedStocks.addStock(stock1, 20);
        ownedStocks.addStock(stock2, 30);

        assertEquals(1 + ". " + "Company Name: " + "companyname0" + "\n" + "Ticker: "
                + "ticker0" + "\n" + "Price: " + 0.0 + "\n" + "Sector: " + "sector0"
                + "\n" + "Description: " + "description0" + "\n" + "Shares Owned: " + 20 + "\n" + "\n" + 2 + ". "
                + "Company Name: " +
                "companyname1" + "\n" + "Ticker: "
                + "ticker1" + "\n" + "Price: " + 1.0 + "\n" + "Sector: " + "sector1"
                + "\n" + "Description: " + "description1" + "\n" +  "Shares Owned: "
                + 30 + "\n" + "\n", ownedStocks.displayOwnedStocks());
    }








}
