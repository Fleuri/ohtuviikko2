/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import com.avaje.ebean.EbeanServer;
import olutopas.model.Rating;
import olutopas.model.User;

/**
 *
 * @author Lauri
 */
public class myRatings extends Command {

    public myRatings(EbeanServer server, User user) {
        super(server, user);
    }

    @Override
    public void run() {
        System.out.println("Ratings by " + user.getName());
        for (Rating rating : user.getRatings()) {
            System.out.println(rating);
        }
    }
}
