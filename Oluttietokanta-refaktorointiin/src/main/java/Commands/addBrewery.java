/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import com.avaje.ebean.EbeanServer;
import olutopas.model.Brewery;
import olutopas.model.User;

/**
 *
 * @author Lauri
 */
public class addBrewery extends Command{
    
    public addBrewery(EbeanServer server, User user) {
        super(server, user);
    }
    
    @Override
    public void run() {
          System.out.print("brewery to add: ");
        String name = scanner.nextLine();
        Brewery brewery = server.find(Brewery.class).where().like("name", name).findUnique();

        if (brewery != null) {
            System.out.println(name + " already exists!");
            return;
        }

        server.save(new Brewery(name));
    }
}
