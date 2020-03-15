package ImageHoster.service;

import ImageHoster.model.User;
import ImageHoster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Call the registerUser() method in the UserRepository class to persist the user record in the database
    public void registerUser(User newUser) {
        userRepository.registerUser(newUser);
    }

    //Since we did not have any user in the database, therefore the user with username 'upgrad' and password 'password' was hard-coded
    //This method returned true if the username was 'upgrad' and password is 'password'
    //But now let us change the implementation of this method
    //This method receives the User type object
    //Calls the checkUser() method in the Repository passing the username and password which checks the username and password in the database
    //The Repository returns User type object if user with entered username and password exists in the database
    //Else returns null
    public User login(User user) {
        User existingUser = userRepository.checkUser(user.getUsername(), user.getPassword());
        if (existingUser != null) {
            return existingUser;
        } else {
            return null;
        }
    }

    /**
     * Validates password string
     * @param password the password to validate
     * @return true if the password is valid else false
     */
    public boolean validatePassword(String password) {
        int countNumbers = 0;
        int countChars = 0;
        int countSpecialChars = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (ch >= '0' && ch <= '9') {
                countNumbers++;
            } else if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
                countChars++;
            } else {
                countSpecialChars++;
            }
        }
        boolean validPassword = (countChars > 0 && countNumbers > 0 && countSpecialChars > 0);
        return  validPassword;
    }

}
