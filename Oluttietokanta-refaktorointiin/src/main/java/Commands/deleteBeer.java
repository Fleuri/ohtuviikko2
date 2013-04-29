/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import com.avaje.ebean.EbeanServer;
import olutopas.model.Beer;
import olutopas.model.User;

/**
 *
 * @author Lauri
 */
public class deleteBeer extends Command{

    public deleteBeer (EbeanServer server, User user) {
        super(server, user);
    }
    
    @Override
    public void run() {
       System.out.print("beer to delete: ");
        String n = scanner.nextLine();
        Beer beerToDelete = server.find(Beer.class).where().like("name", n).findUnique();

        if (beerToDelete == null) {
            System.out.println(n + " not found");
            return;
        }

        server.delete(beerToDelete);
        System.out.println("deleted: " + beerToDelete);

    } }
    

