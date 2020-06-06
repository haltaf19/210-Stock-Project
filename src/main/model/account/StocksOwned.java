// A class that has all the stocks owned in an arraylist


package model.account;


import model.stocks.Stocks;

import java.util.ArrayList;

public class StocksOwned {
    private ArrayList<Stocks> stocksOwned;
    private ArrayList<Integer> shares;


    // EFFECTS: Getters
    public ArrayList<Integer> getShares() {
        return shares;
    }

    public ArrayList<Stocks> getStocksOwned() {
        return stocksOwned;
    }

    // EFFECTS: stocks owned
    public void setStocksOwned(ArrayList<Stocks> stocks) {
        this.stocksOwned = stocks;
    }

    //EFFECTS: set shares
    public void setShares(ArrayList<Integer> shares) {
        this.shares = shares;
    }

    // EFFECTS: Creates an Array list with StocksOwned and Shares
    // Shares and StocksOwned Arraylists have the same indices
    public StocksOwned() {
        stocksOwned = new ArrayList<>();
        shares = new ArrayList<>();


    }

    //MODIFIES: THIS
    //EFFECTS: Adds a stock to the list of stocks you own
    public void addStock(Stocks a, Integer shares) {
        if (!(checkTooSeeIfStockExists(a) == -1)) {
            int index = checkTooSeeIfStockExists(a);
            int sharesOwned = this.shares.get(index);
            sharesOwned += shares;
            this.shares.set(index, sharesOwned);


        } else {
            stocksOwned.add(a);
            this.shares.add(shares);
        }
    }


    //EFFECTS: Checks the stocks to see if it already exists and returns its location
    public Integer checkTooSeeIfStockExists(Stocks a) {
        for (int i = 0; i < stocksOwned.size(); i++) {
            Stocks compare = stocksOwned.get(i);
            String comparision = compare.getTicker();
            if (a.getTicker().equals(comparision)) {
                return i;
            }
        }
        return -1;
    }

    //EFFECTS: checks the stocks and uses checkIt
    public boolean ownEnoughShares(Stocks a, Integer shares) {
        if (checkTooSeeIfStockExists(a) == -1) {
            return false;
        } else {
            int sharesOwned = this.shares.get(checkTooSeeIfStockExists(a));
            return sharesOwned >= shares;
        }
    }

    //MODIFIES: this
    //EFFECTS: Removes the stocks
    public void removeStocks(Stocks a, Integer shares) {
        int index = checkTooSeeIfStockExists(a);
        int sharesOwned = this.shares.get(index);
        sharesOwned -= shares;
        if (sharesOwned < 1) {
            stocksOwned.remove(index);
            this.shares.remove(index);
        } else {
            this.shares.set(index, sharesOwned);
        }
    }


    // EFFECTS: display stocks
    public String displayOwnedStocks() {
        int displayIndex = 1;
        int index = 0;
        StringBuilder example = new StringBuilder();
        if (stocksOwned.size() == 0) {
            String error = " You do not own any stocks ";
            return error;
        } else {
            for (Stocks a : stocksOwned) {
                int sharesOwned = shares.get(index);
                example.append(displayIndex).append(". "
                        + "").append("Company Name: ").append(a.getCompanyName()).append("\n").append("Ticker: "
                        + "").append(a.getTicker()).append("\n").append("Price: ").append(a.getPrice()).append("\n"
                        + "").append("Sector: ").append(a.getSector()).append("\n").append("Description: "
                        + "").append(a.getDescription()).append("\n").append("Shares Owned: "
                        + "").append(sharesOwned).append("\n").append("\n");
                displayIndex += 1;
                index = index + 1;
            }
            return example.toString();
        }
    }


}
