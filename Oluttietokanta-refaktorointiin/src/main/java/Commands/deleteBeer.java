/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Tietokantaoperaatiot.DBHandler;
import Tietokantaoperaatiot.DBHandler2;

import olutopas.model.Beer;
import olutopas.model.User;

/**
 *
 * @author Lauri
 */
public class deleteBeer extends Command{

    public deleteBeer (DBHandler server, User user) {
        super(server, user);
    }
    
    @Override
    public void run() {
       System.out.print("beer to delete: ");
        String n = scanner.nextLine();
        Beer beerToDelete = server.findBeer(n); 

        if (beerToDelete == null) {
            System.out.println(n + " not found");
            return;
        }

        server.deleteBeer(beerToDelete);
        System.out.println("deleted: " + beerToDelete);

    } }
    

