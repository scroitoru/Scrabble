package croitoru.scrabble;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ScrabbleBoardApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        ScrabbleDictionary dictionary = new ScrabbleDictionary();
        LetterBag letterBag = new LetterBag();
        ScrabbleBoardController controller = new ScrabbleBoardController(dictionary,letterBag);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scrabble_board_application.fxml"));
        loader.setController(controller);

        Parent parent = loader.load();
                Scene scene = new Scene(parent, 550, 250);

        stage.setTitle("Scrabble Board");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
