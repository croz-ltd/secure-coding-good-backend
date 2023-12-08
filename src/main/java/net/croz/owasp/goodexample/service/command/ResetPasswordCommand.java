package net.croz.owasp.goodexample.service.command;

import jakarta.validation.constraints.Pattern;

public class ResetPasswordCommand {

    private String username;

    // OWASP[38]
    // OWASP[39]
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{16,}$")
    private String password;
    private String questionOneAnswer;
    private String questionTwoAnswer;
    private String questionThreeAnswer;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuestionOneAnswer() {
        return questionOneAnswer;
    }

    public void setQuestionOneAnswer(String questionOneAnswer) {
        this.questionOneAnswer = questionOneAnswer;
    }

    public String getQuestionTwoAnswer() {
        return questionTwoAnswer;
    }

    public void setQuestionTwoAnswer(String questionTwoAnswer) {
        this.questionTwoAnswer = questionTwoAnswer;
    }

    public String getQuestionThreeAnswer() {
        return questionThreeAnswer;
    }

    public void setQuestionThreeAnswer(String questionThreeAnswer) {
        this.questionThreeAnswer = questionThreeAnswer;
    }

}
