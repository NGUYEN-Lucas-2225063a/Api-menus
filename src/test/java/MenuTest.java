import fr.univamu.iut.apimenus.Menu;
import fr.univamu.iut.apimenus.Plat;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTest {

    @Test
    public void testGettersAndSetters() {
        // Création de plats fictifs
        Plat plat1 = new Plat("Plat 1", "Description plat 1", 10.99, "John Doe");
        Plat plat2 = new Plat("Plat 2", "Description plat 2", 12.99, "Jane Smith");

        // Création d'une liste de plats
        List<Plat> plats = new ArrayList<>();
        plats.add(plat1);
        plats.add(plat2);

        // Création d'un menu
        Menu menu = new Menu(1, "Menu du jour", plats, "John Doe", "2024-04-05");

        // Vérification des getters
        assertEquals(1, menu.getMenuId());
        assertEquals("Menu du jour", menu.getName());
        assertEquals(plats, menu.getPlats());
        assertEquals("John Doe", menu.getCreatorName());
        assertEquals("2024-04-05", menu.getCreationDate());

        // Modification des valeurs avec les setters
        menu.setMenuId(2);
        menu.setName("Menu de la semaine");
        List<Plat> newPlats = new ArrayList<>();
        menu.setPlats(newPlats);
        menu.setCreatorName("Jane Smith");
        menu.setCreationDate("2024-04-10");

        // Vérification des valeurs modifiées
        assertEquals(2, menu.getMenuId());
        assertEquals("Menu de la semaine", menu.getName());
        assertEquals(newPlats, menu.getPlats());
        assertEquals("Jane Smith", menu.getCreatorName());
        assertEquals("2024-04-10", menu.getCreationDate());
    }
}
