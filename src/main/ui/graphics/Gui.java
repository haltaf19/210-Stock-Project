// CLASS FOR GUI AND CREATING MAIN STAGE

package ui.graphics;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import exceptions.CanNotFindException;
import ui.exceptions.NotEnoughException;
import ui.exceptions.TooPoorException;


public class Gui extends Application {


    private int width = 720;
    private int height = 510;
    private PopUp alert;
    private Stage window;
    private Event event;
    private PopUp confirm;
    private GridPane grid;
    private PieChart pieChart;
    private StockTable ownedStockTable;
    private StockTable marketStockTable;

    // EFFECTS: Runs the Game from this class
    public static void main(String[] args) {
        launch(args);
    }

    // MODIFIES: This
    // EFFECTS: sets up stage and adds in all the buttons
    @Override
    public void start(Stage primaryStage) {
        event = new Event();
        alert = new Alert();
        confirm = new Confirm();
        pieChart = new PieChart();
        ownedStockTable = new OwnedStocksChart();
        marketStockTable = new MarketStocksChart();
        event.loadGame();
        window = primaryStage;
        window.setTitle("Stock Market Simulator");
        window.setOnCloseRequest(e -> closeProgram());


        grid = new GridPane();
        grid.setPadding(new Insets(11, 11, 20, 10));
        grid.setVgap(15.0);
        grid.setHgap(10.0);

        setBackground();
        makePurchasingButton();
        makeSellingButton();
        makeOtherButtons();
        displayAccount();

        Scene scene = new Scene(grid, width, height);

        window.setScene(scene);
        window.show();
    }

    //MODIFIES: This
    //EFFECTS: Creates buttons for the program
    private void makeOtherButtons() {
        //Save Game
        Button saveGame = new Button("Save Game");
        saveGame.setOnAction(e -> setSaveGame());
        GridPane.setConstraints(saveGame, 0, 7);

        //Quit Program
        Button quit = new Button("Quit Game");
        quit.setOnAction(e -> closeProgram());
        GridPane.setConstraints(quit, 3, 6);

        Button viewStocks = new Button("Stocks In Market");
        viewStocks.setOnAction(e -> marketStockTable.displayStockTable(event));
        GridPane.setConstraints(viewStocks, 0, 5);

        Button viewOwnedStocks = new Button("View Stocks You Own");
        viewOwnedStocks.setOnAction(e -> ownedStockTable.displayStockTable(event));
        GridPane.setConstraints(viewOwnedStocks, 3, 5);

        Button viewDiversity = new Button("Portfolio Diversity");
        viewDiversity.setOnAction(e -> pieChart.viewChart(event));
        GridPane.setConstraints(viewDiversity, 0, 6);

        grid.getChildren().addAll(saveGame, quit, viewStocks, viewOwnedStocks, viewDiversity);
    }

    // MODIFIE: This
    // EFFECTS: Creates buttons relating to selling
    private void makeSellingButton() {
        //SellStock Label
        javafx.scene.control.Label sellStock = new Label("Sell Stock");
        sellStock.setStyle("-fx-border-color:red; -fx-background-color: silver; -fx-padding: 5 10 5 10;");
        GridPane.setConstraints(sellStock, 2, 0);

        // Sellstock Input
        TextField sellStockInput = new TextField();
        GridPane.setConstraints(sellStockInput, 3, 0);
        sellStockInput.setPromptText("Ticker Value, Eg. PER");
        sellStockInput.setStyle("-fx-background-color: #696969;  -fx-border-color: black; -fx-padding: 5 10 5 10;");


        //# of Shares Label
        javafx.scene.control.Label sellShares = new Label("Number of Shares");
        GridPane.setConstraints(sellShares, 2, 1);
        sellShares.setStyle("-fx-border-color:red; -fx-background-color: silver; -fx-padding: 5 10 5 10;");

        // Shares Input
        TextField sellSharesInput = new TextField();
        GridPane.setConstraints(sellSharesInput, 3, 1);
        sellSharesInput.setPromptText("# of Shares, Eg. 7");
        sellSharesInput.setStyle("-fx-background-color: #696969;  -fx-border-color: black; -fx-padding: 5 10 5 10;"
                + " -fx-border-color: black;");

        Button completeSell = new Button("Sell");
        completeSell.setStyle("-fx-padding: 10 20 10 20; -fx-background-color: #32CD32; ");
        completeSell.setOnAction(e -> sell(sellStockInput.getText(), sellSharesInput.getText()));
        GridPane.setConstraints(completeSell, 3, 2);
        grid.getChildren().addAll(sellStock, sellStockInput, sellShares, sellSharesInput, completeSell);

    }

