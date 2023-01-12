package model;

import java.io.Serializable;

/**
 * This JavaBean represents a user.
 * 
 * @author Alexander Lundqvist & Ramin Shojaei
 */
public class UserBean implements Serializable {
    private String username;
    private String password;
    private String result;
    
    /**
     * No-argument constructor for the Bean.
     */
    public UserBean() {
    }
    
    /**
     * Getter for the username field.
     * @return the username
     */
    public String getUsername(){
        return this.username;
    }
    
    /**
     * Setter for the username field.
     * @param username the username to set
     */
    public void setUsername(String username){
        this.username = username;
    }
    
    /**
     * Getter for the password field.
     * @return the password
     */
    public String getPassword(){
        return this.password;
    }
    
    /**
     * Setter for the password field.
     * @param password the password to set
     */
    public void setPassword(String password){
        this.password = password;
    }   
}
