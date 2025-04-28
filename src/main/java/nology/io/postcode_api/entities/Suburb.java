package nology.io.postcode_api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "suburbs")
public class Suburb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "suburb", nullable = false, unique = true)
    private String suburb;

    @ManyToOne
    @JoinColumn(name = "postcode_id", nullable = false)
    private Postcode postcode;

    public Suburb() {
    }

    public Suburb(String suburb, Postcode postcode) {
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

    public Postcode getPostcode() {
        return postcode;
    }

    public void setPostcode(Postcode postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "Suburb{" +
                "id=" + id +
                ", suburb='" + suburb + '\'' +
                ", postcode=" + postcode +
                '}';
    }
}