package nology.io.postcode_api.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "postcodes")
public class Postcode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "postcode", nullable = false, unique = true)
    private String postcode;

    @OneToMany(mappedBy = "postcode", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Suburb> suburbs;

    public Postcode() {
    }

    public Postcode(String postcode) {
        this.postcode = postcode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public List<Suburb> getSuburbs() {
        return suburbs;
    }

    public void setSuburbs(List<Suburb> suburbs) {
        this.suburbs = suburbs;
    }

    @Override
    public String toString() {
        return "Postcode{" +
                "id=" + id +
                ", postcode='" + postcode + '\'' +
                '}';
    }
}