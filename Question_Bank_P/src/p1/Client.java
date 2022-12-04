package p1;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Client extends Application {
    private double x, y;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("hp.fxml"));
        root.getStylesheets()
                .add("C:/Users/brade/OneDrive/Documents/Fall 2022/CSCI 3331/Question-Bank/Question-Bank/css");
        primaryStage.setScene(new Scene(root));
        // set stage borderless
        primaryStage.initStyle(StageStyle.UNDECORATED);

        // drag it here
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {

            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);

        });
        primaryStage.show();
    }
}