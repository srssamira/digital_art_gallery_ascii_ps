package gallery.example.digital_art_gallery.artworks.app.dtos.datemasking;

public class ArtWorkResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String artistName;

    public ArtWorkResponseDTO() {
    }

    public ArtWorkResponseDTO(Long id, String title, String description, String imageUrl, String artistName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.artistName = artistName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

}
