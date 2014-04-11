package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UserPool {
    HashMap<String, User> users = new HashMap<String, User>();
    
    public void registerUser(User user) {
        if(!users.containsKey(user.getUsername())) {
            users.put(user.getUsername(), user);
        }
    }
    
    public List<User> getUsers() {
        return new ArrayList<User>(users.values());
    }
    
    public User getUser(String username, String password) {
        User user;
        if ((user = users.get(username)) != null) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    
    public User getUser(String username) {
        User user;
        if ((user = users.get(username)) != null) {
            return user;            
        }
        return null;
    }
    
}
