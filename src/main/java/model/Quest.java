package model;

public class Quest {
    private String questId;
    private String question;
    private String[] options;
    private int correctOptionIndex;
    private String successMessage;

    public Quest(String questId, String question, String[] options, int correctOptionIndex, String successMessage) {
        this.questId = questId;
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
        this.successMessage = successMessage;
    }

    public String getQuestId() {
        return questId;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public String getSuccessMessage() {
        return successMessage;
    }
}
