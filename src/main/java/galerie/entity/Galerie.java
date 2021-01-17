package galerie.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity // Une entité JPA
public class Galerie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NonNull
    private String nom;

    @Column(unique = true)
    @NonNull
    private String adresse;

    // TODO : Mettre en oeuvre la relation oneToMany vers Exposition
    @OneToMany(mappedBy = "organisateur")
    private List<Exposition> evenements = new ArrayList<>();

    /**
     * Calculer le chiffre d'affaires annuel pour une galerie
     *
     * @param annee
     * @return le chiffre annuel d'affaires de cette galerie
     */
    float chiffreAffaireAnnuelPour(int annee) {
        float budget = 0;
        for (Exposition e : evenements) {
            for (Transaction t : e.getVentes()) {
                if (t.getVenduLe().getYear() == annee) {
                    budget += t.getPrixVente();
                }

            }
        }
        return budget;
    }
}
