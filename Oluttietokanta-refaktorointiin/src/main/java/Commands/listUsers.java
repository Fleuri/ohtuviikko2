/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import olutopas.model.User;

/**
 *
 * @author Lauri
 */
public class listUsers extends Command {

    public listUsers(EbeanServer server, User user) {
        super(server, user);
    }

    @Override
    public void run() {
        List<User> users = server.find(User.class).findList();
        for (User user : users) {
            System.out.println(user.getName() + " " + user.getRatings().size() + " ratings");
        }
    }
}
