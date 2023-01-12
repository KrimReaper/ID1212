package Model;

import java.io.Serializable;

/**
 * This JavaBean represents a user.
 * 
 * @author Alexander Lundqvist & Ramin Shojaei
 */
public class QuizBean implements Serializable {
    private int id;
    private String subject;
    private String[] options;
    
    /**
     * No-argument constructor for the Bean.
     */
    public QuizBean() {
    }
    
    /**
     * Getter for the id field.
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Setter for the id field.
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the subject field.
     * @return the subject
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * Setter for the subject field.
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
}
