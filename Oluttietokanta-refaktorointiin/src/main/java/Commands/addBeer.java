/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Tietokantaoperaatiot.*;
import com.avaje.ebean.EbeanServer;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.User;

/**
 *
 * @author Lauri
 */
public class addBeer extends Command{
    
    public addBeer(DBHandler server, User user) {
        super(server, user);
    }

    @Override
    public void run() {
        System.out.print("to which brewery: ");
        String name = scanner.nextLine();
        Brewery brewery = server.findBrewery(name);

        if (brewery == null) {
            System.out.println(name + " does not exist");
            return;
        }

        System.out.print("beer to add: ");

        name = scanner.nextLine();

        Beer exists = server.findBeer(name);
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        brewery.addBeer(new Beer(name));
        server.saveBrewery(brewery);
        System.out.println(name + " added to " + brewery.getName());
    }
    
}
