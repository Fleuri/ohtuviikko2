/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author laursuom
 */

public class FileUserDao implements UserDao {
    InMemoryUserDao users;
    Scanner scn;
    File file;
    
    public FileUserDao(File file) throws FileNotFoundException, IOException {
        this.file = file;
        users = new InMemoryUserDao();
        scn = new Scanner(file);
        while (scn.hasNext()) {
            users.add(new User(scn.next(),scn.next()));
            }
        scn.close();
    }
    
    @Override
    public List<User> listAll() {
        return users.listAll();
    }

    @Override
    public User findByName(String name) {
        return users.findByName(name);
    }

    @Override
    public void add(User user) {
        
        try {
            users.add(user);
            FileOutputStream output = new FileOutputStream(file, true);
            OutputStreamWriter writer = new OutputStreamWriter(output);
            writer.append(user.getUsername() + " " + user.getPassword() + "\n" );
            writer.close();
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
