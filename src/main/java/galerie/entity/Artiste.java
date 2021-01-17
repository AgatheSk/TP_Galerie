package galerie.entity;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter 
@NoArgsConstructor
//@RequiredArgsConstructor
@ToString
@Entity // Une entité JPA
public class Artiste extends Personne{
    private String biographie;
    
    @OneToMany (mappedBy = "auteur")
    private List<Tableau> oeuvres = new LinkedList<Tableau>();
}
