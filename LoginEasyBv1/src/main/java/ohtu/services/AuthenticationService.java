package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class AuthenticationService {

    private UserDao userDao;
    @Autowired
    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        if (username.length() < 3) {
            return true;
        } else {
            for (int i = 0; i < username.length(); i++) {
                char c = username.charAt(i);
                for (int j = 0; j < letters.length(); j++) {
                    if (letters.charAt(j) == c) {
                        break;
                    } else if (j == letters.length() - 1) {
                        return true;
                    }
                }
            }
                if (password.length() < 8) {
                    return true;
                } else {
                    boolean letterfound = false;
                    boolean digitfound = false;
                    for (int k = 0; k < password.length(); k++) {
                        char c2 = password.charAt(k);
                        for (int j = 0; j < letters.length(); j++) {
                            if (letters.charAt(j) == c2) {
                                letterfound = true;
                                break;
                            }
                        }
                        if (letterfound) {
                            break;
                        }
                    }
                    for (int k = 0; k < password.length(); k++) {
                        char c2 = password.charAt(k);
                        for (int j = 0; j < digits.length(); j++) {
                            if (digits.charAt(j) == c2) {
                                digitfound = true;
                                break;
                            }
                        }
                        if (digitfound) {
                            break;
                        }
                    }
                    if (letterfound && digitfound) {return false;} 
                    else {return true;}
                }
            }
        }
    }

