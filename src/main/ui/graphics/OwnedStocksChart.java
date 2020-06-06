// Table of Stocks that Are Owned


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
import model.account.StocksOwned;

public class OwnedStocksChart implements StockTable {
    protected TableView<DisplayForGui> ownedStocks;
    protected Stage window;

    // MODIFIES: This
    //EFFECTS: Displays owned stock in alert box table
    public void displayStockTable(Event event) {
        window = new Stage();
        setWindow();
        //Column Ticker
        TableColumn<DisplayForGui, String> companyName = new TableColumn<>("Company Name");
        companyName.setMinWidth(150);
        companyName.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Column Ticker
        TableColumn<DisplayForGui, String> ticker = new TableColumn<>("Ticker");
        ticker.setMinWidth(150);
        ticker.setCellValueFactory(new PropertyValueFactory<>("ticker"));

        //Column Price
        TableColumn<DisplayForGui, Double> price = new TableColumn<>("Price");
        price.setMinWidth(50);
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Column Sector
        TableColumn<DisplayForGui, Integer> shares = new TableColumn<>("Shares");
        shares.setMinWidth(50);
        shares.setCellValueFactory(new PropertyValueFactory<>("shares"));

        //Column Ticker
        TableColumn<DisplayForGui, String> sector = new TableColumn<>("Sector");
        sector.setMinWidth(150);
        sector.setCellValueFactory(new PropertyValueFactory<>("sector"));

        ownedStocks = new TableView<>();
        ownedStocks.setItems(getStocksOwned(event));
        ownedStocks.getColumns().addAll(companyName, ticker, price, shares, sector);

        completeItOwned();
    }

    private void setUpStocksOwned(Event event, ObservableList<DisplayForGui> stocksOwned) {
        StocksOwned owned = event.getStocksOwned();
        int index = 0;


        for (int i = 0; i < owned.getStocksOwned().size(); i++) {
            String ticker = owned.getStocksOwned().get(index).getTicker();
            Double price = owned.getStocksOwned().get(index).getPrice();
            Integer shares = owned.getShares().get(index);
            String sector = owned.getStocksOwned().get(index).getSector();
            String name = owned.getStocksOwned().get(index).getCompanyName();
            stocksOwned.add(new DisplayForGui(name, ticker, price, shares, sector));
            index++;

        }
    }

    // MODFIES: This
    //EFFECTS: Creates a list of stocks owned for the table
    public ObservableList<DisplayForGui> getStocksOwned(Event event) {

        ObservableList<DisplayForGui> stocksOwned = FXCollections.observableArrayList();

        setUpStocksOwned(event, stocksOwned);
        return stocksOwned;
    }

    // MODIFIES: This
    // EFFECTS: complete set up
    protected void completeItOwned() {
        Button no = new Button("Quit");
        no.setOnAction(e -> window.close());
        VBox box = new VBox();
        box.getChildren().addAll(ownedStocks, no);
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
