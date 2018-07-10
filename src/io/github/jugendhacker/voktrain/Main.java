package io.github.jugendhacker.voktrain;

import io.github.jugendhacker.voktrain.objects.Language;
import io.github.jugendhacker.voktrain.objects.Word;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        DatabaseController databaseController = new DatabaseController();
        Word word = new Word("test", Language.ENGLISH, "test", Language.GERMAN);
        Word word1 = new Word("test2", Language.ENGLISH, "test", Language.GERMAN);
        databaseController.writeWord(word);
        databaseController.writeWord(word1);
        ArrayList<Word> wordList = databaseController.getWordsByTermLanguage(Language.ENGLISH);
        launch(args);
    }
}
