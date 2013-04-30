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
public class QueryBuilder {

    private Matcher matcher;

    private QueryBuilder(Matcher matcher) {
        this.matcher = matcher;
    }

    public QueryBuilder() {
    }

    ;
    
    public QueryBuilder HasAtLeast(int number, String category) {
        return new QueryBuilder(new HasAtLeast(number, category, matcher));
    }

    public QueryBuilder HasFewerThan(int number, String category) {
        return new QueryBuilder(new HasFewerThan(number, category, matcher));
    }

    public QueryBuilder PlaysIn(String team) {
        return new QueryBuilder(new PlaysIn(team, matcher));
    }

    public QueryBuilder OneOf(QueryBuilder a, QueryBuilder b) {
        return new QueryBuilder(new Or(a.build(), b.build()));
    }

    public Matcher build() {
        return matcher;
    }
}
