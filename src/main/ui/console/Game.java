// A class with all UI

package ui.console;

import exceptions.CanNotFindException;
import model.market.StocksInMarket;
import model.stocks.Stocks;
import model.account.Account;
import model.account.StocksOwned;
import persistence.Reader;
import persistence.Writer;

import java.util.Scanner;


public class Game {
    private StocksInMarket gameStocks;
    private Account userAccount;
    private StocksOwned stocksOwned;
    private Scanner input;
    private Reader reader;
    private Writer writer;



    public Game() {
        userAccount = new Account(500.0);
        stocksOwned = new StocksOwned();
        reader = new Reader("./data/account.json");
        gameStocks = new StocksInMarket();
        Stocks pear = new Stocks("Pear", 49.0, "Technology",
                "PER", "A company that overcharges on cellphones");
        Stocks instastruct = new Stocks("Instastruct", 30.0, "Industrials",
                "ISC", "A construction business for food industry");
        gameStocks.addStock(pear);
        gameStocks.addStock(instastruct);
        runGame();
    }


    public void saveGame() {
        writer = new Writer(this.stocksOwned, this.userAccount, "./data/account.json");
        writer.writer();
        System.out.println("Your game has been saved");
    }

    public void loadGame() {
        reader.readData();
        this.stocksOwned.setStocksOwned(reader.getStocks());
        this.stocksOwned.setShares(reader.getInteger());
        this.userAccount.setBalance(reader.getBalance());

    }

    private void runGame() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        loadGame();


        System.out.println(stocksOwned.getShares());
        System.out.println("Below are stocks that you own");
        System.out.println(stocksOwned.displayOwnedStocks());
        System.out.println("$" + userAccount.getBalance() + " is your balance");
        while (keepGoing) {
            displayMenu();
            command = input.next();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThank you for playing!");
    }


    public void doSell() {
        System.out.println("Enter the Ticker of the Stock you would like to Sell");
        String command = input.next();
        try {
            Integer indexOfChosen = gameStocks.findStockFromTicker(command);

            if (indexOfChosen < 0) {
                System.out.println("That Stock Could Not Be Found");
            } else {
                Stocks stock = gameStocks.getStock(indexOfChosen);
                System.out.println("Enter the number of shares you would like to sell");
                String sharesToBeSold = input.next();
                int integerShares = Integer.parseInt(sharesToBeSold);

                if (stocksOwned.ownEnoughShares(stock, integerShares)) {
                    stocksOwned.removeStocks(stock, integerShares);
                    userAccount.sellStock(stock, integerShares);

                    System.out.println("You have sold " + integerShares + " shares totalling "
                            + "$" + (integerShares * stock.getPrice()));
                } else {
                    System.out.println("The amount of shares you wish to sell exceeds the amount you own ");
                }
            }
        } catch (CanNotFindException e) {
            System.out.println("Exception Thrown");
        }

    }

    public void doBuy() {
        System.out.println("Enter the Ticker of the Stock you would like to purchase");
        String command = input.next();

        try {
            Integer indexOfChosen = gameStocks.findStockFromTicker(command);
            if (indexOfChosen < 0) {
                System.out.println("Could not find that Stock");
            } else {
                Stocks stock = gameStocks.getStock(indexOfChosen);
                System.out.println("Enter the number of shares you would like to purchase");
                String sharesToBeBought = input.next();
                int integerShares = Integer.parseInt(sharesToBeBought);

                if (canAfford(stock, integerShares)) {
                    stocksOwned.addStock(stock, integerShares);
                    userAccount.buyStock(stock, integerShares);

                    System.out.println("You have bought " + integerShares + " shares totalling "
                            + (integerShares * stock.getPrice()) + "$");
                } else {
                    System.out.println("You can't afford to buy this many stocks ");
                }
            }
        } catch (CanNotFindException e) {
            System.out.println("Error");
        }
    }

    public boolean canAfford(Stocks stock, Integer shares) {
        double price = stock.getPrice();
        double bought = price * shares;
        return !(bought > userAccount.getBalance());
    }

    public void displayStocksInMarket() {
        System.out.println(gameStocks.displayStocks());
    }

    public void displayStocksOwned() {
        System.out.println(stocksOwned.displayOwnedStocks());
        System.out.println("$" + this.userAccount.getBalance());
    }


    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tb -> buy stocks");
        System.out.println("\ts -> sell stocks ");
        System.out.println("\tv -> view stocks in market");
        System.out.println("\to -> view stocks you own");
        System.out.println("\tp -> print balance");
        System.out.println("\tsv -> save game");
        System.out.println("\tq -> quit");
    }


    // EFFECTS: Process all commands
    private void processCommand(String command) {
        switch (command) {
            case "b":
                doBuy();
                break;
            case "s":
                doSell();
                break;
            case "v":
                displayStocksInMarket();
                break;
            case "o":
                displayStocksOwned();
                break;
            case "p":
                System.out.println("$" + this.userAccount.getBalance());
                break;
            case "sv":
                saveGame();
                break;
            default:
                break;
        }

    }

}

