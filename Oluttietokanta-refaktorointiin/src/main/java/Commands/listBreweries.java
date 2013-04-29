/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Tietokantaoperaatiot.DBHandler;
import Tietokantaoperaatiot.DBHandler2;
import com.avaje.ebean.EbeanServer;
import java.util.List;
import olutopas.model.Brewery;
import olutopas.model.User;

/**
 *
 * @author Lauri
 */
public class listBreweries extends Command {

    public listBreweries(DBHandler server, User user) {
        super(server, user);
    }

    @Override
    public void run() {
        List<Brewery> breweries = server.listBreweries();
        for (Brewery brewery : breweries) {
            System.out.println(brewery);
        }
    }
}
