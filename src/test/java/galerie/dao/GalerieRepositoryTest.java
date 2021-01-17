package galerie.dao;

import galerie.entity.Galerie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.test.context.jdbc.Sql;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;

@Log4j2 // Génère le 'logger' pour afficher les messages de trace
@DataJpaTest
public class GalerieRepositoryTest {

    @Autowired
    private GalerieRepository galerieDAO;

    @Test
    @Sql("test-data.sql") // On peut charger des donnnées spécifiques pour un test
    public void onSaitCompterLesEnregistrements() {
        log.info("On compte les enregistrements de la table 'Galerie'");
        int combienDansLeJeuDeTest = 1;
        long nombre = galerieDAO.count();
        assertEquals(combienDansLeJeuDeTest, nombre, "On doit trouver 1 galerie");
    }

    @Test
    void listerLesEntites() {
        log.info("Lister les entités");

        List<Galerie> liste = galerieDAO.findAll(); // Renvoie la liste des entités dans la table

        log.info("Liste des entités: {}", liste);
    }

    @Test
    @Sql("test-data.sql") // On peut charger des donnnées spécifiques pour un test
    void touverParCle() {
        log.info("Trouver une entité par sa clé");

        int codePresent = 1;
        Optional<Galerie> resultat = galerieDAO.findById(codePresent);
        // On s'assure qu'on trouvé le résultat
        assertTrue(resultat.isPresent(), "Cette galerie existe");
        Galerie g = resultat.get();
        assertEquals("Saatchi", g.getNom());

        log.info("Entité trouvée: {}", g);
    }

    @Test
    void creerUneEntite() {
        log.info("Créer une entité");
        Galerie nouvelle = new Galerie("maGalerie", "5 rue du poisson pas né");
        assertNull(nouvelle.getId(), "L'entité n'a pas encore de clé");
        galerieDAO.save(nouvelle); // 'save' enregistre l'entite dans la base
        Integer nouvellecle = nouvelle.getId(); // La clé a été auto-générée lors de l'enregistrement
        assertNotNull(nouvellecle, "Une nouvelle clé doit avoir été générée");
        log.info("Nouvelle entité: {}", nouvelle);
    }

    @Test
    @Sql("test-data.sql")
    void erreurCreationEntite() {
        log.info("Créer une entité avec erreur");
        //Une galerie a déjà ce nom qui est unique
        Galerie nouvelle = new Galerie("Saatchi", "5 rue du poisson pas né");
        try { // L'enregistreement peut générer des exceptions (ex : violation de contrainte d'intégrité)
            galerieDAO.save(nouvelle);
            fail("Une exposition possède déjà cet identifiant, on doit avoir une exception");
        } catch (DataIntegrityViolationException e) {
            // Si on arrive ici c'est normal, on a eu l'exception attendue
        }
    }

}
