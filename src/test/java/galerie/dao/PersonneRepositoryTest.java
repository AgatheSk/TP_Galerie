/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galerie.dao;

import galerie.entity.Artiste;
import galerie.entity.Personne;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

/**
 *
 * @author agath
 */
@DataJpaTest
@Log4j2 // Génère le 'logger' pour afficher les messages de trace
public class PersonneRepositoryTest {

    @Autowired
    private PersonneRepository persoDAO;

    @Test
    @Sql("test-data.sql")
    void compterLesEntites() {
        log.info("Compter les personnes");
        long nombre = persoDAO.count(); // 'count' donne le nombre d'enregistrements
        assertEquals(2, nombre, "Le jeu de test contient 2 personnes");
    }

    @Test
    @Sql("test-data.sql")
    void listerLesEntites() {
        log.info("Lister les personnes");

        List<Personne> liste = persoDAO.findAll(); // Renvoie la liste des entités dans la table

        log.info("Liste des personnes: {}", liste);
    }

    @Test
    @Sql("test-data.sql")
    void touverParCle() {
        log.info("Trouver une entité par sa clé");

        int codePresent = 1;
        Optional<Personne> resultat = persoDAO.findById(codePresent);
        // On s'assure qu'on trouvé le résultat
        assertTrue(resultat.isPresent(), "Cette personne existe");
        Personne p = resultat.get();
        assertEquals("paul", p.getNom());

        log.info("Personne trouvée: {}", p);
    }

    @Test
    void entiteInconnue() {
        log.info("Chercher une entité inconnue");
        int codeInconnu = 99;

        Optional<Personne> resultat = persoDAO.findById(codeInconnu);

        assertFalse(resultat.isPresent(), "Cette personne n'existe pas");

    }

    @Test
    void creerUneEntite() {
        log.info("Créer une entité");
        Personne nouvelle = new Personne("pnj");
        assertNull(nouvelle.getIdPersonne(), "L'entité n'a pas encore de clé");
        persoDAO.save(nouvelle);
        Integer nouvellecle = nouvelle.getIdPersonne(); // La clé a été auto-générée lors de l'enregistrement
        assertNotNull(nouvellecle, "Une nouvelle clé doit avoir été générée");
        log.info("Nouvelle entité: {}", nouvelle);
    }

    @Test
    @Sql("test-data.sql")
    void onNePeutPasDetruireUnArtisteQuiADesOeuvres() {
        log.info("Détruire un artiste (personne) avec des oeuvres");
        Personne peintre = persoDAO.getOne(2);
        assertEquals("Picassou", peintre.getNom());
        // Si on essaie de détruire cet artiste, on doit avoir une exception
        // de violation de contrainte d'intégrité
        assertThrows(DataIntegrityViolationException.class, () -> {
            persoDAO.delete(peintre);
            persoDAO.flush(); // Pour forcer la validation de la transaction
        });
    }

}
