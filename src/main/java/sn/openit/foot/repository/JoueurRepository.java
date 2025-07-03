package sn.openit.foot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.openit.foot.data.JoueurEntity;

import java.util.Optional;

public interface JoueurRepository extends JpaRepository<JoueurEntity, Long> {

    // This interface extends JpaRepository, which provides CRUD operations for JoueurEntity.
    // You can define custom query methods here if needed.

    // Example of a custom query method:
    // List<JoueurEntity> findByNom(String nom);
    // Note: Ensure that the package structure is correct and that this class is in the right place
    // for Spring Data JPA to recognize it as a repository.
    Optional<JoueurEntity> findOneByLastName(String lastName);

    Optional<JoueurEntity> findOneByLastNameIgnoreCase(String lastName);
}


