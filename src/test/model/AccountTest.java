package model;

import model.market.StocksInMarket;
import model.stocks.Stocks;
import model.account.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {
    StocksInMarket s1;
    Stocks stock1;
    Stocks stock2;
    Account account1;

    @BeforeEach
    public void runBefore() {
        s1 = new StocksInMarket();
        stock1 = new Stocks("companyname0", 1.0,
                "sector0", "ticker0", "description0");
        stock2 = new Stocks("companyname1", 2.0, "sector1", "ticker1",
                "description1");
        account1 = new Account(500.0);
    }

    @Test
    public void AccountConstructorTest(){
        Account account2 = new Account(2.0);

        assertEquals(2, account2.getBalance());
    }

    @Test
    public void buyStockTest(){
        account1.buyStock(stock1, 30);
        account1.buyStock(stock2, 10);

        assertEquals(450, account1.getBalance());
    }

    @Test
    public void SellStockTest(){
        account1.sellStock(stock1, 30);
        account1.sellStock(stock2, 10);

        assertEquals(550, account1.getBalance());

    }

    @Test
    public void setBalanceTest(){
        account1.setBalance(600.0);

        assertEquals(600.0, account1.getBalance());


    }





}
