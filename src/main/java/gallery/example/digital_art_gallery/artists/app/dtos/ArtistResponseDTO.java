package gallery.example.digital_art_gallery.artists.app.dtos;

import gallery.example.digital_art_gallery.artworks.app.dtos.ArtWorkResponseDTO;

import java.util.List;

public class ArtistResponseDTO {

    private Long id;
    private String name;
    private String bio;
    private String email;
    private String instagram;
    private String twitter;
    private String website;
    private List<ArtWorkResponseDTO> artworks;

    public ArtistResponseDTO() {
    }

    public ArtistResponseDTO(Long id, String name, String bio, String email, String instagram, String twitter, String website, List<ArtWorkResponseDTO> artworks) {
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

    public List<ArtWorkResponseDTO> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<ArtWorkResponseDTO> artworks) {
        this.artworks = artworks;
    }
}
