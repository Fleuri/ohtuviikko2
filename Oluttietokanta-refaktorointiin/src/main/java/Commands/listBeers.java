/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Tietokantaoperaatiot.DBHandler;
import Tietokantaoperaatiot.DBHandler2;
import com.avaje.ebean.EbeanServer;
import java.util.List;
import olutopas.model.Beer;
import olutopas.model.User;

/**
 *
 * @author Lauri
 */
public class listBeers extends Command {
    
     public listBeers (DBHandler server, User user) {
        super(server, user);
    }

    @Override
    public void run() {
         List<Beer> beers = server.listBeers();
        for (Beer beer : beers) {
            System.out.println(beer);
            if (beer.getRatings() != null && !beer.getRatings().isEmpty()) {
                System.out.println("  ratings given "+beer.getRatings().size() + " average " + beer.averageRating());
            } else {
                System.out.println("  no ratings");
            }
        } }
    
}
