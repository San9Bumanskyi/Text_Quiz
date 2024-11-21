package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class Quest {
    private String questId;
    private String question;
    private String[] options;
    private int correctAnswer;
    private String successMessage;

    @JsonCreator
    public Quest(
            @JsonProperty("id") String questId,
            @JsonProperty("question") String question,
            @JsonProperty("options") String[] options,
            @JsonProperty("correctAnswer") int correctAnswer,
            @JsonProperty("successMessage") String successMessage) {
        this.questId = questId;
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.successMessage = successMessage;
    }

    // Геттери й сеттери
    public String getQuestId() { return questId; }
    public void setQuestId(String questId) { this.questId = questId; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String[] getOptions() { return options; }
    public void setOptions(String[] options) { this.options = options; }

    public int getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(int correctAnswer) { this.correctAnswer = correctAnswer; }

    public String getSuccessMessage() { return successMessage; }
    public void setSuccessMessage(String successMessage) { this.successMessage = successMessage; }

    @Override
    public String toString() {
        return "Quest{" +
                "questId='" + questId + '\'' +
                ", question='" + question + '\'' +
                ", options=" + Arrays.toString(options) +
                ", correctAnswer=" + correctAnswer +
                ", successMessage='" + successMessage + '\'' +
                '}';
    }
}
