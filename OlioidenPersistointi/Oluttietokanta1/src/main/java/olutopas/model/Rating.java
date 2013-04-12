package olutopas.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author laursuom
 */

@Entity
public class Rating {

    int rating;
    
    @ManyToOne
    private Beer beer;
    
    @Id
    private Integer id;
    
   @ManyToOne
    private User user;

    public Rating() {
    }

    public Rating(int rating) {
        this.rating = rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public Beer getBeer() {
        return beer;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Rating{" + "rating=" + rating + ", beer=" + beer + ", id=" + id + ", user=" + user + '}';
    }
}
