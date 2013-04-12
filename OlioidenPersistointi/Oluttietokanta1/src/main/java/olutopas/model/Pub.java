/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
/**
 *
 * @author laursuom
 */
public class Pub {
    
     @Id
    private Integer id;
    
    private String name;
    
    @ManyToMany
    private List<Beer> beers;

    @Override
    public String toString() {
        return "Pub{" + "id=" + id + ", name=" + name + ", beers=" + beers + '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeers(List<Beer> beers) {
        this.beers = beers;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Beer> getBeers() {
        return beers;
    }

    public Pub() {
    }
    
   
}
