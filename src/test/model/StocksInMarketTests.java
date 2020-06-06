package model;

import exceptions.CanNotFindException;
import model.market.StocksInMarket;
import model.stocks.Stocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.awt.geom.AreaOp;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StocksInMarketTests {
    StocksInMarket s1;
    Stocks stock1;
    Stocks stock2;

    @BeforeEach
    public void runBefore() {
        s1 = new StocksInMarket();
        stock1 = new Stocks("companyname0", 0.0,
                "sector0", "ticker0", "description0");
        stock2 = new Stocks("companyname1", 1.0, "sector1", "ticker1",
                "description1");

    }


    @Test
    public void StocksInMarketConstructorTest() {
        Stocks stock4 = new Stocks("companyname3", 3.0,
                "sector3", "ticker3", "description3");
        s1.addStock(stock4);

        try {
            assertEquals(stock4, s1.getStock(0));
            s1.getStock(0);
        } catch (CanNotFindException e) {

        }
    }

    @Test
    public void addSingleStock() {
        s1.addStock(stock1);

        assertEquals(1 + ". " + "Company Name: " + "companyname0" + "\n" + "Ticker: "
                + "ticker0" + "\n" + "Price: " + 0.0 + "\n" + "Sector: " + "sector0"
                + "\n" + "Description: " + "description0" + "\n" + "\n", s1.displayStocks());


    }

    @Test
    public void addMultipleStockFail() {
        s1.addStock(stock1);
        s1.addStock(stock2);


        try {
            assertEquals(stock1, s1.getStock(0));
            assertEquals(stock2, s1.getStock(1));
            s1.findStockFromTicker("wxy");
            fail();
        } catch (CanNotFindException e) {
            System.out.println("Exception was caught ");
        }
    }

    @Test
    public void addMultipleStockPass() {
        s1.addStock(stock1);
        s1.addStock(stock2);


        try {
            assertEquals(stock1, s1.getStock(0));
            assertEquals(stock2, s1.getStock(1));
            assertEquals(1 + ". " + "Company Name: " + "companyname0" + "\n" + "Ticker: "
                    + "ticker0" + "\n" + "Price: " + 0.0 + "\n" + "Sector: " + "sector0"
                    + "\n" + "Description: " + "description0" + "\n" + "\n" + 2 + ". " + "Company Name: " +
                    "companyname1" + "\n" + "Ticker: "
                    + "ticker1" + "\n" + "Price: " + 1.0 + "\n" + "Sector: " + "sector1"
                    + "\n" + "Description: " + "description1" + "\n" + "\n", s1.displayStocks());
            s1.findStockFromTicker("ticker1");
        } catch (CanNotFindException e) {
            fail();
        }
    }


    @Test
    public void displayStocksTest() {
        s1.addStock(stock1);
        s1.addStock(stock2);

        assertEquals(1 + ". " + "Company Name: " + "companyname0" + "\n" + "Ticker: "
                + "ticker0" + "\n" + "Price: " + 0.0 + "\n" + "Sector: " + "sector0"
                + "\n" + "Description: " + "description0" + "\n" + "\n" + 2 + ". " + "Company Name: " +
                "companyname1" + "\n" + "Ticker: "
                + "ticker1" + "\n" + "Price: " + 1.0 + "\n" + "Sector: " + "sector1"
                + "\n" + "Description: " + "description1" + "\n" + "\n", s1.displayStocks());

    }

    @Test
    public void findStockFromTickerTest() {
        s1.addStock(stock1);
        s1.addStock(stock2);
        Stocks stock3 = new Stocks("companyname2", 2.0, "sector2",
                "ticker2", "description2");
        s1.addStock(stock3);

        try {
            assertEquals(2, s1.findStockFromTicker("ticker2"));
            assertEquals(2, s1.findStockFromTicker("ticker2"));
            s1.findStockFromTicker("ticker1");

        } catch (CanNotFindException e) {
            fail();
        }
    }

    @Test
    public void getStockTest() {
        s1.addStock(stock1);
        s1.addStock(stock2);

        try {
            assertEquals(stock1, s1.getStock(0));
            assertEquals(stock2, s1.getStock(1));
            assertEquals("ticker0", s1.getMarketStocks().get(0).getTicker());
            s1.getStock(1);
        } catch (CanNotFindException e) {

        }
    }

    @Test
    public void getStockTestFail() {
        s1.addStock(stock1);
        s1.addStock(stock2);

        try {
            s1.getStock(8);
            fail();
        } catch (CanNotFindException e) {

        }
    }

    @Test
    public void setMarketStocksTest() {
        ArrayList<Stocks> s = new ArrayList<>();
        s.add(stock1);

        s1.setMarketStocks(s);
        try{
            assertEquals("ticker0", s1.getStock(0).getTicker());
            s1.getStock(0);
        }  catch (CanNotFindException e){
            fail();
        }
    }


}
