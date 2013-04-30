package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstatistics.herokuapp.com/players.txt"));
          
//        Matcher m = new And( new HasAtLeast(10, "goals"),
//                             new HasAtLeast(10, "assists"),
//                             new PlaysIn("PHI")
        QueryBuilder query = new QueryBuilder();
        Matcher m = query.OneOf(query.HasAtLeast(10, "goals").HasAtLeast(10, "assists").PlaysIn("PHI"), query.PlaysIn("ANA")).build();
        
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }
    }
}
