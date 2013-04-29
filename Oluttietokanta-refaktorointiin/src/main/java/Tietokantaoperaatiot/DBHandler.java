/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietokantaoperaatiot;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.Rating;
import olutopas.model.User;

/**
 *
 * @author Lauri
 */
public interface DBHandler {

    void deleteBeer(Beer beerToDelete);

    void deleteBrewery(Brewery brewery);

    /*
     * BEER OPERATIONS
     */
    Beer findBeer(String name);

    /*
     * BREWERY OPERATIONS
     */
    Brewery findBrewery(String name);

    /*
     * USER OPERATIONS
     */
    User findUser(String name);

    EbeanServer getServer();

    List<Beer> listBeers();

    List<Brewery> listBreweries();

    List<User> listUsers();

    void saveBeer(Beer b);

    void saveBrewery(Brewery brewery);

    /*
     * RATING OPERATIONS
     */
    void saveRating(Rating rating);

    void saveUser(User user);
    
}
