package olutopas;

import com.avaje.ebean.EbeanServer;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import javax.persistence.OptimisticLockException;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.Rating;
import olutopas.model.User;
import Commands.*;

public class Application {

    private EbeanServer server;
    private Scanner scanner = new Scanner(System.in);
    private User user;
    private HashMap<String, Command> commands;

    public Application(EbeanServer server) {
        this.server = server;
        commands = new HashMap();


    }

    public void run(boolean newDatabase) {
        if (newDatabase) {
            seedDatabase();
        }

        login();

        System.out.println("\nWelcome to Ratebeer " + user.getName());
        commands.put("1", new findBrewery(server, user));
        commands.put("2", new findBeer(server, user));
        commands.put("3", new addBeer(server, user));
        commands.put("4", new listBreweries(server, user));
        commands.put("5", new deleteBeer(server, user));
        commands.put("6", new listBeers(server, user));
        commands.put("7", new deleteBrewery(server, user));
        commands.put("8", new addBrewery(server, user));
        commands.put("9", new myRatings(server, user));
        commands.put("0", new listUsers(server, user));

        while (true) {
            menu();
            System.out.print("> ");
            String command = scanner.nextLine();

            if (command.equals("q")) {
                break;
            } else if (commands.containsKey(command)) {
                commands.get(command).run();
            } else {
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
        System.out.println("2   find/rate beer");
        System.out.println("3   add beer");
        System.out.println("4   list breweries");
        System.out.println("5   delete beer");
        //
        System.out.println("6   list beers");
        System.out.println("7   delete brewery");
        System.out.println("8   add brewery");
        //

        //
        System.out.println("9   show my ratings");
        System.out.println("0   list users");
        //
        System.out.println("q   quit");
        System.out.println("");
    }

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

        server.save(new User("mluukkai"));
    }

    private void login() {
        while (true) {
            System.out.println("\nLogin (give ? to register a new user)\n");

            System.out.print("username: ");
            String name = scanner.nextLine();

            if (name.equals("?")) {
                registerUser();
                continue;
            }

            user = server.find(User.class).where().like("name", name).findUnique();

            if (user != null) {
                break;
            }
            System.out.println("unknown user");
        }
    }

    private void registerUser() {
        System.out.println("Register a new user");
        System.out.print("give username: ");
        String name = scanner.nextLine();
        User u = server.find(User.class).where().like("name", name).findUnique();
        if (u != null) {
            System.out.println("user already exists!");
            return;
        }
        server.save(new User(name));
        System.out.println("user created!\n");
    }
}
