// CLASS FOR ALERT BOX

package ui.graphics;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alert extends PopUp {

    private VBox layout;


    //MODIFIES: This
    //EFFECTS: Creates an alert box
    public Boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);
        window.setMinHeight(200);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.BASELINE_CENTER);
        setBackground();
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return null;
    }

    private void setBackground() {
        Image image = new Image("https://media0.giphy.com/media/xUPGchd4CmXvggSapW/giphy.gif");
        BackgroundImage backgroundImage =
                new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                        BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        layout.setBackground(background);

    }



}