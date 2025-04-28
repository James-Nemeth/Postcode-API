package nology.io.postcode_api.services;

import nology.io.postcode_api.dto.CreateSuburbPostcodeDTO;
import nology.io.postcode_api.entities.SuburbPostcode;
import nology.io.postcode_api.repositories.SuburbPostcodeRepository;
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
    private SuburbPostcodeRepository suburbPostcodeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        suburbPostcodeService = new SuburbPostcodeService();
        suburbPostcodeService.suburbPostcodeRepository = suburbPostcodeRepository;
    }

    @Test
    void shouldSaveSuburbPostcodeSuccessfully() {
        CreateSuburbPostcodeDTO dto = new CreateSuburbPostcodeDTO("Sydney", "2000");

        when(suburbPostcodeRepository.findBySuburb(dto.getSuburb())).thenReturn(Optional.empty());
        when(suburbPostcodeRepository.save(any(SuburbPostcode.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        SuburbPostcode result = suburbPostcodeService.addSuburbPostcode(dto);

        assertThat(result.getSuburb()).isEqualTo("Sydney");
        assertThat(result.getPostcode()).isEqualTo("2000");

        verify(suburbPostcodeRepository, times(1)).findBySuburb(dto.getSuburb());
        verify(suburbPostcodeRepository, times(1)).save(any(SuburbPostcode.class));
    }

    @Test
    void shouldGetSuburbsByPostcode() {
        String postcode = "2000";
        SuburbPostcode suburbPostcode = new SuburbPostcode("Sydney", postcode);

        when(suburbPostcodeRepository.findByPostcode(postcode))
                .thenReturn(Collections.singletonList(suburbPostcode));

        List<String> result = suburbPostcodeService.getSuburbsByPostcode(postcode);

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo("Sydney");

        verify(suburbPostcodeRepository, times(1)).findByPostcode(postcode);
    }

    @Test
    void shouldGetPostcodeBySuburb() {
        String suburb = "Sydney";
        SuburbPostcode suburbPostcode = new SuburbPostcode(suburb, "2000");

        when(suburbPostcodeRepository.findBySuburb(suburb))
                .thenReturn(Optional.of(suburbPostcode));

        String result = suburbPostcodeService.getPostcodeBySuburb(suburb);

        assertThat(result).isEqualTo("2000");

        verify(suburbPostcodeRepository, times(1)).findBySuburb(suburb);
    }
}