    // MODIFIES: This
    // EFFECTS: Creates buttons relating to purchasing
    private void makePurchasingButton() {
        //Buystock Label
        javafx.scene.control.Label buyStock = new Label("Purchase Stock");
        GridPane.setConstraints(buyStock, 0, 0);
        buyStock.setStyle("-fx-border-color:red; -fx-background-color: silver; -fx-padding: 5 10 5 10;");

        // Buystock Input
        TextField stockInput = new TextField();
        GridPane.setConstraints(stockInput, 1, 0);
        stockInput.setPromptText("Ticker Value, Eg. PER");
        stockInput.setStyle("-fx-background-color: #696969;  -fx-border-color: black; -fx-padding: 5 10 5 10;");

        //# of Shares Label
        javafx.scene.control.Label shares = new Label("Number of Shares");
        GridPane.setConstraints(shares, 0, 1);
        shares.setStyle("-fx-border-color:red; -fx-background-color: silver; -fx-padding: 5 10 5 10;");

        // Shares Input
        TextField sharesInput = new TextField();
        GridPane.setConstraints(sharesInput, 1, 1);
        sharesInput.setPromptText("# of Shares, Eg. 7");
        sharesInput.setStyle("-fx-background-color: #696969; -fx-border-color: black; -fx-padding: 5 10 5 10;");

        Button completePurchase = new Button("Purchase");
        completePurchase.setStyle("-fx-padding: 5 10 5 10; -fx-background-color: #FA8072; "
                + " -fx-border-color: black;");
        completePurchase.setOnAction(e -> purchase(stockInput.getText(), sharesInput.getText()));
        GridPane.setConstraints(completePurchase, 0, 2);


        grid.getChildren().addAll(buyStock, stockInput, shares, sharesInput, completePurchase);
    }

    //MODIFIES This
    //EFFECTS: displays account data
    private void displayAccount() {
        Label displayBalance = new Label("Account Balance: $" + updateLabel());
        GridPane.setConstraints(displayBalance, 1, 10);
        displayBalance.setStyle("-fx-border-color:blue; -fx-background-color: #90EE90; -fx-padding: 5 10 5 10;"
                + "-fx-font-size: 16px;");

        grid.getChildren().addAll(displayBalance);
    }

    //MODIFIES: This
    //EFFECTS: purchase method and calls the alert
    private void purchase(String ticker, String shares) {
        try {
            event.doBuy(ticker, shares);
            alert.display("Confirmation", "Stocks have been purchased successfully");
            displayAccount();
        } catch (CanNotFindException a) {
            alert.display("Error", "Stock could not be found");
        } catch (TooPoorException b) {
            alert.display("Error", "Could not purchase that many stocks");
        } catch (Exception e) {
            alert.display("Error", "An Error Occurred");
        }
    }

    // MODIFIES: This
    // EFFECTS:  selling methods and updates balance
    private void sell(String ticker, String shares) {
        try {
            event.doSell(ticker, shares);
            alert.display("Confirmation", "Stocks have been sold successfully");
            displayAccount();
        } catch (CanNotFindException e) {
            alert.display("Error", "Stock could not be found");
        } catch (NotEnoughException s) {
            alert.display("Error", "Could not sell that many stocks");
        } catch (Exception s) {
            alert.display("Error", "An Error Occurred");
        } finally {
            System.out.println("It went through");
        }
    }

    // MODIFIES: this
    //EFFECTS: Saves the game
    private void setSaveGame() {
        event.saveGame();
        alert.display("Save Confirmation", "Your game has been saved");
    }

    // MODIFIES: This
    // EFFECTS: closes the program with alert box
    private void closeProgram() {
        boolean result = confirm.display("Quit Game", "Are you sure you would like to quit? Your"
                + " progress will not be saved!");
        if (result) {
            window.close();
        }

    }


    // REQUIRES: INTERNET CONNECTION
    // MODIFIES: This
    // EFFECTS: adds background image
    private void setBackground() {
        Image image = new Image("https://i.kym-cdn.com/photos/images/newsfeed/001/499/826/2f0.png");
        BackgroundImage backgroundImage =
                new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                        BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        grid.setBackground(background);

    }

    // EFFECTS: creates account balance as a strring
    private String updateLabel() {
        double balance = event.getUserAccount().getBalance();
        return Double.toString(balance);
    }


}



