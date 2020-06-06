// CLASS FOR CONFIRM BOX ALERT

package ui.graphics;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Confirm extends PopUp {
    private boolean answer;
    Stage window;


    // MODIFIES: This
    // EFFECTS: displays confirm box
    public Boolean display(String title, String message) {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);
        window.setMinHeight(250);
        javafx.scene.control.Label label = new javafx.scene.control.Label();
        label.setText(message);

        javafx.scene.control.Button closeButton = new Button("Confirm");
        Button no = new Button("Cancel");

        closeButton.setOnAction(e -> setAnswerToTrue());

        no.setOnAction(e -> {
            answer = false;
            window.close();
        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton, no);
        layout.setAlignment(Pos.BASELINE_CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;


    }

    private void setAnswerToTrue() {
        this.answer = true;
        window.close();
    }

}
