import model.Quest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestTest {

    @Test
    public void testQuestConstructor() {
        String questId = "quest1";
        String question = "What is 2 + 2?";
        String[] options = {"3", "4", "5"};
        int correctAnswer = 1;
        String successMessage = "Correct!";

        Quest quest = new Quest(questId, question, options, correctAnswer, successMessage);

        assertEquals(questId, quest.getQuestId());
        assertEquals(question, quest.getQuestion());
        assertArrayEquals(options, quest.getOptions());
        assertEquals(correctAnswer, quest.getCorrectAnswer());
        assertEquals(successMessage, quest.getSuccessMessage());
    }

    @Test
    public void testQuestToString() {
        String questId = "quest2";
        String question = "What is the capital of France?";
        String[] options = {"Berlin", "Paris", "Madrid"};
        int correctAnswer = 1;
        String successMessage = "Correct!";

        Quest quest = new Quest(questId, question, options, correctAnswer, successMessage);

        String expectedToString = "Quest{questId='quest2', question='What is the capital of France?', options=[Berlin, Paris, Madrid], correctAnswer=1, successMessage='Correct!'}";
        assertEquals(expectedToString, quest.toString());
    }
}