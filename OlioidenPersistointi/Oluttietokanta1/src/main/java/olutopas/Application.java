package olutopas;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import java.util.Scanner;
import javax.persistence.OptimisticLockException;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.Rating;
import olutopas.model.User;

public class Application {

    private EbeanServer server;
    private Scanner scanner = new Scanner(System.in);

    public Application(EbeanServer server) {
        this.server = server;
    }

    public void run(boolean newDatabase) {
        if (newDatabase) {
            seedDatabase();
        }
        String userName = null;
        
        while (true) {
            System.out.println("Login (give ? to register a new user)");
            String n = scanner.nextLine();
            if (n.equals("?")) {
                addUser();
                 }
            else if (n.isEmpty()) {
                System.exit(0);
            } else {
                if(logIn(n)) {
                    userName = n;
                    break;
                }
            }
        }

        System.out.println("Welcome!");

        while (true) {
            menu();
            System.out.print("> ");
            String command = scanner.nextLine();

            if (command.equals("0")) {
                break;
            } else if (command.equals("1")) {
                findBrewery();
            } else if (command.equals("2")) {
                findBeer();
            } else if (command.equals("3")) {
                addBeer();
            } else if (command.equals("4")) {
                listBreweries();
            } else if (command.equals("5")) {
                deleteBeer();
            } else if (command.equals("6")) {
                listBeers();
            } else if (command.equals("7")) {
                addBrewery();
            } else if (command.equals("8")) {
                deleteBrewery();
            } else if (command.equals("9")) {
                listUsers();
            } else if (command.equals("10")) {
                rateBeer(userName);
            } else if (command.equals("listr")) {
                listRatedBeers(userName);
            } else if (command.equals("avg")) {
                getAverageRating(userName);
            }
            else {
                System.out.println("unknown command");
            }

            System.out.print("\npress enter to continue");
            scanner.nextLine();
        }

        System.out.println("bye");
    }

    private void menu() {
        System.out.println("");
        System.out.println("1   find brewery");
        System.out.println("2   find beer");
        System.out.println("3   add beer");
        System.out.println("4   list breweries");
        System.out.println("5   delete beer");
        System.out.println("6   list beers");
        System.out.println("7   add brewery");
        System.out.println("8   delete brewery");
        System.out.println("9   list jyysers");
        System.out.println("10  Rate a beer");
        System.out.println("listr  List rated beers");
        System.out.println("avg     get average beer rates");
        System.out.println("0   quit");
        System.out.println("");
    }

    // jos kanta on luotu uudelleen, suoritetaan tämä ja laitetaan kantaan hiukan dataa
    private void seedDatabase() throws OptimisticLockException {
        Brewery brewery = new Brewery("Schlenkerla");
        brewery.addBeer(new Beer("Urbock"));
        brewery.addBeer(new Beer("Lager"));
        // tallettaa myös luodut oluet, sillä Brewery:n OneToMany-mappingiin on määritelty
        // CascadeType.all
        server.save(brewery);

        // luodaan olut ilman panimon asettamista
        Beer b = new Beer("Märzen");
        server.save(b);
        
        // jotta saamme panimon asetettua, tulee olot lukea uudelleen kannasta
        b = server.find(Beer.class, b.getId());        
        brewery = server.find(Brewery.class, brewery.getId());        
        brewery.addBeer(b);
        server.save(brewery);
        
        server.save(new Brewery("Paulaner"));
    }

    private void addUser() {
        System.out.println("Give username");
               String n = scanner.nextLine();
                User user = server.find(User.class).where().like("name", n).findUnique();

        if (!(user == null)) {
            System.out.println(n + "already exists!");
            return;
        }
        
        server.save(new User(n));
        System.out.println(n +  " registered!");
    
    }
    
    private void listUsers() {
        List<User> drunks = server.find(User.class).findList();
        for (User user : drunks) {
            System.out.println(user);
        }
    }
    private void findBeer() {
        System.out.print("beer to find: ");
        String n = scanner.nextLine();
        Beer foundBeer = server.find(Beer.class).where().like("name", n).findUnique();

        if (foundBeer == null) {
            System.out.println(n + " not found");
            
        }

        System.out.println("found: " + foundBeer);
        
    }
    private boolean findBeer(String n) {
        System.out.print("beer to find: ");
        Beer foundBeer = server.find(Beer.class).where().like("name", n).findUnique();

        if (foundBeer == null) {
            System.out.println(n + " not found");
            return false;
        }

        System.out.println("found: " + foundBeer);
        return true;
    }

