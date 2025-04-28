package nology.io.postcode_api.repositories;

import nology.io.postcode_api.entities.Suburb;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SuburbRepository extends JpaRepository<Suburb, Long> {

    Optional<Suburb> findBySuburb(String suburb);

    @EntityGraph(attributePaths = "postcode")
    List<Suburb> findAllByPostcode_Postcode(String postcode);

    @EntityGraph(attributePaths = "postcode")
    List<Suburb> findAll();
}