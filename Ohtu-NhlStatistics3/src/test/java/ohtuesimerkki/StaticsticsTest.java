package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StaticsticsTest {

    Statistics stats;
    PlayerReader reader = mock(PlayerReader.class); 
    ArrayList<Player> lista;


    @Before
    public void setUp() {
            lista = new ArrayList<Player>();
            lista.add(new Player("Semenko", "EDM", 4, 12));
            lista.add(new Player("Lemieux", "PIT", 45, 54));
            lista.add(new Player("Kurri", "EDM", 37, 53));
            lista.add(new Player("Yzerman", "DET", 42, 56));
            lista.add(new Player("Gretzky", "EDM", 35, 89));
            when(reader.getPlayers()).thenReturn(lista);
            stats = new Statistics(reader);
        
        
    }

    @Test
    public void playerFound() {
        
        Player p = stats.search("Lemieux");
        assertEquals("PIT", p.getTeam());
        assertEquals(45, p.getGoals());
        assertEquals(54, p.getAssists());
        assertEquals(45 + 54, p.getPoints());

    }
    
    @Test
    public void teamMembersFound(){
        when(reader.getPlayers()).thenReturn(lista);
        List<Player> players = stats.team("EDM");
        assertEquals(3, players.size());
        for (Player player : players) {
            assertEquals("EDM", player.getTeam());
        }
    }
    
    @Test
    public void topScorersFound(){
        when(reader.getPlayers()).thenReturn(lista);
        List<Player> players = stats.topScorers(2);
        assertEquals(3, players.size());
        assertEquals("Gretzky", players.get(0).getName());
        assertEquals("Lemieux", players.get(1).getName());
    }
}
