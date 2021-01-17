/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galerie.entity;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author agath
 */
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Personne {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersonne;
    
    @NonNull
    private String nom;
    
    private String adresse;
    
    @OneToMany (mappedBy="client")
    private List<Transaction> achats = new LinkedList<>();
    
    /**
    * Calculer le budget d'art selon l'annee pour une personne
    * @param annee l'annee souhaite
    * @return le budget d'art d'une personne
    */
    float budgetArtPour(int annee){
        float budget =0;
        for (Transaction a : achats){
            if(a.getVenduLe().getYear()==annee)
                budget += a.getPrixVente();
        }
        return budget;
    }
    
}
