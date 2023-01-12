package Integration;

import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * This class handles transactions aimed at an Apache Derby database that 
 * follows the schema described in the assignment. The DB mainly contains the 
 * quizzes and registered users.
 * 
 * Tables in Derby DB:
 *      users       (id, username, password)
 *      questions   (id, text, options, answer)
 *      quizzes     (id, subject)
 *      selector    (quiz_id, question_id)
 *      results     (id, user_id, quiz_id, score)
 * 
 *
 * @author Alexander Lundqvist & Ramin Shojaei
 */
public class DBhandler {
    private Connection connection;
    private Statement statement;
    
    /* Development values */
    private HashMap<Integer, String[]> users;
    private HashMap<Integer, String[]> questions;
    private HashMap<Integer, String[]> quizzes;
    private HashMap<Integer, Integer> selector;
    private HashMap<Integer, String[]> results;
    
    /**
     * CONNECT 'jdbc:derby:test;create=true;user=nbuser;password=nbuser';
     * -- Above command in NetBeans
     *  CONNECT 'jdbc:derby:test;user=nbuser;password=nbuser';
     * Constructor for the handler.
     */
    public DBhandler() {
        /* Development values */
        this.users = new HashMap<>();
        this.questions = new HashMap<>();
        this.quizzes = new HashMap<>();
        this.quizzes = new HashMap<>();
        this.results = new HashMap<>();
        
        this.users.put(1, {})
        
        /*
        CONNECT 'jdbc:derby:test;create=true;user=nbuser;password=nbuser';
        -- Above command in NetBeans
        CONNECT 'jdbc:derby:test;user=nbuser;password=nbuser';
        */
        /*
        try{
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource source = (DataSource)envContext.lookup("jdbc/derby");
            Connection connection = source.getConnection();
            Statement statement = connection.createStatement();
        }
        catch(Exception exception){
            System.err.println(exception.getMessage());
        }
        */
    }
    
    
    
    public String[] getQuestions() {
        
    }
    
    public String getSubjects() {
        
    }
    
    
}
