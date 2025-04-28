package nology.io.postcode_api.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateSuburbPostcodeDTO {

    @NotBlank(message = "Suburb name is required")
    private String suburb;

    @NotBlank(message = "Postcode is required")
    private String postcode;

    public CreateSuburbPostcodeDTO() {
    }

    public CreateSuburbPostcodeDTO(String suburb, String postcode) {
        this.suburb = suburb;
        this.postcode = postcode;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "CreateSuburbPostcodeDTO{" +
                "suburb='" + suburb + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }
}