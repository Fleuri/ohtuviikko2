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
public class deleteBrewery extends Command{

    public deleteBrewery (DBHandler server, User user) {
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

        server.deleteBrewery(brewery);

        System.out.println("deleted: " + name);}
    
}
