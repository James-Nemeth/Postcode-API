package nology.io.postcode_api.repositories;

import nology.io.postcode_api.entities.SuburbPostcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SuburbPostcodeRepository extends JpaRepository<SuburbPostcode, Long> {

    // Custom query to find suburbs by postcode
    List<SuburbPostcode> findByPostcode(String postcode);

    // Custom query to find a suburb-postcode by suburb name
    Optional<SuburbPostcode> findBySuburb(String suburb);
}