package persistence;

import model.account.Account;
import model.account.StocksOwned;
import model.stocks.Stocks;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
// CLASS TO SAVE THE DATA

public class Writer {
    ArrayList<Stocks> stocks;
    ArrayList<Integer> share;
    double balance;
    JSONArray masterArray;
    JSONArray stocksArray;
    JSONArray sharesArray;
    String name;

    // EFFECTS: Initialize a writer
    public Writer(StocksOwned stocks, Account account, String name) {
        this.name = name;
        this.stocks = stocks.getStocksOwned();
        this.share = stocks.getShares();
        this.balance = account.getBalance();
        masterArray = new JSONArray();
        stocksArray = new JSONArray();
        sharesArray = new JSONArray();
        name = "./data/account.json";


    }

    //MODIFES: This
    //EFFECTS: Writes the files to accounts.json
    public void writer() {

        // add stocks to the stocksArray

        inputStocks(this.stocks);
        masterArray.add(stocksArray);

        // add shares to shares arrayy
        for (Integer s : share) {
            sharesArray.add(Integer.toString(s));
        }

        masterArray.add(sharesArray);
        //add to balance
        masterArray.add(balance);

        //Write JSON file
        try (FileWriter file = new FileWriter(new File(name))) {
            file.write(masterArray.toJSONString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //MODIFIES: This
    //EFFECTS: Helper to add stocks to fields
    public void inputStocks(ArrayList<Stocks> stocks) {
        for (Stocks s : stocks) {
            JSONObject stock = new JSONObject();
            stock.put("Company Name", s.getCompanyName());
            stock.put("Price", s.getPrice());
            stock.put("Sector", s.getSector());
            stock.put("Ticker", s.getTicker());
            stock.put("Description", s.getDescription());
            stocksArray.add(stock);
        }
    }

}

