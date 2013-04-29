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
public class DBHandler2 implements DBHandler {

    EbeanServer server;

    public DBHandler2(EbeanServer server) {
        this.server = server;
    }

    @Override
    public EbeanServer getServer() {
        return server;
    }

    /*
     * BEER OPERATIONS
     */
    @Override
    public Beer findBeer(String name) {
        return server.find(Beer.class).where().like("name", name).findUnique();
    }

    @Override
    public void saveBeer(Beer b) {
        server.save(b);
    }

    @Override
    public void deleteBeer(Beer beerToDelete) {
        server.delete(beerToDelete);
    }

    @Override
    public List<Beer> listBeers() {
        return server.find(Beer.class).orderBy("brewery.name").findList();
    }


    /*
     * BREWERY OPERATIONS
     */
    @Override
    public Brewery findBrewery(String name) {
        return server.find(Brewery.class).where().like("name", name).findUnique();
    }

    @Override
    public void saveBrewery(Brewery brewery) {
        server.save(brewery);
    }

    @Override
    public void deleteBrewery(Brewery brewery) {
        server.delete(brewery);
    }

    @Override
    public List<Brewery> listBreweries() {
        return server.find(Brewery.class).findList();
    }

    /*
     * RATING OPERATIONS
     */
    @Override
    public void saveRating(Rating rating) {
        server.save(rating);
    }

    /*
     * USER OPERATIONS
     */
    @Override
    public User findUser(String name) {
        return server.find(User.class).where().like("name", name).findUnique();
    }

    @Override
    public void saveUser(User user) {
        server.save(user);
    }

    @Override
    public List<User> listUsers() {
        return server.find(User.class).findList();
    }
}
