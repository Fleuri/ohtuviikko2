/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import olutopas.model.Beer;
import olutopas.model.User;

/**
 *
 * @author Lauri
 */
public class listBeers extends Command {
    
     public listBeers (EbeanServer server, User user) {
        super(server, user);
    }

    @Override
    public void run() {
         List<Beer> beers = server.find(Beer.class).orderBy("brewery.name").findList();
        for (Beer beer : beers) {
            System.out.println(beer);
            if (beer.getRatings() != null && !beer.getRatings().isEmpty()) {
                System.out.println("  ratings given "+beer.getRatings().size() + " average " + beer.averageRating());
            } else {
                System.out.println("  no ratings");
            }
        } }
    
}
