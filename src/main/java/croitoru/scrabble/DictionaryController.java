package croitoru.scrabble;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;

public class DictionaryController {
    @FXML
    TextField wordSearchField;
    @FXML
    private Text actionTarget;

    public void wordSearch(ActionEvent actionEvent) throws FileNotFoundException {
        String isWord = wordSearchField.getText();

        ScrabbleDictionary scrabble = new ScrabbleDictionary();
        //if in dictionary
        if (scrabble.wordPresent(isWord)){
            actionTarget.setText(isWord + " is present in dictionary");
        }
        //if not
        else {
            actionTarget.setText("sorry, " + isWord + " is not present in dictionary.");
        }
    }
}
