/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galerie.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author agath
 */
@Getter @Setter
//@NoArgsConstructor
@RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class Exposition {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExpo;
    
    private LocalDate debut;
    
    private int duree;

    @ManyToOne
    private Galerie organisateur;
   
    @OneToMany (mappedBy = "lieuDeVente")
    private List<Transaction> ventes = new ArrayList<Transaction>();
    
    @ManyToMany
    @JoinTable(name="expo_tableau",
        joinColumns = 
                @JoinColumn(name = "exposition_id", referencedColumnName="idExpo"),
        inverseJoinColumns = 
                @JoinColumn(name = "tableau_id",  referencedColumnName="idTablo")
    )            
    List<Tableau> oeuvres = new LinkedList<>();
   
    /**
    * Calculer le chiffre d'affaires pour une exposition
    * @return le chiffre d'affaires de cette exposition
    */
    float chiffreAffairePour(){
        float ca=0;
        for(Transaction t : ventes){
            ca+=t.getPrixVente();
        }
        return ca; 
    }
    
}
