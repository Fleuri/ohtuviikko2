package olutopas.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Beer {

  

    private String name;
    
    @ManyToMany
    private List<Pub> pubs;
    
    @Id
    private Integer id;
    
    @ManyToOne
    private Brewery brewery;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Rating> ratings;

    public List<Rating> getRatings() {
        return ratings;
    }
    
      public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public Beer() {
    }
    
    public Beer(String name) {
        this.name = name;
    }        
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Brewery getBrewery() {
        return brewery;
    }

    public void setBrewery(Brewery brewery) {
        this.brewery = brewery;
    }

    @Override
    public String toString() {
        // olioiden kannattaa sisäisestikin käyttää gettereitä oliomuuttujien sijaan
        // näin esim. olueeseen liittyvä panimo tulee varmasti ladattua kannasta
        return getName() + " ("+getBrewery().getName()+")";
    }     

  
}
