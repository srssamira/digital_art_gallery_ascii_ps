package gallery.example.digital_art_gallery.app.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import gallery.example.digital_art_gallery.app.dtos.artist.ArtistCreateDTO;
import gallery.example.digital_art_gallery.app.dtos.artist.ArtistResponseDTO;
import gallery.example.digital_art_gallery.infra.entities.ArtistEntity;

public class ArtistMapper {

    public static ArtistResponseDTO toResponse(ArtistEntity newArtist) {
        return new ArtistResponseDTO();
    }

    public static ArtistEntity toEntity(ArtistCreateDTO newArtist) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(newArtist, ArtistEntity.class);
    }
}
