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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author agath
 */
public class ExpositionTest {
    Exposition e;
    Personne p;
    Transaction tr;
    Transaction tr2;
    
    @BeforeEach
    public void setUp() {
        e = new Exposition();
        p=new Personne("Poulat");
        tr=new Transaction(e, p);
        tr2 = new Transaction(e, p);
        
        tr.setPrixVente(1000);
        tr2.setPrixVente(2000);
        
        e.getVentes().add(tr);
        e.getVentes().add(tr2);
        
    }
    
    @Test
    public void testCAPour(){
        Assertions.assertEquals(3000, e.chiffreAffairePour());
    }
    
    @Test
    public void testCAPourVide(){
        Exposition eVide =new Exposition();
        Assertions.assertEquals(0, eVide.chiffreAffairePour());
    }
    
}
