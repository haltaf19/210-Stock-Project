package persistence;

// CLASS TO READ THE DATA


import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import model.stocks.Stocks;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Reader {
    ArrayList<Stocks> stocksOwned = new ArrayList<>();
    ArrayList<Integer> sharesOwned = new ArrayList<>();
    Double balance;
    JSONParser jsonParser = new JSONParser();
    String name;


    //EFFECTS: reader constructor
    public Reader(String name) {
        this.name = name;


    }

    //EFFECTS: GETTERS
    public String getName() {
        return name;
    }

    public ArrayList<Stocks> getStocks() {
        return stocksOwned;
    }

    public ArrayList<Integer> getInteger() {
        return sharesOwned;
    }

    public double getBalance() {
        return balance;
    }

    //REQUIRES: Something in the account.Json
    //MODIFIES: This
    //EFFECTS: Reads the data and adds it
    public void readData() {
        //JSON parser object to parse read file
        try (FileReader reader = new FileReader(new File(name))) {
            //Read JSON file
            JSONArray masterArray = (JSONArray) jsonParser.parse(reader);
            JSONArray stocks = (JSONArray) masterArray.get(0);
            JSONArray integers = (JSONArray) masterArray.get(1);
            this.balance = (Double)masterArray.get(2);

            makeStock(stocks);


            for (int i = 0; i < integers.size(); i++) {
                Object objects = integers.get(i);
                int z = Integer.parseInt((String) objects);
                sharesOwned.add(z);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } // NOTE = STARTING POINT FOR JSON FILE IS "[[],[],500.0]"

    }

    // MODIFIES: This
    // EFFECTS: extracts stocks
    public void makeStock(JSONArray stocks) {
        for (Object o : stocks) {
            JSONObject stock = (JSONObject) o;
            Stocks stock1 =
                    new Stocks(
                            (String) stock.get("Company Name"),
                            (Double) stock.get("Price"),
                            (String) stock.get("Sector"),
                            (String) stock.get("Ticker"),
                            (String) stock.get("Description"));
            this.stocksOwned.add(stock1);
        }
    }

}

