package model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username = "";
    private String password = "";
    private String firstname = "";
    private String lastname = "";
    private List<Interest> interests = new ArrayList<Interest>();

    public User(){        
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public void addInterest(Interest interest) {
        this.interests.add(interest);
    }
    
    public void removeInterest(Interest interest) {
        if(this.interests.contains(interest)) {
            interests.remove(interest);
        }
    }
    
    public void clearInterests() {
        interests = new ArrayList<Interest>();
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public enum Interest{
        WEBENINEERING, MODELENGINEERING, SEMANTICWEB, OBJECTORIENTEDMODELING, BUSINESSINFORMATICS;
    }
}
