// A class that creates a stock


package model.stocks;

public class Stocks {
    private String companyName;
    private double price;
    private String sector;
    private String ticker;
    private String description;


    // EFFECTS: stocks constructor
    public Stocks(String companyName, Double price, String sector, String ticker, String description) {
        this.companyName = companyName;
        this.price = price;
        this.sector = sector;
        this.ticker = ticker;
        this.description = description;

    }

    // EFFECTS: Getters
    public double getPrice() {
        return price;
    }

    public String getTicker() {
        return ticker;
    }

    public String getSector() {
        return sector;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getDescription() {
        return description;
    }
}

