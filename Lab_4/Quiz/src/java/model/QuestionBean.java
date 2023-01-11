package model;

import java.io.Serializable;

/**
 * This JavaBean represents a question.
 * 
 * @author Alexander Lundqvist & Ramin Shojaei
 */
public class QuestionBean implements Serializable {
    private String question;
    private String[] options;
    private String answer;
    
    /**
     * No-argument constructor for the Bean.
     */
    public QuestionBean() {
    }

    /**
     * Getter for the question field.
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Setter for the question field.
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Getter for the options field.
     * @return the options
     */
    public String[] getOptions() {
        return this.options;
    }

    /**
     * Setter for the options field.
     * @param options the options to set
     */
    public void setOptions(String[] options) {
        this.options = options;
    }

    /**
     * Getter for the answer field.
     * @return the answer
     */
    public String getAnswer() {
        return this.answer;
    }

    /**
     * Setter for the answer field.
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    
    
}
