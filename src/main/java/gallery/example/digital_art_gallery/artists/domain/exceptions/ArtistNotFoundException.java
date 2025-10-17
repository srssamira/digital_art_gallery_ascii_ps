package gallery.example.digital_art_gallery.artists.domain.exceptions;

public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException(Long artistId) {
        super("Artist with ID " + artistId + " not found.");
    }
}
