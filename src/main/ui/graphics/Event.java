// CLASS TO HANDLE EVENTS

package ui.graphics;

import model.account.Account;
import model.account.StocksOwned;
import exceptions.CanNotFindException;
import ui.exceptions.NotEnoughException;
import ui.exceptions.TooPoorException;
import model.market.StocksInMarket;
import model.stocks.Stocks;
import persistence.Reader;
import persistence.Writer;

import java.util.ArrayList;


public class Event {

    private StocksInMarket gameStocks;
    private Account userAccount;
    private StocksOwned stocksOwned;
    private Reader reader;
    private Writer writer;

    // EFFECTS: Creates an event
    public Event() {
        userAccount = new Account(500.0);
        stocksOwned = new StocksOwned();
        reader = new Reader("./data/account.json");
        gameStocks = new StocksInMarket();
        Stocks pear = new Stocks("Pear", 49.0, "Manufacturing",
                "PER", "A company that manufactures hardware for computers");
        Stocks instastruct = new Stocks("Instastruct", 30.0, "Industrials",
                "ISC", "A construction business for food industry");
        Stocks bank = new Stocks("Bank of InvestorVille", 120.0, "Financial", "BIE",
                "A stable bank operating in Investorville");
        Stocks microhard = new Stocks("Microhard", 70.0, "Technology", "MCH",
                "A technology company operating with both software and hardware");
        gameStocks.addStock(pear);
        gameStocks.addStock(instastruct);
        gameStocks.addStock(bank);
        gameStocks.addStock(microhard);
    }


    // MODIFIES: This
    // EFFECTS: Creates an event
    public int doBuy(String ticker, String share) throws CanNotFindException, TooPoorException {
        try {
            Integer indexOfChosen = gameStocks.findStockFromTicker(ticker);

            if (indexOfChosen < 0) {
                System.out.println("Could not find that Stock");
                throw new CanNotFindException();
            } else {
                Stocks stock = gameStocks.getStock(indexOfChosen);
                int integerShares = Integer.parseInt(share);

                if (canAfford(stock, integerShares)) {
                    stocksOwned.addStock(stock, integerShares);
                    userAccount.buyStock(stock, integerShares);

                    System.out.println("You have bought " + integerShares + " shares totalling "
                            + (integerShares * stock.getPrice()) + "$");
                    return 1;
                } else {
                    System.out.println("You can't afford that stock");
                    throw new TooPoorException();
                }
            }
        } finally {
            System.out.println("It went through");
        }
    }


    // EFFECTS: Deterimes whether you can afford
    public boolean canAfford(Stocks stock, Integer shares) {
        double price = stock.getPrice();
        double bought = price * shares;
        return !(bought > userAccount.getBalance());
    }


    // MODIFIES: This
    // EFFECTS: Sells the product. Throws Exceptions if any error occurs

    public int doSell(String ticker, String shares) throws CanNotFindException, NotEnoughException {
        Integer indexOfChosen = gameStocks.findStockFromTicker(ticker);
        if (indexOfChosen < 0) {
            throw new CanNotFindException();
        } else {
            Stocks stock = gameStocks.getStock(indexOfChosen);
            int integerShares = Integer.parseInt(shares);

            if (stocksOwned.ownEnoughShares(stock, integerShares)) {
                stocksOwned.removeStocks(stock, integerShares);
                userAccount.sellStock(stock, integerShares);

                System.out.println("You have sold " + integerShares + " shares totalling "
                        + "$" + (integerShares * stock.getPrice()));
                return 1;

            } else {
                System.out.println("The amount of shares you wish to sell exceeds the amount you own ");
                throw new NotEnoughException();
            }
        }

    }


    // MODIFIES: This
    //EFFECTS: saves the game
    public void saveGame() {
        writer = new Writer(this.stocksOwned, this.userAccount, "./data/account.json");
        writer.writer();
        System.out.println("Your game has been saved");
    }

    // MODIFIES: This
    // EFFECTS: Loads the game
    public void loadGame() {
        reader.readData();
        this.stocksOwned.setStocksOwned(reader.getStocks());
        this.stocksOwned.setShares(reader.getInteger());
        this.userAccount.setBalance(reader.getBalance());
    }

    public StocksOwned getStocksOwned() {
        return stocksOwned;
    }

    public ArrayList<Stocks> getMarketStocks() {
        return gameStocks.getMarketStocks();
    }

    public Account getUserAccount() {
        return userAccount;
    }


}
