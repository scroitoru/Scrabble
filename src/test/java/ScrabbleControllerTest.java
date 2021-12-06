import croitoru.scrabble.LetterBag;
import croitoru.scrabble.ScrabbleBoardController;
import croitoru.scrabble.ScrabbleDictionary;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.junit.BeforeClass;
import org.junit.Test;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ScrabbleControllerTest {

    private LetterBag letterBag;
    private ScrabbleDictionary dictionary;
    private ScrabbleBoardController controller;
    private List<Label> answerLabels;
    private List<Button> letterButtons;
    private Label pointsLabel;

    @BeforeClass
    public static void beforeClass() {
        com.sun.javafx.application.PlatformImpl.startup(()->{});
    }

    @Test
    public void initialize() {
        // given
        givenScrabbleController();
        doReturn("T",
                "G",
                "L")
                .when(letterBag).nextLetter();

        // when
        controller.initialize();

        // then
        verify(controller.letterButtons.get(0)).setText("T");
        verify(controller.letterButtons.get(1)).setText("G");
        verify(controller.letterButtons.get(2)).setText("L");
        verify(letterBag, times(3)).nextLetter();
    }

    @Test
    public void onClear_noAnswer() {
        // given
        givenScrabbleController();

        // when
        controller.onClear(mock(ActionEvent.class));

        // then
        for (Label label : answerLabels) {
            verify(label).getText();
        }

        for (Button button : letterButtons) {
            verifyNoInteractions(button);
        }
    }

    @Test
    public void onClear_someAnswer() {
        // given
        givenScrabbleController();
        doReturn("").when(answerLabels.get(0)).getText();
        doReturn("H").when(answerLabels.get(1)).getText();
        doReturn("").when(answerLabels.get(2)).getText();

        doReturn("G").when(letterButtons.get(0)).getText();
        doReturn("").when(letterButtons.get(1)).getText();
        doReturn("").when(letterButtons.get(2)).getText();

        // when
        controller.onClear(mock(ActionEvent.class));

        // then
        verify(answerLabels.get(1)).getText();
        verify(answerLabels.get(1)).setText("");
        verify(letterButtons.get(1)).setText("H");
        verify(letterButtons.get(0), never()).setText(anyString());
        verify(letterButtons.get(2), never()).setText(anyString());
    }

    @Test
    public void onAnswerClicked() {
        // given
        givenScrabbleController();
        doReturn("").when(answerLabels.get(0)).getText();
        doReturn("H").when(answerLabels.get(1)).getText();
        doReturn("").when(answerLabels.get(2)).getText();

        doReturn("G").when(letterButtons.get(0)).getText();
        doReturn("").when(letterButtons.get(1)).getText();
        doReturn("").when(letterButtons.get(2)).getText();
        MouseEvent event = mock(MouseEvent.class);
        doReturn(answerLabels.get(1)).when(event).getSource();

        // when
        controller.onAnswerClicked(event);

        // then
        verify(answerLabels.get(1)).setText("");
        verify(letterButtons.get(1)).setText("H");
        verify(letterButtons.get(0), never()).setText(anyString());
        verify(letterButtons.get(2), never()).setText(anyString());
    }

    @Test
    public void onLetterClicked() {
        // given
        givenScrabbleController();
        doReturn("G").when(answerLabels.get(0)).getText();
        doReturn("").when(answerLabels.get(1)).getText();
        doReturn("").when(answerLabels.get(2)).getText();

        doReturn("").when(letterButtons.get(0)).getText();
        doReturn("H").when(letterButtons.get(1)).getText();
        doReturn("").when(letterButtons.get(2)).getText();
        MouseEvent event = mock(MouseEvent.class);
        doReturn(letterButtons.get(1)).when(event).getSource();

        // when
        controller.onLetterClicked(event);

        // then
        verify(letterButtons.get(1)).setText("");
        verify(answerLabels.get(1)).setText("H");
        verify(answerLabels.get(0), never()).setText(anyString());
        verify(answerLabels.get(2), never()).setText(anyString());
    }

    @Test
    public void onSubmit_validWord() {
        // given
        givenScrabbleController();
        doReturn("C").when(answerLabels.get(0)).getText();
        doReturn("A").when(answerLabels.get(1)).getText();
        doReturn("T").when(answerLabels.get(2)).getText();
        doReturn("").when(letterButtons.get(0)).getText();
        doReturn("").when(letterButtons.get(1)).getText();
        doReturn("").when(letterButtons.get(2)).getText();
        doReturn(true).when(dictionary).wordPresent("CAT");
        doReturn("E", "S", "T")
                .when(letterBag).nextLetter();

        // when
        controller.onSubmit(mock(ActionEvent.class));

        // then
        verify(dictionary).wordPresent("CAT");
        verify(pointsLabel).setText("3");
        Assert.assertEquals(3, controller.points);
        // TODO verify new letters
        // TODO verify clear labels
    }

    @Test
    public void onSubmit_invalidWord() {
        // given
        givenScrabbleController();
        doReturn("Q").when(answerLabels.get(0)).getText();
        doReturn("W").when(answerLabels.get(1)).getText();
        doReturn("S").when(answerLabels.get(2)).getText();
        doReturn(false).when(dictionary).wordPresent("QWS");

        // when
        controller.onSubmit(mock(ActionEvent.class));

        // then
        verify(dictionary).wordPresent("QWS");
        verifyNoInteractions(pointsLabel);
        Assert.assertEquals(0, controller.points);
        // TODO verify no new letters
        // TODO verify labels not cleared
    }

    private void givenScrabbleController() {
        letterBag = mock(LetterBag.class);
        dictionary = mock(ScrabbleDictionary.class);
        controller = new ScrabbleBoardController(dictionary, letterBag);
        letterButtons = Arrays.asList(
                mock(Button.class),
                mock(Button.class),
                mock(Button.class)
        );
        controller.letterButtons = letterButtons;
        answerLabels = Arrays.asList(
                mock(Label.class),
                mock(Label.class),
                mock(Label.class)
        );
        controller.answerLabels = answerLabels;
        pointsLabel = mock(Label.class);
        controller.pointsLabel = pointsLabel;
    }
}
