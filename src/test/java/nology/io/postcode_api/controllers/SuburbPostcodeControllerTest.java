package nology.io.postcode_api.controllers;

import nology.io.postcode_api.dto.CreateSuburbPostcodeDTO;
import nology.io.postcode_api.entities.Suburb;
import nology.io.postcode_api.services.SuburbPostcodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SuburbPostcodeController.class)
class SuburbPostcodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SuburbPostcodeService suburbPostcodeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnSuburbsByPostcode() throws Exception {
        String postcode = "2000";
        List<String> suburbs = Arrays.asList("Sydney", "Darling Harbour");

        when(suburbPostcodeService.getSuburbsByPostcode(postcode)).thenReturn(suburbs);

        mockMvc.perform(get("/api/suburbs/{postcode}", postcode)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0]").value("Sydney"))
                .andExpect(jsonPath("$[1]").value("Darling Harbour"));
    }

    @Test
    void shouldReturnPostcodeBySuburb() throws Exception {
        String suburb = "Sydney";
        String postcode = "2000";

        when(suburbPostcodeService.getPostcodeBySuburb(suburb)).thenReturn(postcode);

        mockMvc.perform(get("/api/postcodes/{suburb}", suburb)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("2000"));
    }

    @Test
    void shouldAddSuburbPostcode() throws Exception {
        String suburb = "Sydney";
        String postcode = "2000";
        Suburb savedSuburb = new Suburb(suburb, null);
        savedSuburb.setId(1L);

        CreateSuburbPostcodeDTO createSuburbPostcodeDTO = new CreateSuburbPostcodeDTO(suburb, postcode);

        when(suburbPostcodeService.addSuburbPostcode(any(CreateSuburbPostcodeDTO.class))).thenReturn(savedSuburb);

        mockMvc.perform(post("/api/suburbs")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"suburb\": \"Sydney\", \"postcode\": \"2000\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.suburb").value("Sydney"))
                .andExpect(jsonPath("$.postcode").doesNotExist());
    }

    @Test
    void shouldReturnAllSuburbPostcodes() throws Exception {
        Suburb suburb1 = new Suburb("Sydney", null);
        suburb1.setId(1L);
        Suburb suburb2 = new Suburb("Darling Harbour", null);
        suburb2.setId(2L);

        List<Suburb> suburbs = Arrays.asList(suburb1, suburb2);

        when(suburbPostcodeService.getAllSuburbPostcodes()).thenReturn(suburbs);

        mockMvc.perform(get("/api/suburbs")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].suburb").value("Sydney"))
                .andExpect(jsonPath("$[1].suburb").value("Darling Harbour"));
    }
}