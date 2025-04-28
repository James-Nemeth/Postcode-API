package nology.io.postcode_api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import nology.io.postcode_api.dto.CreateSuburbPostcodeDTO;
import nology.io.postcode_api.entities.Suburb;
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

    @Operation(summary = "Retrieve suburbs by postcode")
    @GetMapping("/suburbs/{postcode}")
    public ResponseEntity<List<String>> getSuburbsByPostcode(@PathVariable String postcode) {
        List<String> suburbs = suburbPostcodeService.getSuburbsByPostcode(postcode);
        return ResponseEntity.ok(suburbs);
    }

    @Operation(summary = "Retrieve postcode by suburb")
    @GetMapping("/postcodes/{suburb}")
    public ResponseEntity<String> getPostcodeBySuburb(@PathVariable String suburb) {
        String postcode = suburbPostcodeService.getPostcodeBySuburb(suburb);
        return ResponseEntity.ok(postcode);
    }

    @Operation(summary = "Add a new suburb-postcode combination")
    @PostMapping("/suburbs")
    public ResponseEntity<Suburb> addSuburbPostcode(
            @Valid @RequestBody CreateSuburbPostcodeDTO createSuburbPostcodeDTO) {
        Suburb savedSuburb = suburbPostcodeService.addSuburbPostcode(createSuburbPostcodeDTO);
        return ResponseEntity.ok(savedSuburb);
    }

    @Operation(summary = "Get all suburb-postcode combinations")
    @GetMapping("/suburbs")
    public ResponseEntity<List<Suburb>> getAllSuburbPostcodes() {
        List<Suburb> allSuburbPostcodes = suburbPostcodeService.getAllSuburbPostcodes();
        return ResponseEntity.ok(allSuburbPostcodes);
    }
}