/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import olutopas.model.Brewery;
import olutopas.model.User;

/**
 *
 * @author Lauri
 */
public class listBreweries extends Command {

    public listBreweries(EbeanServer server, User user) {
        super(server, user);
    }

    @Override
    public void run() {
        List<Brewery> breweries = server.find(Brewery.class).findList();
        for (Brewery brewery : breweries) {
            System.out.println(brewery);
        }
    }
}
