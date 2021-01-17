/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galerie.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@NoArgsConstructor
//@RequiredArgsConstructor 
@ToString
@Entity // Une entité JPA
public class Tableau {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTablo;
    
    private String titre;
    
    private String support;
    
    private int largeur;
    
    private int hauteur;
    
    @ManyToOne
    private Artiste auteur;
    
    @OneToOne(mappedBy ="oeuvre")
    private Transaction vendu;
    
    @ManyToMany(mappedBy ="oeuvres")
    private List<Exposition> accrochage= new ArrayList<>();
    
}
