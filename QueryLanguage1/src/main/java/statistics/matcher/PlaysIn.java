package statistics.matcher;

import statistics.Player;

public class PlaysIn implements Matcher {

    private String team;
    private Matcher matcher;

    public PlaysIn(String team) {
        this.team = team;
    }

    public PlaysIn(String team, Matcher matcher) {
        this.team = team;
        this.matcher = matcher;
    }

    @Override
    public boolean matches(Player p) {
        if (matcher == null) {
            return (p.getTeam().contains(team));
        } else {
            return (p.getTeam().contains(team) && matcher.matches(p));
        }
    }
}
