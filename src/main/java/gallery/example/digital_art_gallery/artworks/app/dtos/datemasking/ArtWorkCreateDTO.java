package gallery.example.digital_art_gallery.artworks.app.dtos.datemasking;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ArtWorkCreateDTO {

    private String title;
    private String description;
    private String imageUrl;

    @NotNull(message = "O ID do artista é obrigatório.")
    @Positive(message = "O ID do artista deve ser um número positivo.")
    private Long artistId;

    public ArtWorkCreateDTO() {
    }

    public ArtWorkCreateDTO(String title, String description, String imageUrl, @NotNull(message = "O ID do artista é obrigatório.") @Positive(message = "O ID do artista deve ser um número positivo.") Long artistId) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.artistId = artistId;
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

    public @NotNull(message = "O ID do artista é obrigatório.") @Positive(message = "O ID do artista deve ser um número positivo.") Long getArtistId() {
        return artistId;
    }

    public void setArtistId(@NotNull(message = "O ID do artista é obrigatório.") @Positive(message = "O ID do artista deve ser um número positivo.") Long artistId) {
        this.artistId = artistId;
    }
}