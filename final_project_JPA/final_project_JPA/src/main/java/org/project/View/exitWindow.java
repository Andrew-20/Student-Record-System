package org.project.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Class for pop-up window on exit of application
 * @author Andrew Finch
 */
public class exitWindow {
    private static boolean result;

    /**
     * Exit window constructor
     */
    public exitWindow() {
    }

    /**
     * Main method for exit Window
     * @param args args
     */
    public static void main(String[] args) {
    }

    /**
     * Handles javafx objects and controls
     * @return boolean result. If the user wants to exit or not.
     */
    public static boolean display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Exit window");
        window.setMinWidth(250.0D);
        Label label = new Label("Do you want to quit?");
        Button save = new Button("Yes");
        save.setStyle("-fx-background-color: #20B2AA; -fx-background-radius: 10px; -fx-text-fill: #ffffff");
        save.setOnAction((e) -> {
            result = true;
            window.close();
        });
        Button cancel = new Button("Cancel");
        cancel.setStyle("-fx-background-color: #e6b800; -fx-background-radius: 10px; -fx-text-fill: #ffffff");
        cancel.setOnAction((e) -> {
            result = false;
            window.close();
        });
        VBox vbox = new VBox();
        vbox.getChildren().addAll(new Node[]{label, save, cancel});
        vbox.setSpacing(10.0D);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10.0D));
        Scene scene = new Scene(vbox);
        window.setScene(scene);
        window.showAndWait();
        return result;
    }
}

