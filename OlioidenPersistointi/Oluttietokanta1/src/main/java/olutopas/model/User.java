/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author laursuom
 */
@Entity
public class User {

    @Override
    public String toString() {
        return getName();
    }

    @Id
    private Integer id;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Rating> ratings;
    
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void setName(String name) {
        this.name = name;
    }
}
