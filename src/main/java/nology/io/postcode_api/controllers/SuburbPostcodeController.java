package nology.io.postcode_api.controllers;

import jakarta.validation.Valid;
import nology.io.postcode_api.dto.CreateSuburbPostcodeDTO;
import nology.io.postcode_api.entities.SuburbPostcode;
import nology.io.postcode_api.services.SuburbPostcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SuburbPostcodeController {

    @Autowired
    private SuburbPostcodeService suburbPostcodeService;

    // Retrieve suburb by postcode
    @GetMapping("/suburbs/{postcode}")
    public ResponseEntity<List<String>> getSuburbsByPostcode(@PathVariable String postcode) {
        List<String> suburbs = suburbPostcodeService.getSuburbsByPostcode(postcode);
        return ResponseEntity.ok(suburbs);
    }

    // Retrieve postcode by suburb
    @GetMapping("/postcodes/{suburb}")
    public ResponseEntity<String> getPostcodeBySuburb(@PathVariable String suburb) {
        String postcode = suburbPostcodeService.getPostcodeBySuburb(suburb);
        return ResponseEntity.ok(postcode);
    }

    // Add a new suburb-postcode combination
    @PostMapping("/suburbs")
    public ResponseEntity<SuburbPostcode> addSuburbPostcode(
            @Valid @RequestBody CreateSuburbPostcodeDTO createSuburbPostcodeDTO) {
        SuburbPostcode savedSuburbPostcode = suburbPostcodeService.addSuburbPostcode(createSuburbPostcodeDTO);
        return ResponseEntity.ok(savedSuburbPostcode);
    }

    // Get all suburb-postcode combinations
    @GetMapping("/suburbs")
    public ResponseEntity<List<SuburbPostcode>> getAllSuburbPostcodes() {
        List<SuburbPostcode> allSuburbPostcodes = suburbPostcodeService.getAllSuburbPostcodes();
        return ResponseEntity.ok(allSuburbPostcodes);
    }
}