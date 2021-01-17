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
public class PersonneTest {

    Exposition e;
    Personne p;
    Transaction tr;
    Transaction tr2;

    @BeforeEach
    public void setUp() {
        e = new Exposition();
        p = new Personne("Poulat");
        tr = new Transaction(e, p);
        tr2 = new Transaction(e, p);

        tr.setVenduLe(LocalDate.of(2020, Month.MARCH, 2));
        tr.setPrixVente(1000);
        tr2.setPrixVente(2000);
        p.getAchats().add(tr);
        p.getAchats().add(tr2);

    }

    @Test
    public void testGalerieCAannuel() {
        tr2.setVenduLe(LocalDate.of(2020, Month.MARCH, 2));
        Assertions.assertEquals(3000, p.budgetArtPour(2020));
    }

    @Test
    public void testGalerieCAannuel2021() {
        tr2.setVenduLe(LocalDate.of(2021, Month.MARCH, 2));
        Assertions.assertEquals(2000, p.budgetArtPour(2021));
    }
    
    @Test
    public void testGalerieCAannuel2019() {
        tr2.setVenduLe(LocalDate.of(2021, Month.MARCH, 2));
        Assertions.assertEquals(0, p.budgetArtPour(2019));
    }
}
