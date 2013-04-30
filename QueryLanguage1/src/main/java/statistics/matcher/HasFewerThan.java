/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import statistics.Player;

/**
 *
 * @author Lauri
 */
public class HasFewerThan implements Matcher {
    
    HasAtLeast least;
    Matcher matcher;
    
    public HasFewerThan(int value, String category) {
        least = new HasAtLeast(value, category);
    }
    
    public HasFewerThan(int value, String category, Matcher matcher) {
        least = new HasAtLeast(value, category);
        this.matcher = matcher;
    }

    @Override
    public boolean matches(Player p) {
        if (matcher == null) {
        return (!least.matches(p));
        } else {
            return (!least.matches(p) && matcher.matches(p));
        }
    }
    
}
