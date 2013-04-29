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
public class addBeer extends Command{
    
    public addBeer(EbeanServer server, User user) {
        super(server, user);
    }

    @Override
    public void run() {
        System.out.print("to which brewery: ");
        String name = scanner.nextLine();
        Brewery brewery = server.find(Brewery.class).where().like("name", name).findUnique();

        if (brewery == null) {
            System.out.println(name + " does not exist");
            return;
        }

        System.out.print("beer to add: ");

        name = scanner.nextLine();

        Beer exists = server.find(Beer.class).where().like("name", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        brewery.addBeer(new Beer(name));
        server.save(brewery);
        System.out.println(name + " added to " + brewery.getName());
    }
    
}
