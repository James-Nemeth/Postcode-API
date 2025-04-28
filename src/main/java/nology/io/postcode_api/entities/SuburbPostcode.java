package nology.io.postcode_api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "suburb_postcode")
public class SuburbPostcode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "suburb", nullable = false)
    private String suburb;

    @Column(name = "postcode", nullable = false)
    private String postcode;

    public SuburbPostcode() {
    }

    public SuburbPostcode(String suburb, String postcode) {
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

    @Override
    public String toString() {
        return "SuburbPostcode{" +
                "id=" + id +
                ", suburb='" + suburb + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }
}