    private void findBrewery() {
        System.out.print("brewery to find: ");
        String n = scanner.nextLine();
        Brewery foundBrewery = server.find(Brewery.class).where().like("name", n).findUnique();

        if (foundBrewery == null) {
            System.out.println(n + " not found");
            return;
        }

        System.out.println(foundBrewery);
        for (Beer bier : foundBrewery.getBeers()) {
            System.out.println("   " + bier.getName());
        }
    }

    private void listBreweries() {
        List<Brewery> breweries = server.find(Brewery.class).findList();
        for (Brewery brewery : breweries) {
            System.out.println(brewery);
        }
    }

    private void addBeer() {
        System.out.print("to which brewery: ");
        String name = scanner.nextLine();
        Brewery brewery = server.find(Brewery.class).where().like("name", name).findUnique();

        if (brewery == null) {
            System.out.println(name + " does not exist");
            return;
        }

        System.out.print("beer to add: ");

        name = scanner.nextLine();

        Beer exists = server.find(Beer.class).where().like("name", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        brewery.addBeer(new Beer(name));
        server.save(brewery);
        System.out.println(name + " added to " + brewery.getName());
    }

    private void deleteBeer() {
        System.out.print("beer to delete: ");
        String n = scanner.nextLine();
        Beer beerToDelete = server.find(Beer.class).where().like("name", n).findUnique();

        if (beerToDelete == null) {
            System.out.println(n + " not found");
            return;
        }

        server.delete(beerToDelete);
        System.out.println("deleted: " + beerToDelete);

    }
    private void listBeers() {
         List<Beer> beers = server.find(Beer.class).findList();
        for (Beer beer : beers) {
            System.out.println(beer);
        }
    }
    private void addBrewery() {
    System.out.print("Give the name of the brewery:");
        String name = scanner.nextLine();
       
        Brewery exists = server.find(Brewery.class).where().like("name", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }
        server.save(new Brewery(name));
        System.out.println(name + " added!");
    }
    
    private void deleteBrewery() {
        System.out.print("Brewery to delete: ");
        String n = scanner.nextLine();
        Brewery breweryToDelete = server.find(Brewery.class).where().like("name", n).findUnique();

        if (breweryToDelete == null) {
            System.out.println(n + " not found");
            return;
        }

        server.delete(breweryToDelete);
        System.out.println("deleted: " + breweryToDelete);

    }

    private boolean logIn(String n) {
        User user = server.find(User.class).where().like("name", n).findUnique();

        if (!(user == null)) {
            return true;
        }
        return false;
    }

    private void rateBeer(String user) {
        System.out.println("Give beer name");
        String name = scanner.nextLine();
        if (!(findBeer(name))) {
            return;
        } else {
             Beer foundBeer = server.find(Beer.class).where().like("name", name).findUnique();
             System.out.println("Give a rating");
             Integer rating = scanner.nextInt();
             Rating rate = new Rating(rating);
             rate.setUser(server.find(User.class).where().like("name", user).findUnique());
             rate.setBeer(foundBeer);
             foundBeer.getRatings().add(rate);
             server.save(foundBeer.getRatings());
        }
        
    }

    private void listRatedBeers(String user) {
        User user2 = server.find(User.class).where().like("name", user).findUnique();
        List<Rating> rates  = server.find(Rating.class).where().like("Id", user2.getId().toString()).findList();
        for (Rating rate: rates) {
            System.out.println(rate);
        }
    }
    private void getAverageRating(String user) {
        System.out.println("Give beer name");
        String name = scanner.nextLine();
        if (!(findBeer(name))) {
        } else {
            Beer beer = server.find(Beer.class).where().like("name", name).findUnique();
            List<Rating> rates  = beer.getRatings();
            int sum = 0;
            if (!rates.isEmpty()) {
            for (Rating rating : rates) {
                sum = sum + rating.getRating(); 
            }
            System.out.println("Average rating for: " + name + ": " + sum/rates.size());
        }
        }
    }
    }
    

