package application;

import engine.Engine;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Shop extends Application {

    private Engine engine = Engine.getInstance();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent homeRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("ui/home.fxml")));
        Scene homeScene = new Scene(homeRoot, 800, 600);
        homeScene.getStylesheets().add(String.valueOf(getClass().getClassLoader().getResource("ui/styleSheet.css")));

        primaryStage.setScene(homeScene);
        primaryStage.setTitle("Fulgur Computing");
        primaryStage.getIcons().add(new Image(String.valueOf(getClass().getClassLoader().getResource("ui/icon.png"))));
        primaryStage.show();

        startDatabase();

        primaryStage.setOnCloseRequest(windowEvent -> {
            adjustStock();
        });
    }

    private void startDatabase() {
        engine.prepareDatabase();
    }

    private void adjustStock() {
        engine.adjustStockIfNoCheckout();
    }

}
