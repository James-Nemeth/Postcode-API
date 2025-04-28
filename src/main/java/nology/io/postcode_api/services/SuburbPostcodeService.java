package nology.io.postcode_api.services;

import nology.io.postcode_api.dto.CreateSuburbPostcodeDTO;
import nology.io.postcode_api.entities.SuburbPostcode;
import nology.io.postcode_api.repositories.SuburbPostcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SuburbPostcodeService {

    @Autowired
    SuburbPostcodeRepository suburbPostcodeRepository;

    // Retrieve a list of suburbs by postcode
    public List<String> getSuburbsByPostcode(String postcode) {
        List<SuburbPostcode> suburbPostcodes = suburbPostcodeRepository.findByPostcode(postcode);
        if (suburbPostcodes.isEmpty()) {
            throw new IllegalArgumentException("No suburbs found for postcode: " + postcode);
        }
        return suburbPostcodes.stream()
                .map(SuburbPostcode::getSuburb)
                .collect(Collectors.toList());
    }

    // Retrieve a postcode by suburb name
    public String getPostcodeBySuburb(String suburb) {
        Optional<SuburbPostcode> suburbPostcode = suburbPostcodeRepository.findBySuburb(suburb);
        if (suburbPostcode.isEmpty()) {
            throw new IllegalArgumentException("No postcode found for suburb: " + suburb);
        }
        return suburbPostcode.get().getPostcode();
    }

    // Add a new suburb-postcode combination
    public SuburbPostcode addSuburbPostcode(CreateSuburbPostcodeDTO createSuburbPostcodeDTO) {
        // Validate if the combination already exists
        Optional<SuburbPostcode> existing = suburbPostcodeRepository.findBySuburb(createSuburbPostcodeDTO.getSuburb());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Suburb already exists: " + createSuburbPostcodeDTO.getSuburb());
        }

        SuburbPostcode newSuburbPostcode = new SuburbPostcode(
                createSuburbPostcodeDTO.getSuburb(),
                createSuburbPostcodeDTO.getPostcode());

        return suburbPostcodeRepository.save(newSuburbPostcode);
    }

    // Get all suburb-postcode combinations
    public List<SuburbPostcode> getAllSuburbPostcodes() {
        return suburbPostcodeRepository.findAll();
    }
}