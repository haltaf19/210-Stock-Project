// CLASS FOR PIE CHART DATA

package ui.graphics;

public class DisplayForGui {
    private String name;
    private String ticker;
    private int shares;
    private double price;
    private String sector;

    //EFFECTS: creates a data structure for pie chart
    public DisplayForGui(String name, String ticker, Double price, Integer shares, String sector) {
        this.name = name;
        this.ticker = ticker;
        this.shares = shares;
        this.price = price;
        this.sector = sector;
    }

    public String getTicker() {
        return ticker;
    }

    public double getPrice() {
        return price;
    }

    public int getShares() {
        return shares;
    }

    public String getSector() {
        return sector;
    }

    public String getName() {
        return name;
    }
}
