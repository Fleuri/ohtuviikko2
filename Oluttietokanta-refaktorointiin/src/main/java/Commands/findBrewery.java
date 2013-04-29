/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import com.avaje.ebean.EbeanServer;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.User;

/**
 *
 * @author Lauri
 */
public class findBrewery extends Command {

    public findBrewery(EbeanServer server, User user) {
        super(server, user);
    }
    
    @Override
    public void run() {
       System.out.print("brewery to find: ");
        String n = scanner.nextLine();
        Brewery foundBrewery = server.find(Brewery.class).where().like("name", n).findUnique();

        if (foundBrewery == null) {
            System.out.println(n + " not found");
            return;
        }

        System.out.println(foundBrewery);
        for (Beer bier : foundBrewery.getBeers()) {
            System.out.println("   " + bier.getName());
        }
    }  }
    

