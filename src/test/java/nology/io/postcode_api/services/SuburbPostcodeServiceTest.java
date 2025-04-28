package nology.io.postcode_api.services;

import nology.io.postcode_api.dto.CreateSuburbPostcodeDTO;
import nology.io.postcode_api.entities.Postcode;
import nology.io.postcode_api.entities.Suburb;
import nology.io.postcode_api.repositories.PostcodeRepository;
import nology.io.postcode_api.repositories.SuburbRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SuburbPostcodeServiceTest {

    private SuburbPostcodeService suburbPostcodeService;

    @Mock
    private SuburbRepository suburbRepository;

    @Mock
    private PostcodeRepository postcodeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        suburbPostcodeService = new SuburbPostcodeService(suburbRepository, postcodeRepository);
    }

    @Test
    void shouldSaveSuburbPostcodeSuccessfully() {
        CreateSuburbPostcodeDTO dto = new CreateSuburbPostcodeDTO("Sydney", "2000");

        Postcode postcode = new Postcode("2000");
        Suburb suburb = new Suburb("Sydney", postcode);

        when(postcodeRepository.findByPostcode(dto.getPostcode())).thenReturn(Optional.of(postcode));
        when(suburbRepository.findBySuburb(dto.getSuburb())).thenReturn(Optional.empty());
        when(suburbRepository.save(any(Suburb.class))).thenReturn(suburb);

        Suburb result = suburbPostcodeService.addSuburbPostcode(dto);

        assertThat(result.getSuburb()).isEqualTo("Sydney");
        assertThat(result.getPostcode().getPostcode()).isEqualTo("2000");

        verify(postcodeRepository, times(1)).findByPostcode(dto.getPostcode());
        verify(suburbRepository, times(1)).findBySuburb(dto.getSuburb());
        verify(suburbRepository, times(1)).save(any(Suburb.class));
    }

    @Test
    void shouldGetSuburbsByPostcode() {
        String postcodeValue = "2000";
        Postcode postcode = new Postcode(postcodeValue);
        Suburb suburb = new Suburb("Sydney", postcode);

        when(suburbRepository.findByPostcode_Postcode(postcodeValue))
                .thenReturn(Collections.singletonList(suburb));

        List<String> result = suburbPostcodeService.getSuburbsByPostcode(postcodeValue);

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo("Sydney");

        verify(suburbRepository, times(1)).findByPostcode_Postcode(postcodeValue);
    }

    @Test
    void shouldGetPostcodeBySuburb() {
        String suburbName = "Sydney";
        Postcode postcode = new Postcode("2000");
        Suburb suburb = new Suburb(suburbName, postcode);

        when(suburbRepository.findBySuburb(suburbName)).thenReturn(Optional.of(suburb));

        String result = suburbPostcodeService.getPostcodeBySuburb(suburbName);

        assertThat(result).isEqualTo("2000");

        verify(suburbRepository, times(1)).findBySuburb(suburbName);
    }
}