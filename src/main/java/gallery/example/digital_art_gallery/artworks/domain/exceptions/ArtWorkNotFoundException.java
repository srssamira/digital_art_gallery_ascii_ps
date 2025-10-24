package gallery.example.digital_art_gallery.artworks.domain.exceptions;

public class ArtWorkNotFoundException extends RuntimeException {
    public ArtWorkNotFoundException(Long artWorkId) {
        super("ArtWork with ID " + artWorkId + " not found.");
    }
}
