package croitoru.scrabble;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class ScrabbleBoardController {

    @FXML
    private List<Label> answerLabels;

    @FXML
    private List<Button> letterButtons;

    @FXML
    Label pointsLabel;

    int points = 0;

    private final LetterBag letterBag;

    private final ScrabbleDictionary dictionary;

    // Dependency Injection
    // If a class depends on other objects, pass those objects in the constructor.
    public ScrabbleBoardController(ScrabbleDictionary dictionary,
                                   LetterBag letterBag) {
        this.dictionary = dictionary;
        this.letterBag = letterBag;
    }

    @FXML
    public void initialize() {
        for (Button button : letterButtons) {
            button.setText(letterBag.nextLetter());
        }
    }

    /**
     * return letter in the answerLabel back to an empty
     * letterButton if this label is not empty
     * @param event
     */
    public void onAnswerClicked(MouseEvent event) {
        Label label = (Label) event.getSource();
        String letter = label.getText();
        if (!letter.isEmpty())
        {
            label.setText("");
            for (Button letterButton : letterButtons){
                String s = letterButton.getText();
                if (s.isEmpty()){
                    letterButton.setText(letter);
                    break;
                }
            }
        }
    }

    /**
     * when the letters are clicked they are moved up to the answer labels
     * @param event
     */
    public void onLetterClicked(MouseEvent event) {
        Button buttonLetter = (Button) event.getSource();
        String letter = buttonLetter.getText();
        if (!letter.isEmpty()){
            buttonLetter.setText("");
            for (Label answerLabel : answerLabels) {
                String s = answerLabel.getText();
                if (s.isEmpty()) {
                    answerLabel.setText(letter);
                    break;
                }
            }
        }

    }

    /**
     * returns the letters in answerLabels to letterButtons
     *
     * @param event
     */
    public void onClear(ActionEvent event) {
        for (Label answerLabel : answerLabels) {
            String letter = answerLabel.getText();
            if (letter != null && !letter.isEmpty()) {
                answerLabel.setText("");
                for (Button letterButton : letterButtons) {
                    if (letterButton.getText().isEmpty()) {
                        letterButton.setText(letter);
                        break;
                    }
                }
            }
        }
    }

    public void onSubmit(ActionEvent event) {
        StringBuilder builder = new StringBuilder();
        for (Label answerLabel : answerLabels) {
            String letter = answerLabel.getText();
            if (letter.isEmpty()) {
                break;
            }
            builder.append(letter);
        }
        String word = builder.toString();
        if (dictionary.wordPresent(word)){
            addPoints(word);
            pointsLabel.setText(String.valueOf(points));
            clearAnswerLabels();
            addNewLetters();
        }
    }

    private void addNewLetters() {
        for (Button letterButton : letterButtons){
            if (letterButton.getText().isEmpty()){
                letterButton.setText(letterBag.nextLetter());
            }
        }
    }

    private void clearAnswerLabels() {
        for (Label answerLabel : answerLabels){
            answerLabel.setText("");
        }
    }

    private void addPoints(String word) {
        switch (word.length()){
            case 2:
                points += 1;
                break;
            case 3:
                points += 3;
                break;
            case 4:
                points += 5;
                break;
            case 5:
                points += 7;
                break;
            case 6:
                points += 11;
                break;
            case 7:
                points += 13;
                break;
        }
    }
}