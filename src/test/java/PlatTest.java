import fr.univamu.iut.apimenus.Plat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlatTest {

    @Test
    public void testGettersAndSetters() {
        // Création d'un plat
        Plat plat = new Plat(1, "Plat 1", "Description du plat 1", 10.99, "John Doe");

        // Vérification des getters
        assertEquals(1, plat.getId());
        assertEquals("Plat 1", plat.getNom());
        assertEquals("Description du plat 1", plat.getDescription());
        assertEquals(10.99, plat.getPrix());
        assertEquals("John Doe", plat.getCreateurNom());

        // Modification des valeurs avec les setters
        plat.setId(2);
        plat.setNom("Plat 2");
        plat.setDescription("Description du plat 2");
        plat.setPrix(12.99);
        plat.setCreateurNom("Jane Smith");

        // Vérification des valeurs modifiées
        assertEquals(2, plat.getId());
        assertEquals("Plat 2", plat.getNom());
        assertEquals("Description du plat 2", plat.getDescription());
        assertEquals(12.99, plat.getPrix());
        assertEquals("Jane Smith", plat.getCreateurNom());
    }
}
