package nology.io.postcode_api.services;

import nology.io.postcode_api.dto.CreateSuburbPostcodeDTO;
import nology.io.postcode_api.dto.SuburbResponseDTO;
import nology.io.postcode_api.entities.Postcode;
import nology.io.postcode_api.entities.Suburb;
import nology.io.postcode_api.exceptions.ResourceConflictException;
import nology.io.postcode_api.repositories.PostcodeRepository;
import nology.io.postcode_api.repositories.SuburbRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuburbPostcodeService {

    private final SuburbRepository suburbRepository;
    private final PostcodeRepository postcodeRepository;

    public SuburbPostcodeService(SuburbRepository suburbRepository, PostcodeRepository postcodeRepository) {
        this.suburbRepository = suburbRepository;
        this.postcodeRepository = postcodeRepository;
    }

    // Retrieve a list of suburbs by postcode
    public List<String> getSuburbsByPostcode(String postcode) {
        List<Suburb> suburbs = suburbRepository.findAllByPostcode_Postcode(postcode);
        if (suburbs.isEmpty()) {
            throw new IllegalArgumentException("No suburbs found for postcode: " + postcode);
        }
        return suburbs.stream()
                .map(Suburb::getSuburb)
                .collect(Collectors.toList());
    }

    // Retrieve a postcode by suburb name
    public String getPostcodeBySuburb(String suburb) {
        Suburb foundSuburb = suburbRepository.findBySuburb(suburb)
                .orElseThrow(() -> new IllegalArgumentException("No postcode found for suburb: " + suburb));
        return foundSuburb.getPostcode().getPostcode();
    }

    // Add a new suburb-postcode combination
    public Suburb addSuburbPostcode(CreateSuburbPostcodeDTO createSuburbPostcodeDTO) {
        Postcode postcode = postcodeRepository.findByPostcode(createSuburbPostcodeDTO.getPostcode())
                .orElseGet(() -> postcodeRepository.save(new Postcode(createSuburbPostcodeDTO.getPostcode())));

        if (suburbRepository.findBySuburb(createSuburbPostcodeDTO.getSuburb()).isPresent()) {
            throw new ResourceConflictException("Suburb already exists: " + createSuburbPostcodeDTO.getSuburb());
        }

        Suburb suburb = new Suburb(createSuburbPostcodeDTO.getSuburb(), postcode);
        return suburbRepository.save(suburb);
    }

    // Get all suburb-postcode combinations as DTOs
    public List<SuburbResponseDTO> getAllSuburbPostcodes() {
        return suburbRepository.findAll().stream()
                .map(suburb -> new SuburbResponseDTO(
                        suburb.getId(),
                        suburb.getSuburb(),
                        suburb.getPostcode().getPostcode()))
                .collect(Collectors.toList());
    }
}