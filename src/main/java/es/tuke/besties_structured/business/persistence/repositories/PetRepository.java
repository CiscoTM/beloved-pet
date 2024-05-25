package es.tuke.besties_structured.business.persistence.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import es.tuke.besties_structured.business.persistence.entities.PetEntity;
import java.util.List;


public interface PetRepository extends JpaRepository<PetEntity, Long> {

    List<PetEntity> findByNombre(String nombre);
    boolean existsById(@NonNull Long id);
    boolean existsByNombre(String nombre); 
    List<PetEntity> findByActive(boolean active);

    @Query(value = "SELECT * FROM pets p WHERE p.active=1 AND p.pet_id= ?1", nativeQuery = true)
    PetEntity ActiveById(Long id);

    @Query(value = "Select * from pets p WHERE p.active=0 AND p.pet_id= ?1", nativeQuery = true)
    PetEntity InactiveById(Long id);
    

}
