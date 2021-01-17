/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galerie.dao;

import galerie.entity.Exposition;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

/**
 *
 * @author agath
 */
@Log4j2 // Génère le 'logger' pour afficher les messages de trace  
@DataJpaTest
public class ExpositionRepositoryTest {

    @Autowired
    private ExpositionRepository expoDAO;

    @Test
    @Sql("test-data.sql") // On peut charger des donnnées spécifiques pour un test
    public void onSaitCompterLesEnregistrements() {
        log.info("On compte les enregistrements de la table 'Exposition'");
        int combienDansLeJeuDeTest = 1;
        long nombre = expoDAO.count();
        assertEquals(combienDansLeJeuDeTest, nombre, "On doit trouver 1 exposition");
    }

    @Test
    void listerLesEntites() {
        log.info("Lister les entités");

        List<Exposition> liste = expoDAO.findAll(); // Renvoie la liste des entités dans la table

        log.info("Liste des entités: {}", liste);
    }

    @Test
    @Sql("test-data.sql")      
    void touverParCle() {
        log.info("Trouver une entité par sa clé");

        int codePresent = 1;
        Optional<Exposition> resultat = expoDAO.findById(codePresent);
        // On s'assure qu'on trouvé le résultat
        assertTrue(resultat.isPresent(), "Cette exposition existe");
        Exposition e = resultat.get();
        assertEquals("Saatchi", e.getOrganisateur().getNom());

        log.info("Entité trouvée: {}", e);
    }

    @Test
    @Sql("test-data.sql")
    void entiteInconnue() {
        log.info("Chercher une entité inconnue");
        int codeInconnu = 99;

        Optional<Exposition> resultat = expoDAO.findById(codeInconnu);

        assertFalse(resultat.isPresent(), "Cette exposition n'existe pas");

    }

    @Test
    void creerUneEntite() {
        log.info("Créer une entité");
        Exposition nouvelle = new Exposition();
        nouvelle.setDebut(LocalDate.of(2020, Month.MARCH, 1));
        nouvelle.setDuree(3);
        assertNull(nouvelle.getIdExpo(), "L'entité n'a pas encore de clé");
        expoDAO.save(nouvelle); // 'save' enregistre l'entite dans la base
        Integer nouvellecle = nouvelle.getIdExpo(); // La clé a été auto-générée lors de l'enregistrement
        assertNotNull(nouvellecle, "Une nouvelle clé doit avoir été générée");
        log.info("Nouvelle entité: {}", nouvelle);
    }

    @Test
    @Sql("test-data.sql")
    void destructionExpoAvecOrganisateur() {
        log.info("Détruire une expositon avec un organisateur");
        assertFalse(expoDAO.getOne(1).getOrganisateur().getId() == null);
        // Si on essaie de détruire cette exposition, on doit avoir une exception
        // de violation de contrainte d'intégrité
        assertThrows(DataIntegrityViolationException.class, () -> {
            expoDAO.delete(expoDAO.getOne(1));
            expoDAO.flush(); // Pour forcer la validation de la transaction
        });
    }
    
    @Test
    @Sql("test-data.sql")
    void testCAPour(){
        assertEquals(300, expoDAO.chiffreAffairePour(1));
    }
    
    @Test
    void testCAPourVide(){
        try{
            //la clé 1 n'exsite pas
            expoDAO.chiffreAffairePour(1);
            fail();
        }catch (Exception e){
            //on a réussi
        }
    }
}
