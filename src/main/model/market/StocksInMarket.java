// A  class representing all the stocks ion the market

package model.market;

import exceptions.CanNotFindException;
import model.stocks.Stocks;

import java.util.ArrayList;

public class StocksInMarket {
    ArrayList<Stocks> marketStocks;


    //EFFECTS: Constructs an ArrayList of stocks in the market
    public StocksInMarket() {
        marketStocks = new ArrayList<>();
    }

    // MODIFIES: This
    // EFFECTS: Adds a stock to the stocks in the market
    public void addStock(Stocks stock) {
        marketStocks.add(stock);
    }

    // EFFECTS; Display the stocks in an ordered fashion
    public String displayStocks() {
        int index = 1;
        String example = "";
        for (Stocks a : marketStocks) {
            example += index + ". " + "Company Name: " + a.getCompanyName() + "\n" + "Ticker: "
                    + a.getTicker() + "\n" + "Price: " + a.getPrice() + "\n" + "Sector: "
                    + a.getSector() + "\n" + "Description: " + a.getDescription() + "\n" + "\n";
            index += 1;
        }
        return example;
    }

    // EFFECTS: finds and returns the location of the stock in the list
    public Integer findStockFromTicker(String ticker) throws CanNotFindException {
        int location = 0;
        for (Stocks stock : marketStocks) {
            String stockTicker = stock.getTicker();
            if (ticker.equals(stockTicker)) {
                return location;
            } else {
                location += 1;
            }
        }
        throw new CanNotFindException();
    }

    // EFFECTS: Getters
    public Stocks getStock(Integer index) throws CanNotFindException {
        if (index > marketStocks.size() || index < 0) {
            throw new CanNotFindException();
        }
        return marketStocks.get(index);
    }

    public ArrayList<Stocks> getMarketStocks() {
        return marketStocks;
    }

    // MODIFES; This
    // EFFECTS: Set the market stocks
    public void setMarketStocks(ArrayList<Stocks> list) {
        this.marketStocks = list;
    }


}
