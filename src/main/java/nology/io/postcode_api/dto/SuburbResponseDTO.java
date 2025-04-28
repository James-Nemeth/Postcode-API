package nology.io.postcode_api.dto;

public class SuburbResponseDTO {

    private Long id;
    private String suburb;
    private String postcode;

    public SuburbResponseDTO(Long id, String suburb, String postcode) {
        this.id = id;
        this.suburb = suburb;
        this.postcode = postcode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}