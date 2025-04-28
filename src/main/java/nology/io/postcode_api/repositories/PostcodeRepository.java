package nology.io.postcode_api.repositories;

import nology.io.postcode_api.entities.Postcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostcodeRepository extends JpaRepository<Postcode, Long> {

    Optional<Postcode> findByPostcode(String postcode);
}