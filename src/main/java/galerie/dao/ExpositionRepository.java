package galerie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import galerie.entity.Exposition;
import org.springframework.data.jpa.repository.Query;

public interface ExpositionRepository extends JpaRepository<Exposition, Integer>{
    /**
    * Calculer le chiffre d'affaires pour une exposition
    * @param id la clé primaire de l'exposition
    * @return le chiffre d'affaires de cette exposition
    */
    @Query(
        value = 
        "SELECT SUM(t.prix_vente) AS CA_exposition "
        + "FROM Exposition e, Transaction t " +
        "WHERE e.id_expo = :id And t.lieu_de_vente_id_expo=e.id_expo;", 
        nativeQuery = true
    )
    float chiffreAffairePour(Integer id);
}
