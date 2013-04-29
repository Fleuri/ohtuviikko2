/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Tietokantaoperaatiot.DBHandler;
import Tietokantaoperaatiot.DBHandler2;
import com.avaje.ebean.EbeanServer;
import olutopas.model.Beer;
import olutopas.model.Rating;
import olutopas.model.User;

/**
 *
 * @author Lauri
 */
public class findBeer extends Command {

    public findBeer(DBHandler server, User user) {
        super(server, user);
    }

    @Override
    public void run() {
        System.out.print("beer to find: ");
        String n = scanner.nextLine();
        Beer foundBeer = server.findBeer(n);

        if (foundBeer == null) {
            System.out.println(n + " not found");
            return;
        }

        System.out.println(foundBeer);

        if (foundBeer.getRatings() != null && !foundBeer.getRatings().isEmpty()) {
            System.out.println("  number of ratings: " + foundBeer.getRatings().size() + " average " + foundBeer.averageRating());
        } else {
            System.out.println("no ratings");
        }

        System.out.print("give rating (leave emtpy if not): ");
        try {
            int rating = Integer.parseInt(scanner.nextLine());
            addRating(foundBeer, rating);
        } catch (Exception e) {
        }
    }

    private void addRating(Beer foundBeer, int value) {
        Rating rating = new Rating(foundBeer, user, value);
        server.saveRating(rating);
    }
}
