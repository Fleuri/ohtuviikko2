/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Tietokantaoperaatiot.DBHandler;
import Tietokantaoperaatiot.DBHandler2;
import com.avaje.ebean.EbeanServer;
import olutopas.model.Brewery;
import olutopas.model.User;

/**
 *
 * @author Lauri
 */
public class addBrewery extends Command{
    
    public addBrewery(DBHandler server, User user) {
        super(server, user);
    }
    
    @Override
    public void run() {
          System.out.print("brewery to add: ");
        String name = scanner.nextLine();
        Brewery brewery = server.findBrewery(name);

        if (brewery != null) {
            System.out.println(name + " already exists!");
            return;
        }

        server.saveBrewery(new Brewery(name));
    }
}
