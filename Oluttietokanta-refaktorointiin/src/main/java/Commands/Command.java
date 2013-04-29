/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import com.avaje.ebean.EbeanServer;
import java.util.Scanner;
import olutopas.model.User;

/**
 *
 * @author Lauri
 */
public abstract class Command {
    Scanner scanner;
    EbeanServer server;
    User user;
    
    public Command(EbeanServer server, User user) {
     scanner = new Scanner(System.in);
     this.server = server;
     this.user = user;
     
}
    
  public abstract void run();
}
