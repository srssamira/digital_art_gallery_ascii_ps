package gallery.example.digital_art_gallery.app.mappers;

import gallery.example.digital_art_gallery.app.dtos.artist.ArtistCreateDTO;
import gallery.example.digital_art_gallery.app.dtos.artist.ArtistResponseDTO;
import gallery.example.digital_art_gallery.infra.entities.ArtistEntity;

public class ArtistMapper {

    public static ArtistResponseDTO toResponse(ArtistEntity newArtist) {
        return new ArtistResponseDTO();
    }

    public static ArtistEntity toEntity(ArtistCreateDTO newArtist) {
        return new ArtistEntity();
    }
}
