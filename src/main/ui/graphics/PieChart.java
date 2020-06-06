// CLASS THAT CREATES A PIECHART


package ui.graphics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.account.StocksOwned;


public class PieChart {
    private Stage window;
    private javafx.scene.chart.PieChart pieChart;


    public PieChart() {
    }

    // MODIFIES: This
    // EFFECTS: Displays Pie Chart
    public void viewChart(Event event) {
        window = new Stage();
        window.setTitle("Portfolio Diversification");
        window.setMinWidth(600);
        window.setMinHeight(400);

        ObservableList<javafx.scene.chart.PieChart.Data> list = FXCollections.observableArrayList();
        StocksOwned owned = event.getStocksOwned();
        int index = 0;


        for (int i = 0; i < owned.getStocksOwned().size(); i++) {
            Integer shares = owned.getShares().get(index);
            String sector = owned.getStocksOwned().get(index).getSector();
            list.add(new javafx.scene.chart.PieChart.Data(sector, shares));
            index++;
        }

        pieChart = new javafx.scene.chart.PieChart(list);
        pieChart.setData(list);

        Group root = new Group(pieChart);


        Button no = new Button("Quit");
        no.setOnAction(e -> window.close());
        VBox box = new VBox();
        box.getChildren().addAll(root, no);
        finish(box);
    }


    // MODIFIES: This
    // EFFECTS: Finishs set up
    private void finish(VBox box) {
        Scene scene = new Scene(box);
        window.setScene(scene);
        window.showAndWait();
    }

}
