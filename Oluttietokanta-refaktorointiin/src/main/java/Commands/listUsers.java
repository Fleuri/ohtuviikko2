/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Tietokantaoperaatiot.DBHandler;
import Tietokantaoperaatiot.DBHandler2;
import com.avaje.ebean.EbeanServer;
import java.util.List;
import olutopas.model.User;

/**
 *
 * @author Lauri
 */
public class listUsers extends Command {

    public listUsers(DBHandler server, User user) {
        super(server, user);
    }

    @Override
    public void run() {
        List<User> users = server.listUsers();
        for (User user : users) {
            System.out.println(user.getName() + " " + user.getRatings().size() + " ratings");
        }
    }
}
