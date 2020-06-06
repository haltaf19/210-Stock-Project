// Stocks that are in the Market

package ui.graphics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.stocks.Stocks;

import java.util.ArrayList;

public class MarketStocksChart implements StockTable {
    protected TableView<Stocks> marketStocks;
    protected Stage window;

    public void displayStockTable(Event event) {
        window = new Stage();
        setWindow();
        //Column Name
        TableColumn<Stocks, String> name = new TableColumn<>("Company Name");
        name.setMinWidth(150);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Column Price
        TableColumn<Stocks, Double> price = new TableColumn<>("Price");
        price.setMinWidth(50);
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Column Sector
        TableColumn<Stocks, String> sector = new TableColumn<>("Sector");
        sector.setMinWidth(150);
        sector.setCellValueFactory(new PropertyValueFactory<>("sector"));

        //Column Ticker
        TableColumn<Stocks, String> ticker = new TableColumn<>("Ticker");
        ticker.setMinWidth(50);
        ticker.setCellValueFactory(new PropertyValueFactory<>("ticker"));

        //Column Description
        TableColumn<Stocks, String> description = new TableColumn<>("Description");
        description.setMinWidth(650);
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        marketStocks = new TableView<>();
        marketStocks.setItems(getStocksInMarket(event));
        marketStocks.getColumns().addAll(price, sector, ticker, description);

        completeTableSetUp();
    }

    //MODIFIES: This
    //EFFECTS: Creates a list of stocks in market for the table
    private ObservableList<Stocks> getStocksInMarket(Event event) {
        ObservableList<Stocks> stocksInMarket = FXCollections.observableArrayList();

        ArrayList<Stocks> market = event.getMarketStocks();
        stocksInMarket.addAll(market);

        return stocksInMarket;
    }

    // MODIFIES: This
    // EFFECTS: Complete set up
    private void completeTableSetUp() {
        Button no = new Button("Quit");
        no.setOnAction(e -> window.close());
        VBox box = new VBox();
        box.getChildren().addAll(marketStocks, no);
        Scene scene = new Scene(box);
        window.setScene(scene);
        window.showAndWait();
    }


    // MODIFIES: This
    // EFFECTS: Creates a new window
    protected void setWindow() {
        window.setTitle("List Of Stocks");
        window.setMinWidth(600);
        window.setMinHeight(400);
    }
}
