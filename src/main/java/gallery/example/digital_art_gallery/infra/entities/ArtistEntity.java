package gallery.example.digital_art_gallery.infra.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
public class ArtistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String bio;
    private String email;
    private String instagram;
    private String twitter;
    private String website;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ArtWorkEntity> artworks;

    public ArtistEntity() {
    }

    public ArtistEntity(Long id, String name, String bio, String email, String instagram, String twitter, String website, List<ArtWorkEntity> artworks) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.email = email;
        this.instagram = instagram;
        this.twitter = twitter;
        this.website = website;
        this.artworks = artworks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<ArtWorkEntity> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<ArtWorkEntity> artworks) {
        this.artworks = artworks;
    }
}
