/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galerie.entity;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author agath
 */
public class GalerieTest {

    Galerie g;
    Exposition e;
    Personne p;
    Transaction tr;
    Transaction tr2;

    @BeforeEach
    public void setUp() {
        e = new Exposition();
        g = new Galerie("Galeria", "12 rue du poulet");
        p = new Personne("Poulat");
        e.setOrganisateur(g);
        tr = new Transaction(e, p);
        tr2 = new Transaction(e, p);
        
        tr.setVenduLe(LocalDate.of(2020, Month.MARCH, 2));
        tr.setPrixVente(1000);
        tr2.setPrixVente(2000);
        
        e.getVentes().add(tr);
        e.getVentes().add(tr2);
        g.getEvenements().add(e);

    }

    @Test
    public void testGalerieCAannuel2020() {
        tr2.setVenduLe(LocalDate.of(2020, Month.MARCH, 2));
        Assertions.assertEquals(3000, g.chiffreAffaireAnnuelPour(2020));
    }

    @Test
    public void testGalerieCAannuel2021() {
        tr2.setVenduLe(LocalDate.of(2021, Month.MARCH, 2));
        Assertions.assertEquals(2000, g.chiffreAffaireAnnuelPour(2021));
    }

    @Test
    public void testGalerieCAannuel2019() {
        tr2.setVenduLe(LocalDate.of(2020, Month.MARCH, 2));
        Assertions.assertEquals(0, g.chiffreAffaireAnnuelPour(2019));
    }
}
